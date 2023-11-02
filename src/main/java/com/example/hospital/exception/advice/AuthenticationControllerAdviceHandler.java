package com.example.hospital.exception.advice;

import com.example.hospital.controllers.v1.AuthenticationController;
import com.example.hospital.exception.ExceptionModel;
import com.example.hospital.exception.ResponseModel;
import com.example.hospital.exception.exceptions.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = {AuthenticationController.class})
public class AuthenticationControllerAdviceHandler {

    @ExceptionHandler(value = ExceptionModel.class)
    public ResponseEntity<ResponseModel> adviceHandlerUserAuthController(ExceptionModel ex){
        ResponseModel responseModel = new ResponseModel(ex);
        return new ResponseEntity<>(responseModel, responseModel.getStatus());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ResponseModel> unknownExceptionFromServer(Exception ex){
        UnknownException unknown = new UnknownException(
                "Не учтенная ошибка сервера.",
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Произошла нечаянная ошибка сервера при регистрации пользователя."
        );
        ResponseModel model = new ResponseModel(unknown);
        model.setException(ex.getClass().getSimpleName());
        return new ResponseEntity<>(model, model.getStatus());
    }



}
