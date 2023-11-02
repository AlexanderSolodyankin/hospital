package com.example.hospital.exception.exceptions;

import com.example.hospital.exception.ExceptionModel;
import org.springframework.http.HttpStatus;

public class IllLegalUserArgumentException extends ExceptionModel {
    public IllLegalUserArgumentException(String message, HttpStatus status, String advice) {
        super(message, status, advice);
    }
}
