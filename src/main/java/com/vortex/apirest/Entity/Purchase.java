package com.vortex.apirest.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Getter
@Setter
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail; // Relación con el usuario por email

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<PurchaseItem> items; // Lista de películas compradas

    private Double totalAmount; // Gran total de la compra

    private Integer confirmationStatus = 0; // Estado de confirmación de la compra

    public void calculateTotalAmount() {
        this.totalAmount = items.stream().mapToDouble(PurchaseItem::getTotalPrice).sum();
    }
}
