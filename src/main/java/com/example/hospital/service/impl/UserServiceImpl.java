package com.example.hospital.service.impl;

import com.example.hospital.entity.UserEntity;
import com.example.hospital.exception.exceptions.DuplicateLoginValueException;
import com.example.hospital.exception.exceptions.UnknownException;
import com.example.hospital.repository.UserRepository;
import com.example.hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity newUser(UserEntity entity)
            throws
            DuplicateLoginValueException,
            UnknownException
    {
        try {
            entity = userRepository.save(entity);
        }catch (DataIntegrityViolationException ex){
            throw new DuplicateLoginValueException(
                    "Пользователь под данным логином или почтовым ящиком уже есть в системе.",
                    HttpStatus.CONFLICT,
                    "Такой пользователь уже зарегистрирован в системе попробуйте придумать другой логин."
            );
        }catch (Exception ex){
            throw new UnknownException(
                    ex.getClass().getSimpleName() + ": " + ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Произошла нечаянная ошибка сервера при регистрации пользователя."
            );
        }
        return entity;
    }

    @Override
    public UserEntity getUserByLogin(String login) {
        return null;
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        return null;
    }
}
