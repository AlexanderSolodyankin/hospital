package com.example.hospital.service;

import com.example.hospital.entity.UserEntity;
import com.example.hospital.exception.exceptions.*;

import java.util.List;

public interface UserService {

    UserEntity register(UserEntity entity) throws DuplicateLoginValueException, UnknownException;
    UserEntity getUserByLogin(String login) throws UserNotFoundException;
    UserEntity getUserByEmail(String email) throws UserNotFoundException;
    UserEntity updateUser(Long id, String oldPassword, String newPassword, Boolean active) throws UserNotFoundException, IllLegalUserArgumentException, InvalidUserCredentialsException;

    List<UserEntity> getAllUsers();

}
