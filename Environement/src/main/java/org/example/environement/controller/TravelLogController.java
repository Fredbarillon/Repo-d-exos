package org.example.environement.controller;

import org.example.environement.dto.travellogs.TravelLogDtoResponse;
import org.example.environement.dto.travellogs.TravelLogDtoStat;
import org.example.environement.service.TravelLogsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/travellog")
public class TravelLogController {

    private final TravelLogsService travelLogsService;

    public TravelLogController(TravelLogsService travelLogsService) {
        this.travelLogsService = travelLogsService;
    }

    @GetMapping
    public ResponseEntity<List<TravelLogDtoResponse>> getAllTravellogs (){
        return ResponseEntity.ok(travelLogsService.get(10));
    }

    @GetMapping("/stats/{id}")
    public ResponseEntity<TravelLogDtoStat> getStatFromObseration (@PathVariable long id){
        return ResponseEntity.ok(travelLogsService.getStat(id));
    }

    @GetMapping("/user/{name}")
    public ResponseEntity<Map<String, TravelLogDtoStat>> getTravelStatForUserOnLAstMonth (@PathVariable String name){
        return ResponseEntity.ok(travelLogsService.getStatForUserLastMonth(name));
    }
}
