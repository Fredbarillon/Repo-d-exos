package org.app.apilogs.dtos;

import org.app.apilogs.models.documents.EnumLevel;

import java.time.LocalDateTime;

public record LogDTORequest(String message, String source, LocalDateTime timestamp, EnumLevel level) {
}
