package org.app.gestion_meubles.service;

import org.app.gestion_meubles.interfaces.FurnitureInterface;
import org.app.gestion_meubles.mapper.FurnitureMapper;
import org.app.gestion_meubles.model.dto.FurnitureDTO;
import org.app.gestion_meubles.model.entity.Furniture;
import org.app.gestion_meubles.repository.FurnitureRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class FurnitureService implements FurnitureInterface {
    private FurnitureRepository furnitureRepository;

    public FurnitureService(FurnitureRepository furnitureRepository) {
        this.furnitureRepository = furnitureRepository;
    }

    @Override
    public List<FurnitureDTO> getAllFurnitures() {
        List<Furniture> furnitures = furnitureRepository.findAll();
        return FurnitureMapper.furnituresToFurnituresDTO(furnitures);
    }

    @Override
    public FurnitureDTO getFurnitureById(UUID id) throws Exception {
        Furniture furniture = furnitureRepository.findById(id).orElse(null);

        if (furniture == null)
            throw new Exception("Le meuble d'ID : " + id + " n'existe pas.");

        return FurnitureMapper.furnitureToFurnitureDTO(furniture);
    }

    @Override
    public FurnitureDTO saveFurniture(FurnitureDTO furnitureDTO) {
        Furniture furniture = furnitureRepository.save(FurnitureMapper.furnitureDTOToFurniture(furnitureDTO));
        return FurnitureMapper.furnitureToFurnitureDTO(furniture);
    }

    @Override
    public void deleteFurniture(UUID id) throws Exception {
        if (!furnitureRepository.existsById(id))
            throw new Exception("Le meuble d'ID : " + id + " n'existe pas.");
        furnitureRepository.deleteById(id);
    }
}
