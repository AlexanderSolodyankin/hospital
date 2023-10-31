package com.example.hospital.service;

import com.example.hospital.dto.request.UserResponseDto;
import com.example.hospital.entity.UserEntity;
import com.example.hospital.exception.exceptions.UserAuthPasswordIncorrectException;
import com.example.hospital.exception.exceptions.UserNotFoundFromSystemException;

public interface AuthUserService {

    UserEntity register(UserEntity entity);
    UserEntity login (String login, String password, String email)
            throws UserAuthPasswordIncorrectException, UserNotFoundFromSystemException;
}
