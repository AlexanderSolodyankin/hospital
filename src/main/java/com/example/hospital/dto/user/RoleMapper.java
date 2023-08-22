package com.example.hospital.dto.user;

import com.example.hospital.dto.user.response.RoleResponseDto;
import com.example.hospital.entity.RoleEntity;
import com.example.hospital.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class RoleMapper {
    private RoleService roleService;

    @Autowired
    public RoleMapper(RoleService roleService) {
        this.roleService = roleService;
    }

    public RoleEntity mapModelToEntity(RoleResponseDto responseDto){
        RoleEntity role = roleService.getRoleByTitle(responseDto.getTitleRole());
        if(Objects.isNull(role)) {
            role = new RoleEntity();
            role.setTitle(responseDto.getTitleRole());
        }
        return role;
    }
    public RoleResponseDto mapEntityToModel(RoleEntity entity){
        return new RoleResponseDto().setTitleRole(entity.getTitle());
    }

    public List<RoleResponseDto> mapEntityListToModelDtoList(List<RoleEntity> entityList){
        return entityList.stream().map(this::mapEntityToModel).collect(Collectors.toList());
    }
    public List<RoleEntity> mapModelListToEntityList(List<RoleResponseDto> modelList){
        return modelList.stream().map(this::mapModelToEntity).collect(Collectors.toList());
    }
}
