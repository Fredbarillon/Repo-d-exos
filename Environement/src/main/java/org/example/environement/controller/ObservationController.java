package org.example.environement.controller;

import org.example.environement.dto.observation.ObservationDtoReceive;
import org.example.environement.dto.observation.ObservationDtoResponse;
import org.example.environement.service.ObservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/observation")
public class ObservationController {
    private final ObservationService observationService;

    public ObservationController(ObservationService observationService) {
        this.observationService = observationService;
    }

    @GetMapping("/specie/{id}")
    public ResponseEntity<List<ObservationDtoResponse>> getObservationBySpecie(@PathVariable long id) {
        return ResponseEntity.ok(observationService.getBySpecie(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObservationDtoResponse> getById(@PathVariable long id) {
        return ResponseEntity.ok(observationService.get(id));
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<List<ObservationDtoResponse>> getObservationByLocation(@PathVariable String location) {
        return ResponseEntity.ok(observationService.getByLocation(location));
    }

    @PostMapping
    public ResponseEntity<ObservationDtoResponse> create(@RequestBody ObservationDtoReceive dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(observationService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<ObservationDtoResponse>> getAll(@RequestParam int pageSize, @RequestParam int pageNumber) {
        return ResponseEntity.ok(observationService.get(pageSize, pageNumber));
    }
}
