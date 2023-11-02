package com.example.hospital.service;

import com.example.hospital.entity.UserEntity;
import com.example.hospital.exception.exceptions.DuplicateLoginValueException;
import com.example.hospital.exception.exceptions.UnknownException;

public interface UserService {

    UserEntity newUser(UserEntity entity) throws DuplicateLoginValueException, UnknownException;
    UserEntity getUserByLogin(String login);
    UserEntity getUserByEmail(String email);

}
