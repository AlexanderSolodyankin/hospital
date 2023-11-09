package com.example.hospital.exception.exceptions;

import com.example.hospital.exception.ExceptionModel;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends ExceptionModel {
    public UserNotFoundException(String message, HttpStatus status, String advice) {
        super(message, status, advice);
    }
}
