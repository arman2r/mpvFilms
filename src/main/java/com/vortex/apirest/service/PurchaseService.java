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

import java.util.List;
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

        // Obtener el usuario autenticado desde Spring Security
        String authenticatedEmail = authService.getAuthenticatedUserEmail();

        // Valida que el email de la compra coincide con el autenticado
        if (!authenticatedEmail.equals(purchaseDTO.getUserEmail())) {
            throw new RuntimeException("El email de la compra no coincide con el usuario autenticado");
        }

        Purchase purchase = new Purchase();
        purchase.setUserEmail(purchaseDTO.getUserEmail());

        final Purchase finalPurchase = purchase;

        List<PurchaseItem> items = purchaseDTO.getItems().stream().map(itemDTO -> {
            Optional<FilmEntity> filmOptional = filmRepository.findById(itemDTO.getFilmId());

            if (filmOptional.isEmpty()) {
                throw new RuntimeException("Película con ID " + itemDTO.getFilmId() + " no encontrada");
            }

            FilmEntity film = filmOptional.get();
            PurchaseItem item = new PurchaseItem();
            item.setFilm(film);
            item.setQuantity(itemDTO.getQuantity());
            item.setTicketPrice(itemDTO.getTicketPrice());
            item.calculateTotalPrice();
            item.setPurchase(finalPurchase);
            return item;
        }).collect(Collectors.toList());

        purchase.setItems(items);
        purchase.calculateTotalAmount();
        purchase.setConfirmationStatus(0); // 0 por defecto hasta confirmar la compra
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
        return purchaseRepository.findByUserEmail(email).stream().map(purchase -> {
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
        }).collect(Collectors.toList());
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
     * Eliminar un conjunto de PurchaseItems de una compra no confirmada.
     */
    public Purchase removeItemsFromPurchase(Long purchaseId, List<Long> itemIds) {
        Optional<Purchase> optionalPurchase = purchaseRepository.findById(purchaseId);

        if (optionalPurchase.isEmpty()) {
            throw new RuntimeException("Compra no encontrada");
        }

        Purchase purchase = optionalPurchase.get();

        // Verificar si la compra ya fue confirmada
        if (purchase.getConfirmationStatus() == 1) {
            throw new RuntimeException("No se pueden eliminar items de una compra confirmada");
        }

        // Filtrar los items a eliminar
        List<PurchaseItem> itemsToRemove = purchase.getItems().stream()
                .filter(item -> itemIds.contains(item.getId()))
                .toList();

        if (itemsToRemove.isEmpty()) {
            throw new RuntimeException("No se encontraron los items especificados en la compra");
        }

        // Remover los items de la lista
        purchase.getItems().removeAll(itemsToRemove);
        purchase.calculateTotalAmount(); // Recalcula el total de la compra

        // Si la compra se queda sin ítems, eliminarla completamente
        if (purchase.getItems().isEmpty()) {
            purchaseRepository.delete(purchase);
            return null; // Retorna null indicando que la compra fue eliminada
        } else {
            purchaseRepository.save(purchase);
        }

        // Eliminar los items de la base de datos
        purchaseItemRepository.deleteAll(itemsToRemove);

        return purchase;
    }
}
