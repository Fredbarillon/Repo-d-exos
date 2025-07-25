package org.app.gestion_meubles.exceptions;

import org.app.gestion_meubles.model.dto.ExceptionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleConflictException(CartNotFoundException ex) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(ex.getMessage(), ex.getHttpStatus().value(), ex.getTime());
        return new ResponseEntity<>(exceptionDTO, ex.getHttpStatus());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(CartItemNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleConflictException(CartItemNotFoundException ex) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(ex.getMessage(), ex.getHttpStatus().value(), ex.getTime());
        return new ResponseEntity<>(exceptionDTO, ex.getHttpStatus());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(FurnitureNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleConflictException(FurnitureNotFoundException ex) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(ex.getMessage(), ex.getHttpStatus().value(), ex.getTime());
        return new ResponseEntity<>(exceptionDTO, ex.getHttpStatus());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(OutOfStockException.class)
    public ResponseEntity<ExceptionDTO> handleConflictException(OutOfStockException ex) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(ex.getMessage(), ex.getHttpStatus().value(), ex.getTime());
        return new ResponseEntity<>(exceptionDTO, ex.getHttpStatus());
    }
}
