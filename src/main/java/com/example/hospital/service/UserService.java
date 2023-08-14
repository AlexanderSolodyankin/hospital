package com.example.hospital.service;

import com.example.hospital.entity.RoleEntity;
import com.example.hospital.entity.Status;
import com.example.hospital.entity.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> getAll();
    UserEntity register(UserEntity entity);

    UserEntity getUserById(Long id);

    UserEntity getUserByLogin(String login);
    UserEntity getUserByEmail(String email);
    List<UserEntity> getAllUsersByStatus(Status status);
    UserEntity getUserByActivitiesCode(String activitiesCode);
    List<UserEntity> getAllUserByRole(RoleEntity roleEntity);
    UserEntity updateUser(UserEntity userEntity);
    UserEntity updateUser(UserEntity userEntity, UserEntity newUserEntity) throws Exception;
    void deleteUser(UserEntity userEntity) ;
    void deleteUserByLogin(String login);
    void deleteUserByEmail(String email);
    void deleteUserById(Long id) throws Exception;


}
