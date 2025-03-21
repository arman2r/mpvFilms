package com.vortex.apirest.DTO;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class PurchaseDTO {
    private String userEmail;
    private List<PurchaseItemDTO> items;
    private Double totalAmount;
    private Integer confirmationStatus;
}