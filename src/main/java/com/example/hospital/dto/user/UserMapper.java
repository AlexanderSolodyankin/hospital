package com.example.hospital.dto.user;

import com.example.hospital.dto.user.request.UserRequestDto;
import com.example.hospital.dto.user.response.UserResponseDto;
import com.example.hospital.entity.RoleEntity;
import com.example.hospital.entity.UserEntity;
import com.example.hospital.service.RoleService;
import com.example.hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public UserMapper(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    public UserEntity mapModelToEntity(UserRequestDto requestDto){
        UserEntity entity = null;
        entity = mapModelToEntityFromDataBase(requestDto);
        if(Objects.isNull(entity)){
            entity = new UserEntity();
            entity.setLogin(requestDto.getLogin())
                    .setEmail(requestDto.getEmail())
                    .setPassword(requestDto.getPassword());
        }
        return entity;
    }

    public UserEntity mapModelToEntity(UserResponseDto responseDto){
        UserEntity entity = mapModelToEntityFromDataBase(responseDto);
        if(Objects.isNull(entity)){
            entity = new UserEntity();
            List<RoleEntity> roleEntities = new ArrayList<>();
            for(String el : responseDto.getRoleList()){
                roleEntities.add(roleService.getRoleByTitle(el));
            }
            entity.setId(responseDto.getId());
            entity.setLogin(responseDto.getLogin())
                    .setEmail(responseDto.getEmail())
                    .setRoleEntityList(roleEntities);
        }
        return entity;
    }

    private UserEntity mapModelToEntityFromDataBase(UserRequestDto requestDto){
        UserEntity entity = userService.getUserByLogin(requestDto.getLogin());
        if(Objects.isNull(entity)) entity = userService.getUserByEmail(requestDto.getEmail());
        return entity;
    }
    private UserEntity mapModelToEntityFromDataBase(UserResponseDto responseDto){
        UserEntity entity = userService.getUserById(responseDto.getId());
        if(Objects.isNull(entity)) entity = userService.getUserByLogin(responseDto.getLogin());
        if(Objects.isNull(entity)) entity = userService.getUserByEmail(responseDto.getEmail());
        return entity;
    }

    public UserResponseDto mapEntityToResponseModelDto(UserEntity entity){
        UserResponseDto responseDto = new UserResponseDto();
        responseDto
                .setId(entity.getId())
                .setLogin(entity.getLogin())
                .setEmail(entity.getEmail())
                .setRoleList(
                        entity.getRoleEntityList()
                                .stream()
                                .map(RoleEntity::getTitle)
                                .collect(Collectors.toList())
                );
        return responseDto;
    }
    public UserRequestDto mapEntityToRequestModelDto(UserEntity entity){
       UserRequestDto  requestDto = new UserRequestDto();
        requestDto
                .setLogin(entity.getLogin())
                .setEmail(entity.getEmail())
                .setPassword(entity.getPassword());
        return requestDto;
    }

    public List<UserRequestDto> mapEntityListToRequestModelList(List<UserEntity> userEntityList){
        List<UserRequestDto> requestDtoList = new ArrayList<>();
        for(UserEntity el : userEntityList){
            requestDtoList.add(mapEntityToRequestModelDto(el));
        }
        return requestDtoList;
    }
    public List<UserResponseDto> mapEntityListToResponseModelList(List<UserEntity> userEntityList){
        List<UserResponseDto> responseDtoList = new ArrayList<>();
        for(UserEntity el : userEntityList){
            responseDtoList.add(mapEntityToResponseModelDto(el));
        }
        return responseDtoList;
    }

    public List<UserEntity> mapResponseModelListToEntityList(List<UserResponseDto> responseDtoList){
        List<UserEntity> entityList = new ArrayList<>();
        for(UserResponseDto el : responseDtoList){
            entityList.add(mapModelToEntity(el));
        }
        return entityList;
    }
    public List<UserEntity> mapRequestModelListToEntityList(List<UserRequestDto> requestDtoList){
        List<UserEntity> entityList = new ArrayList<>();
        for(UserRequestDto el : requestDtoList){
            entityList.add(mapModelToEntity(el));
        }
        return entityList;
    }


}
