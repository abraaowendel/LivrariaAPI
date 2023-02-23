package com.api.livraria.controllers.exceptions;

import com.api.livraria.services.exceptions.DataBaseException;
import com.api.livraria.services.exceptions.ResourceNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> ResourceEntityNotFound(ResourceNotFoundException exception){
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorResponse response = new ErrorResponse();

        response.setStatus(status.value());
        response.setMessage(exception.getMessage());
        response.setTimestamp(Instant.now().toString());

        return ResponseEntity.status(status).body(response);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> IllegalArgumentException(IllegalArgumentException exception){
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErrorResponse response = new ErrorResponse();

        response.setStatus(status.value());
        response.setMessage(exception.getMessage());
        response.setTimestamp(Instant.now().toString());

        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrors> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        ValidationErrors response = new ValidationErrors();
        response.setStatus(status.value());
        response.setTimestamp(Instant.now().toString());
        response.getMessages().addAll(errors);

        return ResponseEntity.status(status).body(response);
    }
    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ErrorResponse> InvalidDateFormatException (DateTimeParseException exception){
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErrorResponse response = new ErrorResponse();

        response.setStatus(status.value());
        response.setMessage("Data inválida.");
        response.setTimestamp(Instant.now().toString());

        return ResponseEntity.status(status).body(response);
    }
    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<ErrorResponse> DataBaseException(DataBaseException exception){
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErrorResponse response = new ErrorResponse();

        response.setStatus(status.value());
        response.setMessage(exception.getMessage());
        response.setTimestamp(Instant.now().toString());

        return ResponseEntity.status(status).body(response);
    }
}