package com.example.hospital.exception.exceptions;

import com.example.hospital.exception.ExceptionModel;
import org.springframework.http.HttpStatus;

public class InvalidAuthorizeException extends ExceptionModel {
    public InvalidAuthorizeException(String message, HttpStatus status, String advice) {
        super(message, status, advice);
    }
}
