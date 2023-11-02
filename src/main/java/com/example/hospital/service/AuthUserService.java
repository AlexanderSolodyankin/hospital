package com.example.hospital.service;

import com.example.hospital.entity.UserEntity;

public interface AuthUserService {

    UserEntity register(UserEntity entity);
    UserEntity login (String login, String password, String email);
}
