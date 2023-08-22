package com.example.hospital.service.impl;

import com.example.hospital.dto.user.RoleMapper;
import com.example.hospital.dto.user.request.UserRequestUpdateDto;
import com.example.hospital.entity.RoleEntity;
import com.example.hospital.entity.Status;
import com.example.hospital.entity.UserEntity;
import com.example.hospital.exceptions.ThenComeUpWithException;
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
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final RoleMapper roleMapper;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            RoleService roleService,
            RoleMapper roleMapper) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.roleMapper = roleMapper;
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

    @Override
    public UserEntity updateUser(UserEntity userEntity) {
        return null;
    }

    @Override
    public UserEntity updateUser(UserRequestUpdateDto updateDto)  {
        Optional<UserEntity> entity = userRepository.findById(updateDto.getId());
        if (entity.isEmpty() || entity == null) {
            throw new ThenComeUpWithException("Для изменения пользователя нужно выбрать орегинального пользователя");
        }
        UserEntity originalUser = entity.get();
        originalUser.setActive(updateDto.getStatus())
                .setEmail(updateDto.getEmail())
                .setPassword(updateDto.getPassword())
                .setRoleEntityList(roleMapper.mapModelListToEntityList(updateDto.getRoleLists()));

        return userRepository.save(originalUser);
    }


    @Override
    public void deleteUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity entity = (UserEntity) userDetails;
        userRepository.save(entity.setActive(Status.DELETED));
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
