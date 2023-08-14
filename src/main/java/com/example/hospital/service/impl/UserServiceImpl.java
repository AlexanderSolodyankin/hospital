package com.example.hospital.service.impl;

import com.example.hospital.entity.RoleEntity;
import com.example.hospital.entity.Status;
import com.example.hospital.entity.UserEntity;
import com.example.hospital.repository.UserRepository;
import com.example.hospital.service.RoleService;
import com.example.hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            RoleService roleService
    ) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity register(UserEntity entity) {
        List<RoleEntity> roleList = new ArrayList<>();
        roleList.add(roleService.getRoleByTitle("USER"));
        entity
                .setActive(Status.NO_ACTIVE)
                .setRoleEntityList(roleList);
        return userRepository.save(entity);
    }

    @Override
    public UserEntity getUserById(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isEmpty()) {
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

    @PreAuthorize("hasRole('USER')")
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

    @PreAuthorize("hasRole('USER')")
    @Override
    public void deleteUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity entity = (UserEntity) userDetails;
        userRepository.save(entity.setActive(Status.DELETED));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @Override
    public void deleteUserByLogin(String login) {
        UserEntity entity = userRepository.findByLogin(login);
        entity.setActive(Status.DELETED);
        register(entity);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @Override
    public void deleteUserByEmail(String email) {
        UserEntity entity = userRepository.findByEmail(email);
        entity.setActive(Status.DELETED);
        register(entity);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
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
