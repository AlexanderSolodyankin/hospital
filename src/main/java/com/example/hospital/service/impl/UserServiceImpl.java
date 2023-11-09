package com.example.hospital.service.impl;

import com.example.hospital.entity.UserEntity;
import com.example.hospital.exception.exceptions.*;
import com.example.hospital.repository.UserRepository;
import com.example.hospital.service.UserService;
import com.sun.nio.sctp.IllegalUnbindException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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
    public UserEntity register(UserEntity entity)
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
    public UserEntity getUserByLogin(String login) throws UserNotFoundException {
        UserEntity user = userRepository.findByLogin(login).orElse(null);
        if(Objects.isNull(user)) throw  new UserNotFoundException(
                "Пользователя с таким логином нет в системе.",
                HttpStatus.NOT_FOUND,
                "Пользователя по лагину: < " + login + " > не найден."

        );
        return user;
    }

    @Override
    public UserEntity getUserByEmail(String email) throws UserNotFoundException {
        UserEntity user = userRepository.findByLogin(email).orElse(null);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException(
                    "Пользователя с такой почтой нет в системе.",
                    HttpStatus.NOT_FOUND,
                    "Пользователя по почтовому адресу: < " + email + " > не найден."
            );
        }
        return user;
    }

    @Override
    public UserEntity updateUser(Long id, String oldPassword, String newPassword, Boolean active)
            throws
            UserNotFoundException,
            IllLegalUserArgumentException, InvalidUserCredentialsException {
        UserEntity user = userRepository.findById(id).orElse(null);
        if(Objects.isNull(user)){
            throw new UserNotFoundException(
                    "Пользователя с данным идентификатором не найден в системе",
                    HttpStatus.NOT_FOUND,
                    "Скорей всего произошла ошибка в передаче вашего идентификатора попробуйте повторить попытку пожже."
            );
        }

        if(Objects.isNull(oldPassword) || oldPassword.isEmpty()){
            throw new IllLegalUserArgumentException(
                    "Обязательное поле пустое.",
                    HttpStatus.UNAUTHORIZED,
                    "Подтвердите ваш пароль для проверки безопасности и уточнее что именно вы хотите изменить свои данные."
            );
        }
        /*
        * Позже добавить проверку через секьюрити
        * */

        if(!user.getPassword().equals(oldPassword)){
            throw new InvalidUserCredentialsException(
                    "Неверный пароль пользователя.",
                    HttpStatus.UNAUTHORIZED,
                    "Вы ввели неверный пароль. Попробуйте перезайти в аккаунт и попробовать снова."
            );
        }
        if(Objects.nonNull(newPassword) && !newPassword.isEmpty()){
            user.setPassword(newPassword);
        }
        if(Objects.nonNull(active)){
            user.setActive(active);
        }

        return userRepository.save(user);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}
