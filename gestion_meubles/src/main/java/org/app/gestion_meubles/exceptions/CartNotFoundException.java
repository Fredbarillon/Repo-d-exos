package org.app.gestion_meubles.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class CartNotFoundException extends RuntimeException {
    private HttpStatus httpStatus;
    private LocalDateTime time;

    public CartNotFoundException(String message) {
        super(message);
        this.httpStatus = HttpStatus.NOT_FOUND;
        this.time = LocalDateTime.now();
    }
}
