package com.example.hospital.mapper;

import com.example.hospital.dto.request.UserResponseDto;
import com.example.hospital.dto.response.UserRequestDto;
import com.example.hospital.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserEntity mapDtoModelToEntity(UserResponseDto responseDto) {
        UserEntity user = new UserEntity();
        user
           .setLogin(responseDto.getLogin())
           .setDataCreate(responseDto.getDateCreate())
           .setEmail(responseDto.getEmail())
           .setId(responseDto.getId());

        return user;
    }
    public static UserEntity mapDtoModelToEntity(UserRequestDto requestDto) {
        UserEntity user = new UserEntity();
        user
                .setLogin(requestDto.getLogin())
                .setEmail(requestDto.getEmail())
                .setPassword(requestDto.getPassword());

        return user;
    }

    public static UserResponseDto mapEntityToResponseDtoModel(UserEntity entity){
        UserResponseDto responseDto = new UserResponseDto();
        responseDto
                .setId(entity.getId())
                .setLogin(entity.getLogin())
                .setEmail(entity.getEmail())
                .setDateCreate(entity.getDataCreate());

        return responseDto;
    }

    public static UserRequestDto mapEntityToRequestDtoModel(UserEntity entity){
        UserRequestDto requestDto = new UserRequestDto();
        requestDto
                .setEmail(entity.getEmail())
                .setLogin(entity.getLogin())
                .setPassword(entity.getPassword());

        return requestDto;
    }

    public static List<UserEntity> mapListResponseDtoModelToEntityList(List<UserResponseDto> responseDtoList){
        return responseDtoList
                            .stream()
                            .map(UserMapper::mapDtoModelToEntity)
                            .collect(Collectors.toList());
    }
    public static List<UserEntity> mapListRequestDtoModelToEntityList(List<UserRequestDto> requestDtoList) {
        return requestDtoList
                .stream()
                .map(UserMapper::mapDtoModelToEntity)
                .collect(Collectors.toList());
    }

    public static List<UserResponseDto> mapListEntityToResponseDtoModelList(List<UserEntity> entityList){
        return entityList
                .stream()
                .map(UserMapper::mapEntityToResponseDtoModel)
                .collect(Collectors.toList());
    }
    public static List<UserRequestDto> mapListEntityToRequestDtoModelList(List<UserEntity> entityList){
        return entityList
                .stream()
                .map(UserMapper::mapEntityToRequestDtoModel)
                .collect(Collectors.toList());
    }
}
