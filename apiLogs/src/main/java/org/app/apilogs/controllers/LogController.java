package org.app.apilogs.controller;

import lombok.RequiredArgsConstructor;
import org.app.apilogs.dtos.LogDTORequest;
import org.app.apilogs.dtos.LogDTOResponse;
import org.app.apilogs.interfaces.LogInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
public class LogController {

    private final LogInterface logService;
    @Value("${server.port}")
    private String serverPort;

    @GetMapping
    public ResponseEntity<List<LogDTOResponse>> getAllLogs() {
        List<LogDTOResponse> logs = logService.getAllLogs();
        return new ResponseEntity<>(logs, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LogDTOResponse> saveLog(@RequestBody LogDTORequest logDTO)throws URISyntaxException {
        LogDTOResponse savedLog = logService.saveLog(logDTO);
        URI createdLocation = new URI(
                String.format("http://localhost:%s/api/logs/%s", serverPort, savedLog.id())
        );
        return ResponseEntity.created(createdLocation).build();
    }
}
