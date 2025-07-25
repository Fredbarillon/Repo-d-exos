package org.app.gestion_meubles.model.dto;

import java.time.LocalDateTime;

public record ExceptionDTO(String message, int status, LocalDateTime time) {}
