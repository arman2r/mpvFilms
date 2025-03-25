package com.vortex.apirest.service;

import com.vortex.apirest.DTO.PurchaseDTO;
import com.vortex.apirest.DTO.PurchaseItemDTO;
import com.vortex.apirest.Entity.FilmEntity;
import com.vortex.apirest.Entity.Purchase;
import com.vortex.apirest.Entity.PurchaseItem;
import com.vortex.apirest.Repository.FilmRepository;
import com.vortex.apirest.Repository.PurchaseItemRepository;
import com.vortex.apirest.Repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private PurchaseItemRepository purchaseItemRepository;

    @Autowired
    private EmailService emailService;

    public PurchaseDTO createPurchase(PurchaseDTO purchaseDTO) {
        String authenticatedEmail = authService.getAuthenticatedUserEmail();

        if (!authenticatedEmail.equals(purchaseDTO.getUserEmail())) {
            throw new RuntimeException("El email de la compra no coincide con el usuario autenticado");
        }

        Optional<Purchase> existingPurchaseOpt = purchaseRepository
                .findByUserEmailAndConfirmationStatus(authenticatedEmail, 0);

        Purchase purchase = existingPurchaseOpt.orElseGet(() -> {
            Purchase newPurchase = new Purchase();
            newPurchase.setUserEmail(purchaseDTO.getUserEmail());
            newPurchase.setConfirmationStatus(0);
            newPurchase.setItems(new ArrayList<>());
            return newPurchase;
        });

        final Purchase finalPurchase = purchase;

        List<PurchaseItem> newItems = purchaseDTO.getItems().stream().map(itemDTO -> {
            FilmEntity film = filmRepository.findById(itemDTO.getFilmId())
                    .orElseThrow(
                            () -> new RuntimeException("Película con ID " + itemDTO.getFilmId() + " no encontrada"));

            Optional<PurchaseItem> existingItemOpt = finalPurchase.getItems().stream()
                    .filter(item -> item.getFilm().getId().equals(itemDTO.getFilmId()))
                    .findFirst();

            if (existingItemOpt.isPresent()) {
                PurchaseItem existingItem = existingItemOpt.get();
                existingItem.setQuantity(itemDTO.getQuantity()); // Se usa la cantidad del frontend
                existingItem.calculateTotalPrice();
                return existingItem;
            } else {
                PurchaseItem newItem = new PurchaseItem();
                newItem.setFilm(film);
                newItem.setQuantity(itemDTO.getQuantity()); // Se usa la cantidad del frontend
                newItem.setTicketPrice(itemDTO.getTicketPrice());
                newItem.calculateTotalPrice();
                newItem.setPurchase(finalPurchase);
                return newItem;
            }
        }).collect(Collectors.toList());

        purchase.getItems().addAll(newItems);
        purchase.calculateTotalAmount();
        purchase = purchaseRepository.save(purchase);

        return convertToDTO(purchase);
    }

    private PurchaseDTO convertToDTO(Purchase purchase) {
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        purchaseDTO.setUserEmail(purchase.getUserEmail());

        List<PurchaseItemDTO> items = purchase.getItems().stream().map(item -> {
            PurchaseItemDTO itemDTO = new PurchaseItemDTO();
            itemDTO.setFilmName(item.getFilm().getTitle());
            itemDTO.setQuantity(item.getQuantity());
            itemDTO.setTicketPrice(item.getTicketPrice());
            itemDTO.setTotalPrice(item.getTotalPrice());
            return itemDTO;
        }).collect(Collectors.toList());

        purchaseDTO.setItems(items);
        return purchaseDTO;
    }

    public List<PurchaseDTO> getPurchasesByUserEmail(String email) {
        return purchaseRepository.findByUserEmail(email).stream()
                .map(this::mapPurchaseToDTO)
                .collect(Collectors.toList());
    }

    private PurchaseDTO mapPurchaseToDTO(Purchase purchase) {
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        purchaseDTO.setId(purchase.getId().intValue());
        purchaseDTO.setUserEmail(purchase.getUserEmail());
        purchaseDTO.setTotalAmount(purchase.getTotalAmount());
        purchaseDTO.setConfirmationStatus(purchase.getConfirmationStatus());

        List<PurchaseItemDTO> items = purchase.getItems().stream()
                .map(this::mapItemToDTO)
                .collect(Collectors.toList());

        purchaseDTO.setItems(items);
        return purchaseDTO;
    }

    private PurchaseItemDTO mapItemToDTO(PurchaseItem item) {
        PurchaseItemDTO itemDTO = new PurchaseItemDTO();
        itemDTO.setFilmId(item.getFilm().getId()); // Añadir el filmId
        itemDTO.setFilmName(item.getFilm().getTitle());
        itemDTO.setQuantity(item.getQuantity());
        itemDTO.setTicketPrice(item.getTicketPrice());
        itemDTO.setTotalPrice(item.getTotalPrice());
        return itemDTO;
    }

    @Transactional
    public void updateConfirmationStatus(Long purchaseId, Integer confirmationStatus, String htmlBody) {
        Purchase purchase = purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new RuntimeException("Compra no encontrada"));

        purchase.setConfirmationStatus(confirmationStatus);
        purchaseRepository.save(purchase);

        // Si la compra se confirma, enviamos el correo con el HTML que envía Angular
        if (confirmationStatus == 1) {
            String toEmail = purchase.getUserEmail();
            String subject = "Confirmación de Compra";

            emailService.sendEmail(toEmail, subject, htmlBody);
        }
    }

    /**
     * Actualiza la cantidad de tickets de un PurchaseItems de una compra no
     * confirmada.
     */
    @Transactional
    public Purchase updateTicketQuantity(Long purchaseId, Long filmId, Integer newQuantity) {
        Purchase purchase = purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new RuntimeException("Compra no encontrada"));

        if (purchase.getConfirmationStatus() == 1) {
            throw new RuntimeException("No se pueden modificar tickets en una compra confirmada");
        }

        System.out.println("Buscando item con Film ID: " + filmId);

        purchase.getItems().forEach(item -> System.out
                .println("Item en compra -> ID: " + item.getId() + ", Film ID: " + item.getFilm().getId()));

        PurchaseItem item = purchase.getItems().stream()
                .filter(i -> i.getFilm().getId().intValue() == filmId.intValue())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Item con Film ID no encontrado en la compra"));

        System.out.println("Item encontrado con Film ID: " + item.getFilm().getId());

        // Actualizar la cantidad y recalcular el precio total
        item.setQuantity(newQuantity);
        item.calculateTotalPrice();

        // Recalcular el total de la compra
        purchase.calculateTotalAmount();

        // Guardar cambios en la base de datos
        purchaseRepository.save(purchase);

        return purchase;
    }

    /**
     * Eliminar un conjunto de PurchaseItems de una compra no confirmada.
     */
    public Map<String, Object> removeItemsByFilmIds(Long purchaseId, List<Long> filmIds) {
        Purchase purchase = purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new RuntimeException("Compra no encontrada"));

        // Convertir a Integer para comparar con FilmEntity.id
        List<Integer> filmIdsInt = filmIds.stream()
                .map(Long::intValue)
                .collect(Collectors.toList());

        // Buscar el ítem a eliminar
        PurchaseItem itemToRemove = purchase.getItems().stream()
                .filter(item -> filmIdsInt.contains(item.getFilm().getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        "Película no encontrada en la compra. FilmIDs existentes: " +
                                purchase.getItems().stream()
                                        .map(item -> item.getFilm().getId())
                                        .collect(Collectors.toList())));

        // Eliminar y actualizar
        purchase.getItems().remove(itemToRemove);
        purchase.calculateTotalAmount();

        Map<String, Object> response = new HashMap<>();
        response.put("deletedItem", Map.of(
                "filmId", itemToRemove.getFilm().getId(),
                "filmName", itemToRemove.getFilm().getTitle(),
                "quantity", itemToRemove.getQuantity()));

        if (purchase.getItems().isEmpty()) {
            purchaseRepository.delete(purchase);
            response.put("message", "Compra eliminada por no tener más ítems");
        } else {
            purchaseRepository.save(purchase);
            response.put("remainingTotal", purchase.getTotalAmount());
        }

        purchaseItemRepository.delete(itemToRemove);
        return response;
    }

    public List<PurchaseItemDTO> getPurchaseItems(Long purchaseId) {
        Purchase purchase = purchaseRepository.findById(purchaseId).orElse(null);

        if (purchase == null) {
            return List.of(); // Retorna una lista vacía si no encuentra la compra
        }

        return purchase.getItems().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private PurchaseItemDTO convertToDto(PurchaseItem item) {
        return new PurchaseItemDTO(
                item.getId().intValue(),
                item.getFilm().getTitle(),
                item.getQuantity(),
                item.getTicketPrice(),
                item.getTotalPrice());
    }
}
