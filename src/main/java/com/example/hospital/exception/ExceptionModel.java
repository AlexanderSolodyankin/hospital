package com.example.hospital.exception;

import org.springframework.http.HttpStatus;
/**
 * Базовая модель исключений нужна как ДТО для передачи фронтенд разработчику
 * */
public abstract class ExceptionModel extends Exception {

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


    public String getExceptionName() {
        return getExceptionSimpleName();
    }

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
    public String getAdvice() {
        return advice;
    }

    public ExceptionModel setAdvice(String advice) {
        this.advice = advice;
        return this;
    }
}
