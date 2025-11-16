package com.cesarlead.DAProgress.exception;

import com.cesarlead.DAProgress.dto.ErrorResponseDTO;
import com.cesarlead.DAProgress.exception.DuplicateResourceException;
import com.cesarlead.DAProgress.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(DuplicateResourceException.class)
  public ResponseEntity<ErrorResponseDTO> DuplicateResourceExceptionHandler(
      DuplicateResourceException ex) {
    ErrorResponseDTO errorResponse = new ErrorResponseDTO(
        ex.getMessage(),
        LocalDateTime.now());

    return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorResponseDTO> ResourceNotFoundHandler(
      ResourceNotFoundException ex) {
    ErrorResponseDTO errorResponse = new ErrorResponseDTO(
        ex.getMessage(),
        LocalDateTime.now());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
  }

  @ExceptionHandler(ExternalServiceException.class)
  public ResponseEntity<ErrorResponseDTO> handleExternalServiceException(ExternalServiceException ex) {
    logger.warn("Fallo de comunicación con servicio externo");
    ErrorResponseDTO errorResponse = new ErrorResponseDTO(
        ex.getMessage(),
        LocalDateTime.now()
    );

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_GATEWAY);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponseDTO> ErrorHandler(Exception ex) {
    ErrorResponseDTO errorResponse = new ErrorResponseDTO(
        ex.getMessage(),
        LocalDateTime.now());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
  }

}
