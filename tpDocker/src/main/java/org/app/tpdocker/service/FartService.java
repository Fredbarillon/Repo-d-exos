package org.app.tpdocker.service;

import org.app.tpdocker.interfaces.FartInterface;
import org.app.tpdocker.mapper.FartMapper;
import org.app.tpdocker.model.entity.Fart;
import org.app.tpdocker.model.dto.FartDTO;
import org.app.tpdocker.repository.FartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FartService implements FartInterface {

    private final FartRepository fartRepository;

    public FartService(FartRepository fartRepository) {
        this.fartRepository = fartRepository;
    }

    @Override
    public List<FartDTO> getAllFarts() {
        List<Fart> farts = fartRepository.findAll();
        return farts.stream()
                .map(f -> new FartDTO(f.getId(),f.getSmell(),f.getDissipation(),f.getSoundVolume(),f.getOrigin()))
                .collect(Collectors.toList());
    }

    @Override
    public FartDTO getFartByID(Long id) {
        return fartRepository.findById(id)
                .map(FartMapper::entitytoDTO)
                .orElseThrow(() -> new RuntimeException("Fart not found with id: " + id));
    }

    @Override
    public FartDTO saveFart(FartDTO fartDTO) {
        Fart saved = fartRepository.save(FartMapper.DTOtoEntity(fartDTO));
        return FartMapper.entitytoDTO(saved);
    }

    @Override
    public FartDTO updateFart(Long id, FartDTO fartDTO) {
        Optional<Fart> optionalFart = fartRepository.findById(id);
        if (optionalFart.isEmpty()) {
            throw new RuntimeException("Fart not found with id: " + id);
        }

        Fart fartToUpdate = optionalFart.get();
        fartToUpdate.setSmell(fartDTO.smell());
        fartToUpdate.setDissipation(fartDTO.dissipation());
        fartToUpdate.setSoundVolume(fartDTO.soundVolume());
        fartToUpdate.setOrigin(fartDTO.origin());

        Fart updatedFart = fartRepository.save(fartToUpdate);
        return FartMapper.entitytoDTO(updatedFart);
    }

    @Override
    public void deleteFart(Long id) {
        if (!fartRepository.existsById(id)) {
            throw new RuntimeException("Fart not found with id: " + id);
        }
        fartRepository.deleteById(id);
    }
}
