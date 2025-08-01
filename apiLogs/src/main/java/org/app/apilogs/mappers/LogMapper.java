package org.app.apilogs.mappers;

import org.app.apilogs.dtos.LogDTORequest;
import org.app.apilogs.dtos.LogDTOResponse;
import org.app.apilogs.models.documents.Log;

public class LogMapper {
    public static Log DTOtoEntity(LogDTORequest dto) {
        return Log.builder()
                .message(dto.message())
                .source(dto.source())
                .timestamp(dto.timestamp())
                .level(dto.level())
                .build();
    }

    public static LogDTOResponse EntityToResponseDTO(Log log) {
        return new LogDTOResponse(
                log.getId(),
                log.getMessage(),
                log.getSource(),
                log.getTimestamp(),
                log.getLevel()
        );
    }


}
