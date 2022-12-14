package com.growdev.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ControllerException {
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<StandardError> entityNotFoundIdException(ResourceNotFoundException e, HttpServletRequest request) {
    StandardError error = new StandardError();
    HttpStatus statusError = HttpStatus.NOT_FOUND;
    error.setTimestamp(Instant.now());
    error.setStatus(statusError.value());
    error.setMessage(e.getMessage());
    error.setPath(request.getRequestURI());
    error.setError("Recurso não encontrado");
    return ResponseEntity.status(statusError).body(error);
  }

  @ExceptionHandler(DatabaseException.class)
  public ResponseEntity<StandardError> databaseException(DatabaseException e, HttpServletRequest request) {
    StandardError error = new StandardError();
    HttpStatus statusError = HttpStatus.BAD_REQUEST;
    error.setTimestamp(Instant.now());
    error.setStatus(statusError.value());
    error.setMessage(e.getMessage());
    error.setPath(request.getRequestURI());
    error.setError("Database exception");
    return ResponseEntity.status(statusError).body(error);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ValidationErrors> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
    ValidationErrors error = new ValidationErrors();
    HttpStatus statusError = HttpStatus.UNPROCESSABLE_ENTITY;//422
    error.setTimestamp(Instant.now());
    error.setStatus(statusError.value());
    error.setMessage(e.getMessage());
    error.setPath(request.getRequestURI());
    error.setError("Validation exception");
    //tira todos os erros de validação do campos (lista de erro)
    //Lista de erro
    for (FieldError f : e.getBindingResult().getFieldErrors()) {
      error.addError(f.getField(), f.getDefaultMessage());
    }
    return ResponseEntity.status(statusError).body(error);
  }
}
