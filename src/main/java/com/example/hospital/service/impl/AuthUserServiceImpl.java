package com.example.hospital.service.impl;

import com.example.hospital.entity.UserEntity;
import com.example.hospital.exception.exceptions.UserAuthPasswordIncorrectException;
import com.example.hospital.exception.exceptions.UserNotFoundFromSystemException;
import com.example.hospital.repository.UserRepository;
import com.example.hospital.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public UserEntity register(UserEntity entity) {
        return userRepository.save(entity);
    }

    @Override
    public UserEntity login(String login, String password, String email)
            throws UserAuthPasswordIncorrectException,
            UserNotFoundFromSystemException
    {
        UserEntity user = null;
        if(Objects.nonNull(login) && !login.isEmpty()){
            user = userRepository.findByLogin(login);
        }
        if(Objects.nonNull(email) && !email.isEmpty() && Objects.isNull(user)){
            user = userRepository.findByEmail(email);
        }
        if(Objects.isNull(user)){
            throw new UserNotFoundFromSystemException("Такого пользователя нет в системе.");
        }
        if(Objects.isNull(password) || password.isEmpty()){
            throw  new UserAuthPasswordIncorrectException("Пароль пользователя пустой или отсутствует.");
        }
        if(!user.getPassword().equals(password)){
            throw new UserAuthPasswordIncorrectException("Введенный пароль неверен.");
        }
        return user;
    }
}
