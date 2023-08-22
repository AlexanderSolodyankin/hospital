package com.example.hospital.controllers;

import com.example.hospital.dto.user.RoleMapper;
import com.example.hospital.dto.user.UserMapper;
import com.example.hospital.dto.user.request.UserRequestDto;
import com.example.hospital.dto.user.request.UserRequestTransferDto;
import com.example.hospital.dto.user.request.UserRequestUpdateDto;
import com.example.hospital.dto.user.response.RoleResponseDto;
import com.example.hospital.dto.user.response.UserResponseDto;
import com.example.hospital.entity.RoleEntity;
import com.example.hospital.entity.Status;
import com.example.hospital.entity.UserEntity;
import com.example.hospital.service.RoleService;
import com.example.hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administrator")
public class AdministratorController {

    private final UserService userService;
    private final RoleService roleService;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    @Autowired
    public AdministratorController(
            UserService userService,
            RoleService roleService, UserMapper userMapper, RoleMapper roleMapper) {
        this.userService = userService;
        this.roleService = roleService;
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "/get_all_users")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(
                userMapper.mapEntityListToResponseModelList(userService.getAll())
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/get_user_role")
    public ResponseEntity<List<UserResponseDto>> getAllUserByRole(@RequestParam(name = "role") String role) {
        RoleEntity roleEntity = roleService.getRoleByTitle(role);
        return ResponseEntity.ok(
                userMapper.mapEntityListToResponseModelList(
                        userService.getAllUserByRole(roleEntity)
                )
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/get_user_status")
    public ResponseEntity<List<UserResponseDto>> getAllUsersByStatus(@RequestParam(name = "status") Status status) {
        return ResponseEntity.ok(userMapper.mapEntityListToResponseModelList(userService.getAllUsersByStatus(status)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/get_user_id")
    public ResponseEntity<UserResponseDto> updateUserRole(@RequestParam(name = "userId") Long userId) {
        return ResponseEntity.ok(userMapper.mapEntityToResponseModelDto(userService.getUserById(userId)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/update_user_role")
    public ResponseEntity<UserResponseDto> updateUserRole(@RequestBody UserRequestUpdateDto userRequestUpdateDto) {
//        return ResponseEntity.ok(userRequestUpdateDto);
        return ResponseEntity.ok(userMapper.mapEntityToResponseModelDto(
                userService.updateUser(
                userRequestUpdateDto
                        )
                )
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/new_role")
    public ResponseEntity<RoleResponseDto> newRole(@RequestBody RoleResponseDto roleResponseDto) {
        return ResponseEntity.ok(
                roleMapper.mapEntityToModel(
                        roleService.saveRole(
                            roleMapper.mapModelToEntity(roleResponseDto)
                        )
                )
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/get_all_roles")
    public ResponseEntity<List<RoleResponseDto>> getAllRoles() {
        return ResponseEntity.ok(roleMapper.mapEntityListToModelDtoList(roleService.getAllRole()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/get_all_roles_by_user")
    public ResponseEntity<List<RoleResponseDto>> getAllRolesByUser(@RequestBody UserRequestTransferDto requestDto) {
        return ResponseEntity.ok(
                roleMapper.mapEntityListToModelDtoList(
                        roleService.getAllRolesByUser(
                             userMapper.mapModelToEntity(requestDto)
                        )
                )
        );
    }
}
