package com.example.hospital.service.impl;

import com.example.hospital.entity.RoleEntity;
import com.example.hospital.entity.Status;
import com.example.hospital.entity.UserEntity;
import com.example.hospital.repository.UserRepository;
import com.example.hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity register(UserEntity entity) {
        entity.setActive(Status.NO_ACTIVE);
        return userRepository.save(entity);
    }

    @Override
    public UserEntity getUserById(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (Objects.isNull(user) || user.isEmpty()) {
            throw new IllegalArgumentException("Такого пользователя нет в системе");
        }
        return user.get();
    }

    @Override
    public UserEntity getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserEntity> getAllUsersByStatus(Status status) {
        return userRepository.findAllByActive(status);
    }

    @Override
    public UserEntity getUserByActivitiesCode(String activitiesCode) {

        return userRepository.findByCodeActivities(activitiesCode);
    }

    @Override
    public List<UserEntity> getAllUserByRole(RoleEntity roleEntity) {
        return userRepository.findAllByRoleEntityList(roleEntity);
    }

    @Override
    public UserEntity updateUser(UserEntity userEntity) {
        return null;
    }

    @Override
    public UserEntity updateUser(UserEntity userEntity, UserEntity newUserEntity) throws Exception {
        Optional<UserEntity> entity = userRepository.findById(userEntity.getId());
        if (entity.isEmpty()) {
            throw new Exception("Для изменения пользователя нужно выбрать орегинального пользователя");
        }
        UserEntity originalUser = entity.get();
        if (!originalUser.getLogin().equals(newUserEntity.getLogin()) &&
                !originalUser.getId().equals(newUserEntity.getId()) &&
                !originalUser.getEmail().equals(newUserEntity.getEmail()) &&
                !originalUser.getDateCreate().equals(newUserEntity.getDateCreate())
        ) throw new Exception("Вы не можите менять у пользователя только пароль, статус или роль");
        return register(newUserEntity);
    }

    @Override
    public void deleteUser(UserEntity userEntity) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(userEntity.getId());
        if (optionalUserEntity.isEmpty()) {
            System.out.println("Пользователь не получился");
        }
        UserEntity entity = optionalUserEntity.get();
        if (!entity.getEmail().equals(userEntity.getEmail()) &&
                !entity.getLogin().equals(userEntity.getLogin()) &&
                !entity.getPassword().equals(userEntity.getPassword())
        ) throw new IllegalArgumentException("Удоление не удалось");
        entity.setActive(Status.DELETED);
        register(entity);

    }

    @Override
    public void deleteUserByLogin(String login) {
        UserEntity entity = userRepository.findByLogin(login);
        entity.setActive(Status.DELETED);
        register(entity);
    }

    @Override
    public void deleteUserByEmail(String email) {
        UserEntity entity = userRepository.findByEmail(email);
        entity.setActive(Status.DELETED);
        register(entity);
    }

    @Override
    public void deleteUserById(Long id) throws Exception {
        Optional<UserEntity> entity = userRepository.findById(id);
        if (entity.isEmpty()) {
            throw new Exception("Пользователя с таким ID не существуетс УДАЛЕНИЕ НЕ ВОЗМОЖНО!!!");
        }
        UserEntity userEntity = entity.get();
        userEntity.setActive(Status.DELETED);
        register(userEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByLogin(username);
    }
}
