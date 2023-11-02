package com.example.hospital.service.impl;

import com.example.hospital.entity.UserEntity;
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
    {
        UserEntity user = null;
        if(Objects.nonNull(login) && !login.isEmpty()){
            user = userRepository.findByLogin(login);
        }
        if(Objects.nonNull(email) && !email.isEmpty() && Objects.isNull(user)){
            user = userRepository.findByEmail(email);
        }
        return user;
    }
}
