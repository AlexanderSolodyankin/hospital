package com.example.hospital.service;

import com.example.hospital.entity.RoleEntity;
import com.example.hospital.entity.UserEntity;

import java.util.List;

public interface RoleService {
    List<RoleEntity> getAllRole();
    RoleEntity saveRole(RoleEntity role);
    RoleEntity getRoleByTitle(String title);
    List<RoleEntity> getAllRolesByUser(UserEntity userEntity);
}
