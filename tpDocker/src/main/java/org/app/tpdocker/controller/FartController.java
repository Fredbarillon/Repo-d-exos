package org.app.tpdocker.controller;

import org.app.tpdocker.interfaces.FartInterface;
import org.app.tpdocker.model.dto.FartDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/farts")
public class FartController {

    private final FartInterface fartService;

    public FartController(FartInterface fartService) {
        this.fartService = fartService;
    }

    @GetMapping
    public ResponseEntity<List<FartDTO>> getAllFarts() {
        List<FartDTO> farts = fartService.getAllFarts();
        return new ResponseEntity<>(farts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FartDTO> getFartById(@PathVariable Long id) {
        FartDTO fart = fartService.getFartByID(id);
        return new ResponseEntity<>(fart, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FartDTO> createFart(@RequestBody FartDTO fartDTO) {
        FartDTO created = fartService.saveFart(fartDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FartDTO> updateFart(@PathVariable Long id, @RequestBody FartDTO fartDTO) {
        FartDTO updated = fartService.updateFart(id, fartDTO);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFart(@PathVariable Long id) {
        fartService.deleteFart(id);
        return ResponseEntity.noContent().build();
    }
}
