package com.example.hospital.controllers.Advice;

import com.example.hospital.controllers.AdministratorController;
import com.example.hospital.controllers.UserController;
import com.example.hospital.exceptions.ExceptionOfPassedValues;
import com.example.hospital.exceptions.ThenComeUpWithException;
import com.example.hospital.exceptions.users_related_exception.UserAuthenticationException;
import com.example.hospital.exceptions.users_related_exception.UserInformationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;


@RestControllerAdvice(basePackageClasses = {AdministratorController.class, UserController.class})
public class HospitalControllersAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ThenComeUpWithException.class)
    public String DataProcessingException(ThenComeUpWithException ex) {
        return "Ошибка Для которой нужно придумать нормальную обработку: " + ex.getLocalizedMessage();
    }
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = AccessDeniedException.class)
    public String authorizeException(AccessDeniedException ex) {
        return "Ошибка авторизации пользователя: " + ex.getLocalizedMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public String handExceptions(IllegalArgumentException ex) {
        throw new ExceptionOfPassedValues( "Ошибка передаваемых параметров: " + ex.getLocalizedMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = UserInformationException.class)
    public String storedDataException(UserInformationException ex) {
        return "Ошибка Хранимых данных: " + ex.getLocalizedMessage();
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = UserAuthenticationException.class)
    public String authExceptions(UserAuthenticationException ex) {
        if( Objects.isNull(ex.getMessage()) || ex.getMessage().isEmpty()) {
            return "Неверный логин или пароль";
        }
        return ex.getMessage();
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ExceptionOfPassedValues.class)
    public String passedParametersException(ExceptionOfPassedValues ex) {
        return "Ошибка передоваемых параметров данных: " + ex.getLocalizedMessage();
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public String DataProcessingException(RuntimeException ex) {
        return "Ошибка обработки данных: " + ex.getLocalizedMessage();
    }



    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = Exception.class)
    public String DataProcessingException(Exception ex) {
        throw new ThenComeUpWithException(ex.getClass().getName() + ": " + ex.getMessage() );
    }


}
