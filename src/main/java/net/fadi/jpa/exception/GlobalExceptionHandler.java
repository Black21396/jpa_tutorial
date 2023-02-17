package net.fadi.jpa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class GlobalExceptionHandler {


    /*
     this method will call when I create a new object from "CustomNotFoundException"
     , that's mean when the exception occurred
     */
    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundException(CustomNotFoundException ex){

        ErrorResponse err = ErrorResponse
                            .builder()
                            .message(ex.getMessage())
                            .time(LocalDate.now())
                            .build();

        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }
}
