package org.app.gestion_meubles.interfaces;

import org.app.gestion_meubles.model.dto.FurnitureDTO;

import java.util.List;
import java.util.UUID;

public interface FurnitureInterface {
    List<FurnitureDTO> getAllFurnitures();
    FurnitureDTO getFurnitureById(UUID id)throws Exception;
    FurnitureDTO saveFurniture(FurnitureDTO furnitureDTO);
    void deleteFurniture(Long id)throws Exception;


}
