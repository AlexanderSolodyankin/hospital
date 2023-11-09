package com.example.hospital.exception.exceptions;

import com.example.hospital.exception.ExceptionModel;
import org.springframework.http.HttpStatus;
/** Исключения Разработки приложения
 *  Метка, что модуль находиться на стадии разработки.
 * */
public class DevelopApplicationException extends ExceptionModel {

    public DevelopApplicationException() {
        super("Модуль не работает.",
                HttpStatus.INTERNAL_SERVER_ERROR,
                " Данный модуль находиться на стадии разработки."
        );
    }

    public DevelopApplicationException(String message, HttpStatus status, String advice) {
        super(message, status, advice);
    }
}
