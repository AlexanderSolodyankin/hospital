package com.example.hospital.service.impl;

import com.example.hospital.entity.UserEntity;
import com.example.hospital.exception.exceptions.IllLegalUserArgumentException;
import com.example.hospital.exception.exceptions.InvalidAuthorizeException;
import com.example.hospital.exception.exceptions.UserNotFoundException;
import com.example.hospital.repository.UserRepository;
import com.example.hospital.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthUserServiceImpl implements AuthUserService {
    private UserRepository userRepository;

    @Autowired
    public AuthUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity login(String login, String password, String email)
            throws
            UserNotFoundException,
            InvalidAuthorizeException, IllLegalUserArgumentException {
        UserEntity user = null;
        if ((login.isEmpty() && email.isEmpty()) || password.isEmpty()) {
            throw new IllLegalUserArgumentException(
                    "Необходимые параметры пусты.",
                    HttpStatus.BAD_REQUEST,
                    "Параметры необходимые для входа в систему пусты. Проверьте правильность ввода."
            );
        }

        if (!login.isEmpty()) {
            user = userRepository.findByLogin(login).orElse(null);
        }

        if (Objects.nonNull(email) && !email.isEmpty() && Objects.isNull(user)) {
            user = userRepository.findByEmail(email).orElse(null);
        }

        if (Objects.isNull(user)) {
            throw new UserNotFoundException(
                    "Пользователя с таким логином или почтой не найдено в системе",
                    HttpStatus.UNAUTHORIZED,
                    "Проверьте корректность введенного вами логина возможно вы ввели его не правильно"
            );
        }

        if (!user.getPassword().equals(password)) {
            throw new InvalidAuthorizeException(
                    "Ошибка авторизации: Логин или пароль не совпадают.",
                    HttpStatus.UNAUTHORIZED,
                    "Вы ввели неверный логин или пароль. Убедитесь в правильности ввода данных и повторите попытку."
            );
        }
        return user;
    }
}
