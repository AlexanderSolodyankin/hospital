package com.example.hospital.exception;

import org.springframework.http.HttpStatus;

public interface PrintableMessageException {

    String getExceptionName();
    HttpStatus getHttpStatus();
    String getMessage();
    String getAdvice();
}
