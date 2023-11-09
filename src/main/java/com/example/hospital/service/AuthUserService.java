package com.example.hospital.service;

import com.example.hospital.entity.UserEntity;
import com.example.hospital.exception.exceptions.IllLegalUserArgumentException;
import com.example.hospital.exception.exceptions.InvalidAuthorizeException;
import com.example.hospital.exception.exceptions.UserNotFoundException;

public interface AuthUserService {

    UserEntity login (String login, String password, String email) throws UserNotFoundException, InvalidAuthorizeException, IllLegalUserArgumentException;
}
