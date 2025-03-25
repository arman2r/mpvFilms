package com.vortex.apirest.Controllers;

import com.vortex.apirest.DTO.PurchaseDTO;
import com.vortex.apirest.DTO.PurchaseItemDTO;
import com.vortex.apirest.Entity.Purchase;
import com.vortex.apirest.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/purchase/create")
    public PurchaseDTO createPurchase(@RequestBody PurchaseDTO purchaseDTO) {
        return purchaseService.createPurchase(purchaseDTO);
    }

    @GetMapping("/purchase/user/{email}")
    public List<PurchaseDTO> getUserPurchases(@PathVariable String email) {
        return purchaseService.getPurchasesByUserEmail(email);
    }

    @PutMapping("/purchase/confirm/{purchaseId}")
    public ResponseEntity<String> confirmPurchase(
            @PathVariable Long purchaseId,
            @RequestParam Integer confirmationStatus,
            @RequestBody String htmlBody) {

        purchaseService.updateConfirmationStatus(purchaseId, confirmationStatus, htmlBody);
        return ResponseEntity.ok("Estado de compra actualizado correctamente");
    }

    /**
     * Actualiza la cantidad de tickets de una compra no confirmada
     * http://localhost:8080/api/purchases/purchase/update-ticket-quantity/{purchaseId}/{itemId}?newQuantity=5
     */
    @PutMapping("/purchase/update-ticket-quantity/{purchaseId}/{filmId}")
    public ResponseEntity<?> updateTicketQuantity(
            @PathVariable Long purchaseId,
            @PathVariable Long filmId,
            @RequestBody Map<String, Integer> request) {
        
        Integer newQuantity = request.get("newQuantity");
        
        if (newQuantity == null || newQuantity <= 0) {
            return ResponseEntity.badRequest().body("La cantidad debe ser mayor que 0");
        }
    
        try {
            Purchase updatedPurchase = purchaseService.updateTicketQuantity(purchaseId, filmId, newQuantity);
            return ResponseEntity.ok(updatedPurchase);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Elimina una o mas ítems de una compra no confirmada
     */
    @DeleteMapping("/purchase/remove-items-by-film/{purchaseId}")
    public ResponseEntity<?> removeItemsByFilmIds(
            @PathVariable Long purchaseId,
            @RequestBody Map<String, List<Long>> requestBody) {

        try {
            List<Long> filmIds = Optional.ofNullable(requestBody.get("filmIds"))
                    .orElseThrow(() -> new IllegalArgumentException("Se requiere filmIds"));

            Map<String, Object> result = purchaseService.removeItemsByFilmIds(purchaseId, filmIds);
            return ResponseEntity.ok(result);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", e.getMessage(),
                    "success", false));
        }
    }

    @GetMapping("/purchase/{purchaseId}/items")
    public ResponseEntity<?> getPurchaseItems(@PathVariable Long purchaseId) {
        List<PurchaseItemDTO> purchaseItems = purchaseService.getPurchaseItems(purchaseId);

        if (purchaseItems.isEmpty()) {
            return ResponseEntity.ok("No hay ítems en esta compra.");
        }

        return ResponseEntity.ok(purchaseItems);
    }
}