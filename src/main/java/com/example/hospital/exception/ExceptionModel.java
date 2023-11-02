package com.example.hospital.exception;

import org.springframework.http.HttpStatus;

public abstract class ExceptionModel extends Exception implements PrintableMessageException{

    protected HttpStatus status;
    protected String advice;

    public ExceptionModel(
            String message,
            HttpStatus status,
            String advice
    ) {
        super(message);
        this.status = status;
        this.advice = advice;
    }

    @Override
    public String getExceptionName() {
        return getExceptionSimpleName();
    }

    @Override
    public HttpStatus getHttpStatus() {
        return getHttpStatus();
    }

    public String getExceptionSimpleName(){
        return this.getClass().getSimpleName();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public ExceptionModel setStatus(HttpStatus status) {
        this.status = status;
        return this;
    }

    @Override
    public String getAdvice() {
        return advice;
    }

    public ExceptionModel setAdvice(String advice) {
        this.advice = advice;
        return this;
    }
}
