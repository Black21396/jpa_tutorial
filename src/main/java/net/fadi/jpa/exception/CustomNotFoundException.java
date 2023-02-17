package net.fadi.jpa.exception;


import lombok.Data;

/*
    *this is customize class to handle the Exception ("NotFoundException")
    * all customize class should extends "RunTimeException"
 */
@Data
public class CustomNotFoundException extends RuntimeException{
    private String message;
    public CustomNotFoundException(String message){
                super(message);
                this.message = message;
    }
}
