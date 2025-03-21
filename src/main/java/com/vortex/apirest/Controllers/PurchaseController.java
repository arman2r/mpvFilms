package com.vortex.apirest.Controllers;

import com.vortex.apirest.DTO.PurchaseDTO;
import com.vortex.apirest.Entity.Purchase;
import com.vortex.apirest.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("purchase/create")
    public PurchaseDTO createPurchase(@RequestBody PurchaseDTO purchaseDTO) {
        return purchaseService.createPurchase(purchaseDTO);
    }

    @GetMapping("purchase/user/{email}")
    public List<PurchaseDTO> getUserPurchases(@PathVariable String email) {
        return purchaseService.getPurchasesByUserEmail(email);
    }

    @PutMapping("purchase/confirm/{purchaseId}")
    public ResponseEntity<String> confirmPurchase(
            @PathVariable Long purchaseId,
            @RequestParam Integer confirmationStatus,
            @RequestBody String htmlBody) {

        purchaseService.updateConfirmationStatus(purchaseId, confirmationStatus, htmlBody);
        return ResponseEntity.ok("Estado de compra actualizado correctamente");
    }

    /**
     * Elimina una o mas ítems de una compra no confirmada
     */
    @DeleteMapping("purchase/remove-items/{purchaseId}")
    public ResponseEntity<?> removeItemsFromPurchase(
            @PathVariable Long purchaseId,
            @RequestBody Map<String, List<Long>> requestBody) {

        List<Long> itemIds = requestBody.get("itemIds");

        if (itemIds == null || itemIds.isEmpty()) {
            return ResponseEntity.badRequest().body("Debe proporcionar una lista de itemIds para eliminar.");
        }

        Purchase updatedPurchase = purchaseService.removeItemsFromPurchase(purchaseId, itemIds);

        if (updatedPurchase == null) {
            return ResponseEntity.ok("Compra eliminada porque no tenía más ítems.");
        }

        return ResponseEntity.ok(updatedPurchase);
    }
}