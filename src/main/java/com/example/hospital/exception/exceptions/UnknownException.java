package com.example.hospital.exception.exceptions;

import com.example.hospital.exception.ExceptionModel;
import org.springframework.http.HttpStatus;

/**
 *  Не известная ошибка
 *  Отлов не учтенных исключений для последующей обработки
 * */
public class UnknownException extends ExceptionModel {
    public UnknownException(String message, HttpStatus status, String advice) {
        super(message, status, advice);
    }
}
