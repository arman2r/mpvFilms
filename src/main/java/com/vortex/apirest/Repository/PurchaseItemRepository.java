package com.vortex.apirest.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vortex.apirest.Entity.PurchaseItem;

public interface PurchaseItemRepository extends JpaRepository<PurchaseItem, Long> {
     // Buscar todos los items de una compra específica
    List<PurchaseItem> findByPurchaseId(Long purchaseId);

    // Eliminar todos los items de una compra específica
    void deleteByPurchaseId(Long purchaseId);

}
