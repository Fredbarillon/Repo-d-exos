package org.app.gestion_meubles.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddFurnitureToCartItemDTO {
    private UUID furnitureId;
    private int quantity;
}
