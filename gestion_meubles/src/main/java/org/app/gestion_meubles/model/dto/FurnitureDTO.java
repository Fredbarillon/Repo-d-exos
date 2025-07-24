package org.app.gestion_meubles.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FurnitureDTO {
    private UUID id;
    @NotNull
    @Size(max = 255)
    private String name;
    @NotNull
    @Size(max = 765)
    private String description;
    @NotNull
    private Double price;
    @Min(1)
    @Max(100)
    private int stock;

}
