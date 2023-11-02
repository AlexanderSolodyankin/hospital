package com.example.hospital.exception.exceptions;

import com.example.hospital.exception.ExceptionModel;
import org.springframework.http.HttpStatus;

public class UnknownException extends ExceptionModel {
    public UnknownException(String message, HttpStatus status, String advice) {
        super(message, status, advice);
    }
}
