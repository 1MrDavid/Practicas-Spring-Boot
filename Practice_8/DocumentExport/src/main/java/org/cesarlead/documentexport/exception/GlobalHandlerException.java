package org.cesarlead.documentexport.exception;

import lombok.extern.slf4j.Slf4j;
import org.cesarlead.documentexport.dto.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;

@ControllerAdvice
@Slf4j
public class GlobalHandlerException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetail> ResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorDetail errorDetail= new ErrorDetail(
                LocalDateTime.now(),
                ex.getMessage()
        );

        log.error(Arrays.toString(ex.getStackTrace()));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetail);
    }

    @ExceptionHandler(ConnectionNotFoundException.class)
    public ResponseEntity<ErrorDetail> ConnectionNotFoundException(ConnectionNotFoundException ex) {
        ErrorDetail errorDetail = new ErrorDetail(
                LocalDateTime.now(),
                ex.getMessage()
        );

        System.out.println(ex);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetail);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> ErrorHandler(Exception ex) {
        ErrorDetail errorDetail = new ErrorDetail(
                LocalDateTime.now(),
                ex.getMessage()
        );

        System.out.println(ex);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetail);
    }
}
