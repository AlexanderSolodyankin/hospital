package com.example.hospital.exception.advice;

import com.example.hospital.controllers.v1.AuthenticationController;
import com.example.hospital.exception.ResponseModel;
import com.example.hospital.exception.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = {AuthenticationController.class})
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AuthenticationControllerAdviceHandler {

    @ExceptionHandler(value = UserRegistrationNullPointException.class)
    public ResponseEntity<ResponseModel> adviceHandelUserRegistrationNullPointException(UserRegistrationNullPointException ex) {
        return ResponseEntity.badRequest().body(
                new ResponseModel(
                        ex,
                        HttpStatus.BAD_REQUEST,
                        "Вы скорей всего не создали модель JSON параметра и не передали его на сервер." +
                                " Проверьте тело своего запроса и попробуйте снова.")
        );
    }
    @ExceptionHandler(value = UserRegisterParameterException.class)
    public ResponseEntity<ResponseModel> adviceHandelUserRegisterParameterException(UserRegisterParameterException ex) {
        return ResponseEntity.badRequest().body(
                new ResponseModel(
                        ex,
                        HttpStatus.BAD_REQUEST,
                        "Один или несколько параметров вашей JSON модели пусты" +
                                " проверьте корректность отправленных вами данных и попробуйте снова.")
        );
    }
    @ExceptionHandler(value = UserAuthenticationLoginParameterIsNullException.class)
    public ResponseEntity<ResponseModel>
    adviceHandelUserAuthenticationLoginParameterIsNullException(UserAuthenticationLoginParameterIsNullException ex) {
        return ResponseEntity.badRequest().body(
                new ResponseModel(
                        ex,
                        HttpStatus.BAD_REQUEST,
                        "Вы не отправили нужные параметры для проверки пользователя в системе. " +
                                "Попробуйте ввести или логин или почту и попробуйте снова.")
        );
    }
    @ExceptionHandler(value = UserAuthenticationParameterIsEmptyException.class)
    public ResponseEntity<ResponseModel>
    adviceHandelUserAuthenticationParameterIsEmptyException(UserAuthenticationParameterIsEmptyException ex) {
        return ResponseEntity.badRequest().body(
                new ResponseModel(
                        ex,
                        HttpStatus.BAD_REQUEST,
                        "Отправленные вами параметры пусты. Для проверки пользователя нужен или логин или почта." +
                                "Заполните один из этих параметров и попробуйте снова.")
        );
    }
    @ExceptionHandler(value = UserAuthPasswordIncorrectException.class)
    public ResponseEntity<ResponseModel>
    adviceHandelUserAuthenticationParameterIsEmptyException(UserAuthPasswordIncorrectException ex) {
        return ResponseEntity.badRequest().body(
                new ResponseModel(
                        ex,
                        HttpStatus.BAD_REQUEST,
                        "Отправленный вами пароль пустой что делает не возможным дальнейшею авторизацию. " +
                                "Попробуйте ввести пароль еще раз и повторить запрос.")
        );
    }


}
