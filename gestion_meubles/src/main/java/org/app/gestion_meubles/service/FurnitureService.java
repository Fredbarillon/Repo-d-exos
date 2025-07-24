package org.app.gestion_meubles.service;

import org.app.gestion_meubles.interfaces.FurnitureInterface;
import org.app.gestion_meubles.mapper.FurnitureMapper;
import org.app.gestion_meubles.model.dto.FurnitureDTO;
import org.app.gestion_meubles.model.entity.Furniture;
import org.app.gestion_meubles.repository.FurnitureRepository;

import java.util.List;
import java.util.UUID;

public class FurnitureService implements FurnitureInterface {
    private FurnitureRepository furnitutreRepository;

    public FurnitureService(FurnitureRepository furnitutreRepository) {
        this.furnitutreRepository = furnitutreRepository;
    }

    @Override
    public List<FurnitureDTO> getAllFurnitures() {
        List<Furniture> furnitures = furnitutreRepository.findAll();
        return FurnitureMapper.usersToUserDTOs(furnitures);
    }

    @Override
    public FurnitureDTO getFurnitureById(UUID id) throws Exception {
        return null;
    }

    @Override
    public FurnitureDTO saveFurniture(FurnitureDTO furnitureDTO) {
        return null;
    }

    @Override
    public void deleteFurniture(Long id) throws Exception {

    }
}
