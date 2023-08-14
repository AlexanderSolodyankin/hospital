package com.example.hospital.service.impl;

import com.example.hospital.entity.RoleEntity;
import com.example.hospital.entity.UserEntity;
import com.example.hospital.repository.RoleRepository;
import com.example.hospital.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RoleEntity> getAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public RoleEntity saveRole(RoleEntity role) {
        return roleRepository.save(role);
    }

    @Override
    public RoleEntity getRoleByTitle(String title) {
        return roleRepository.findByTitleIgnoreCase(title);
    }

    @Override
    public List<RoleEntity> getAllRolesByUser(UserEntity userEntity) {
        return roleRepository.findByUserEntityList(userEntity);
    }
}
