package com.vortex.apirest.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
/* loombok sirve para no crear los getters y setters manualmente
 private Integer filmId;
 public Integer getFilmId() {
        return filmId;
    }
*/
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseItemDTO {
    private Integer filmId;
    private String filmName;
    private Integer quantity;
    private Double ticketPrice;
    private Double totalPrice;
}