package com.example.hospital.controllers;

import com.example.hospital.entity.Status;
import com.example.hospital.entity.UserEntity;
import com.example.hospital.service.UserService;
import com.example.hospital.service.impl.AuthorizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final AuthorizeService authorizeService;




    @Autowired
    public UserController(
            UserService userService,
            AuthorizeService authorizeService
    ) {
        this.userService = userService;
        this.authorizeService = authorizeService;
    }

    @GetMapping(value = "/all")
    public List<UserEntity> getAllUsers(){
        return userService.getAllUsersByStatus(Status.ACTIVE);
    }

    @PostMapping(value = "/registration")
    public UserEntity setNewUser(@RequestBody UserEntity entity){
        return userService.register(entity);
    }

    @PostMapping(value = "/log_In")
    public String logIn(@RequestBody UserEntity userEntity){
        return authorizeService.loginUser();
    }

    @DeleteMapping(value = "/userDelete")
    public String deleteUser(@RequestBody UserEntity entity){
        userService.deleteUser(entity);
        return "Удоление пользователя пршло успешно ";
    }


}
