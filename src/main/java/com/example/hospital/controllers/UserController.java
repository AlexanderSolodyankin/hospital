package com.example.hospital.controllers;

import com.example.hospital.dto.user.UserMapper;
import com.example.hospital.dto.user.request.UserRequestDto;
import com.example.hospital.dto.user.response.UserResponseDto;
import com.example.hospital.entity.Status;
import com.example.hospital.exceptions.ExceptionOfPassedValues;
import com.example.hospital.exceptions.ThenComeUpWithException;
import com.example.hospital.exceptions.users_related_exception.UserInformationException;
import com.example.hospital.service.AuthorizeService;
import com.example.hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final AuthorizeService authorizeService;

    @Autowired
    public UserController(
            UserService userService,
            UserMapper userMapper,
            AuthorizeService authorizeService
    ) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.authorizeService = authorizeService;
    }
    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    " 'HEAD_DOCTOR'," +
                    "'DOCTOR'," +
                    "'NURSE'," +
                    "'ADMIN'," +
                    "'REGISTRY'" +
                    ")"
    )
    @GetMapping(value = "/all")
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        List<UserResponseDto> resultList = userMapper.mapEntityListToResponseModelList(
                userService.getAllUsersByStatus(Status.ACTIVE));
        if(Objects.isNull(resultList) || resultList.isEmpty()){
            throw new UserInformationException("Активных пользователей в системе не обнаруженно.");
        }
        return ResponseEntity.ok(resultList);
    }

    @PostMapping(value = "/registration")
    public ResponseEntity<String> setNewUser(@RequestBody UserRequestDto requestDto){
        if(Objects.isNull(requestDto)){
            throw new ExceptionOfPassedValues("Передоваеммые зночения пользователя не могут быть пустыми");
        }
        requestDto.setPassword(authorizeService.passwordEncode(requestDto.getPassword())); // Надо исправить Очень дерьмовый вариант
        UserResponseDto result = userMapper
                .mapEntityToResponseModelDto(
                        userService.register(
                                userMapper.mapModelToEntity(requestDto)
                        )
                );
        if(Objects.isNull(result))
            throw new UserInformationException("Во время регистрации пользователя произошла ошибка: регистрация не получилась");
        return ResponseEntity.ok("code_activation");
    }

    @PostMapping(value = "/log_In")
    public ResponseEntity<String> logIn(@RequestBody UserRequestDto requestDto){
            return ResponseEntity.ok(authorizeService.logInUser(requestDto));
    }
    @GetMapping(value = "/log_In")
    public ResponseEntity<String> logIn(){
        return ResponseEntity.ok(authorizeService.logInUser());
    }


    @PreAuthorize("hasRole('USER')")
    @DeleteMapping(value = "/userDelete")
    public ResponseEntity<String> deleteUser(
            @RequestParam(name = "userPassword") String userPassword
            ){
        if(authorizeService.passwordValidats(userPassword)){
            userService.deleteUser();
            return ResponseEntity.ok("Удоление пользователя прошло успешно.");
        }
        throw new ThenComeUpWithException("Что то при удолении пользоватея пошло не так");
    }


}
