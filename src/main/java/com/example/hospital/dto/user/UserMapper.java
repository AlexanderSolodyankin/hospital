package com.example.hospital.dto.user;

import com.example.hospital.dto.user.request.UserRequestDto;
import com.example.hospital.dto.user.request.UserRequestTransferDto;
import com.example.hospital.dto.user.response.UserResponseDto;
import com.example.hospital.entity.RoleEntity;
import com.example.hospital.entity.UserEntity;
import com.example.hospital.exceptions.ThenComeUpWithException;
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
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserMapper(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    public UserEntity mapModelToEntity(UserRequestDto requestDto){
        UserEntity entity;
        entity = mapModelToEntityFromDataBase(null, requestDto.getLogin(), requestDto.getEmail());
        if(Objects.isNull(entity)){
            entity = new UserEntity();
            entity.setLogin(requestDto.getLogin())
                    .setEmail(requestDto.getEmail())
                    .setPassword(requestDto.getPassword());
        }
        return entity;
    }

    public UserEntity mapModelToEntity(UserResponseDto responseDto){
        UserEntity entity = mapModelToEntityFromDataBase(responseDto.getId(), responseDto.getLogin(), responseDto.getEmail());
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
    public UserEntity mapModelToEntity(UserRequestTransferDto responseDto){
        UserEntity entity = mapModelToEntityFromDataBase(responseDto.getId(), responseDto.getLogin(), responseDto.getEmail());
        if(Objects.isNull(entity)){
            throw new ThenComeUpWithException("Такого пользователя нет в системе");
        }
        return entity;
    }

    private UserEntity mapModelToEntityFromDataBase(Long id, String login, String email){
        if(Objects.nonNull(id)) return userService.getUserById(id);
        if(Objects.nonNull(login)) return userService.getUserByLogin(login);
        if(Objects.nonNull(email)) return userService.getUserByEmail(email);
        return null;
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
        return userEntityList.stream().map(this::mapEntityToRequestModelDto).collect(Collectors.toList());
    }
    public List<UserResponseDto> mapEntityListToResponseModelList(List<UserEntity> userEntityList){
        return userEntityList.stream().map(this::mapEntityToResponseModelDto).collect(Collectors.toList());
    }

    public List<UserEntity> mapResponseModelListToEntityList(List<UserResponseDto> responseDtoList){
        return responseDtoList.stream().map(this::mapModelToEntity).collect(Collectors.toList());
    }
    public List<UserEntity> mapRequestModelListToEntityList(List<UserRequestDto> requestDtoList){
        return requestDtoList.stream().map(this::mapModelToEntity).collect(Collectors.toList());
    }


}
