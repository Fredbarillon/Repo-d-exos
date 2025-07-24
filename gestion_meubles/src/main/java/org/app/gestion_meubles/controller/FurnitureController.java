package org.app.gestion_meubles.controller;

import org.app.gestion_meubles.interfaces.FurnitureInterface;
import org.app.gestion_meubles.model.dto.FurnitureDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/furniture")
public class FurnitureController {
    private final FurnitureInterface furnitureService;

    public FurnitureController(FurnitureInterface furnitureService) {
        this.furnitureService = furnitureService;
    }

    @GetMapping
    public ResponseEntity<List<FurnitureDTO>> getAllFurniture() {
        return new ResponseEntity<>(furnitureService.getAllFurnitures(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FurnitureDTO>getFurnitureById(@PathVariable UUID id)throws Exception{
        return new ResponseEntity<>(furnitureService.getFurnitureById(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FurnitureDTO> saveFurniture(@Validated @RequestBody FurnitureDTO furnitureDTO){
        return new ResponseEntity<>(furnitureService.saveFurniture(furnitureDTO),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteFurniture(@PathVariable UUID id)throws Exception{
        furnitureService.deleteFurniture(id);
    }
}
