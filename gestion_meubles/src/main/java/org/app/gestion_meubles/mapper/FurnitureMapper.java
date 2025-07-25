package org.app.gestion_meubles.mapper;

import org.app.gestion_meubles.model.dto.FurnitureDTO;
import org.app.gestion_meubles.model.entity.Furniture;

import java.util.List;

public class FurnitureMapper {

    public static FurnitureDTO furnitureToFurnitureDTO(Furniture furniture) {
        return new FurnitureDTO(furniture.getId(), furniture.getName(), furniture.getDescription(), furniture.getPrice(),furniture.getStock());
    }

    public static List<FurnitureDTO> furnituresToFurnituresDTO(List<Furniture> furnitures) {
        return furnitures.stream().map(FurnitureMapper::furnitureToFurnitureDTO).toList();
    }

    public static Furniture furnitureDTOToFurniture(FurnitureDTO furnitureDTO) {
        return new Furniture(furnitureDTO.getId(), furnitureDTO.getName(), furnitureDTO.getDescription(), furnitureDTO.getPrice(),furnitureDTO.getStock());
    }

    public static List<Furniture> furnituresDTOToFurnitures(List<FurnitureDTO> furnituresDTO) {
        return furnituresDTO.stream().map(FurnitureMapper::furnitureDTOToFurniture).toList();
    }
}
