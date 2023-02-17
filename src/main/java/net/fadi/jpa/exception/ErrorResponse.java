package net.fadi.jpa.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/*
    to customize our exception create this class and put
    how we want send the response when the error occurred
  */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

    private String message;
    // this value is important to tell Frontend Developer that occurred an error
    private final boolean success = false;
    private LocalDate time;
}
