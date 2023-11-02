package com.example.hospital.exception.exceptions;

import com.example.hospital.exception.ExceptionModel;
import org.springframework.http.HttpStatus;

public class InvalidUserCredentialsException extends ExceptionModel {
    public InvalidUserCredentialsException(String message, HttpStatus status, String advice) {
        super(message, status, advice);
    }
}
