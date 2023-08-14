package com.example.hospital.controllers;

import com.example.hospital.entity.RoleEntity;
import com.example.hospital.entity.Status;
import com.example.hospital.entity.UserEntity;
import com.example.hospital.service.RoleService;
import com.example.hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administrator")
@PreAuthorize("hasRole('ADMIN')")
public class AdministratorController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdministratorController(
            UserService userService,
            RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/get_all_users")
    public List<UserEntity> getAllUsers(){
        return userService.getAll();
    }

    @PostMapping(value = "/get_user_role")
    public List<UserEntity> getAllUserByRole(@RequestParam(name = "role") String role){
        RoleEntity roleEntity = roleService.getRoleByTitle(role);
        return userService.getAllUserByRole(roleEntity);
    }
    @PostMapping(value = "/get_user_status")
    public List<UserEntity> getAllUsersByStatus(@RequestParam(name = "status") Status status){
        return userService.getAllUsersByStatus(status);
    }
    @GetMapping(value = "/update_user_role")
    public UserEntity updateUserRole(@RequestParam(name = "userId") Long userId){
        return userService.getUserById(userId);
    }
    @PutMapping(value = "/update_user_role")
    public UserEntity updateUserRole(@RequestBody UserEntity userEntity){
        return userService.register(userEntity);
    }
    @PostMapping(value = "new_role")
    public RoleEntity newRole(@RequestBody RoleEntity roleEntity){
        return roleService.saveRole(roleEntity);
    }

    @GetMapping(value = "/get_all_roles")
    public List<RoleEntity> getAllRoles(){
        return roleService.getAllRole();
    }

    @PostMapping(value = "/get_all_roles_by_user")
    public List<RoleEntity> getAllRolesByUser(@RequestBody UserEntity userEntity){
        return roleService.getAllRolesByUser(userEntity);
    }
}
