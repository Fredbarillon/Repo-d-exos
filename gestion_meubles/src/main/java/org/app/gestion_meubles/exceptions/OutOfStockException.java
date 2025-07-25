package org.app.gestion_meubles.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class OutOfStockException extends RuntimeException {
    private HttpStatus httpStatus;
    private LocalDateTime time;

    public OutOfStockException(String message) {
        super(message);
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.time = LocalDateTime.now();
    }
}