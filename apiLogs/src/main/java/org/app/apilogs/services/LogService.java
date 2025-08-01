package org.app.apilogs.services;

import lombok.RequiredArgsConstructor;
import org.app.apilogs.dtos.LogDTORequest;
import org.app.apilogs.dtos.LogDTOResponse;
import org.app.apilogs.interfaces.LogInterface;
import org.app.apilogs.mappers.LogMapper;
import org.app.apilogs.models.documents.Log;
import org.app.apilogs.repository.LogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogService implements LogInterface {

    private final LogRepository logRepository;

    @Override
    public List<LogDTOResponse> getAllLogs() {
        return logRepository.findAll().stream()
                .map(LogMapper::EntityToResponseDTO)
                .toList();
    }

    @Override
    public LogDTOResponse saveLog(LogDTORequest logDTO) {
        Log log = LogMapper.DTOtoEntity(logDTO);
        Log savedLog = logRepository.save(log);
        return LogMapper.EntityToResponseDTO(savedLog);
    }
}