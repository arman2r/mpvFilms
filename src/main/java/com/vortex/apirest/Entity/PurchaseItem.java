package com.vortex.apirest.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PurchaseItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false)
    private FilmEntity film; // Ahora se relaciona con la película completa
    
    private Integer quantity; // Cantidad de tickets
    private Double ticketPrice; // Precio unitario del ticket
    private Double totalPrice; // Precio total (quantity * ticketPrice)

    @ManyToOne
    @JoinColumn(name = "purchase_id", nullable = false)
    @JsonBackReference
    private Purchase purchase;

    public void calculateTotalPrice() {
        this.totalPrice = this.quantity * this.ticketPrice;
    }
}
