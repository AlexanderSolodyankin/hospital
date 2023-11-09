package com.example.hospital.exception.advice;

import com.example.hospital.controllers.v1.AuthenticationController;
import com.example.hospital.exception.ExceptionModel;
import com.example.hospital.exception.ResponseExceptionModel;
import com.example.hospital.exception.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = {AuthenticationController.class})
public class AuthenticationControllerAdviceHandler {

    @ExceptionHandler(value = ExceptionModel.class)
    public ResponseEntity<ResponseExceptionModel> adviceHandlerUserAuthController(ExceptionModel ex){
        ResponseExceptionModel responseExceptionModel = new ResponseExceptionModel(ex);
        return new ResponseEntity<>(responseExceptionModel, responseExceptionModel.getStatus());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ResponseExceptionModel> unknownExceptionFromServer(Exception ex){
        UnknownException unknown = new UnknownException(
                "Не учтенная ошибка сервера.",
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Произошла нечаянная ошибка сервера при регистрации пользователя."
        );
        ResponseExceptionModel model = new ResponseExceptionModel(unknown);
        model.setException(ex.getClass().getSimpleName());
        return new ResponseEntity<>(model, model.getStatus());
    }



}
