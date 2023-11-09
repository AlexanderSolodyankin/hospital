package com.example.hospital.validator;

import com.example.hospital.dto.request.UserRequestDto;
import com.example.hospital.exception.exceptions.IllLegalUserArgumentException;
import com.example.hospital.exception.exceptions.InvalidUserCredentialsException;
import org.springframework.http.HttpStatus;

import java.util.Objects;

public class UserValidator {


    public static void userRequestDtoParamValidate(UserRequestDto requestDto)
            throws
            IllLegalUserArgumentException,
            InvalidUserCredentialsException
    {
        userValidateIsNull(requestDto);
        userLoginParamValidate(requestDto.getLogin());
        userEmailParamValidate(requestDto.getEmail());
        userPasswordParamValidate(requestDto.getPassword());

    }

    public static void userValidateIsNull(UserRequestDto requestDto) throws IllLegalUserArgumentException {
        if(Objects.isNull(requestDto))
            throw new IllLegalUserArgumentException(
                    "Данные пользователя не могут быть: null.",
                    HttpStatus.BAD_REQUEST,
                    "Вы скорей всего отправили пустой запрос и не заполнили нужные поля. " +
                            "Попробуйте заполнить поля и попробуйте снова."
            );
    }

    public static void userLoginParamValidate(String login) throws InvalidUserCredentialsException {
        if(Objects.isNull(login) || login.isEmpty())
            throw new InvalidUserCredentialsException(
                    "Логин пользователя не может быть пустым",
                    HttpStatus.BAD_REQUEST,
                    "Скорей всего вы не отправили логин пользователя попробуйте заполнить данное поле и повторите попытку."
            );

        if( login.contains(" ") || login.length() < 5 )
            throw new InvalidUserCredentialsException(
                    "Не корректный логин пользователя.",
                    HttpStatus.BAD_REQUEST,
                    "Логин может содержать латинские буквы. " +
                            "Логин не должен содержать пробелов. " +
                            "Логин не должен состоять как минимум из 5-ти симвалов."
            );
    }

    public static void userEmailParamValidate(String email) throws InvalidUserCredentialsException {
        if (Objects.isNull(email) || email.isEmpty())
            throw new InvalidUserCredentialsException(
                    "Не указан почтовый ящик пользователя.",
                    HttpStatus.BAD_REQUEST,
                    "Почтовый ящик пользователя не указан. Заполните данный параметр и попробуйте снова."
            );
        if(!email.contains("@") || !email.contains("."))
            throw new InvalidUserCredentialsException(
                    "Некорректный адрес почтового ящика пользователя.",
                    HttpStatus.BAD_REQUEST,
                    "Скорей всего вы отправили некорректный почтовый ящик. " +
                            "Адрес почтового ящика должен содержать в себе символ @ " +
                            "и так же содержать домен вашей почты. " +
                            "Пример user@email.com"
            );
    }

    public static void userPasswordParamValidate(String password) throws InvalidUserCredentialsException {
        if(Objects.isNull(password) || password.isEmpty())
            throw new InvalidUserCredentialsException(
                    "Не указа пароль пользователя.",
                    HttpStatus.BAD_REQUEST,
                    "Вы скорей всего не указали пароль из за чего безопасность вашего аккаунта может быть под угрозой. " +
                            "Попробуйте придумать пароль и попробовать попытку."
            );
        if(password.contains(" ") ||
                !password.matches("^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-zA-Z]).+$") ||
                password.length() <= 6
        )
            throw new InvalidUserCredentialsException(
                    "Некорректный пароль пользователя",
                    HttpStatus.BAD_REQUEST,
                    "Составленный вами пароль не соответствует условиям безопасности. " +
                            "Пароль должен быть не короче 6-ти символов. " +
                            "Должен содержать хотя бы одну заглавную букву, " +
                            "хотя бы одну цифру и хотя бы один символ." +
                            "Пароль не ложен содержать пробелов."
            );
    }

}
