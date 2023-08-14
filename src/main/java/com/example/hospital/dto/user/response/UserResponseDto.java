package com.example.hospital.dto.user.response;

import java.util.List;

public class UserResponseDto {
    private Long id;
    private String login;
    private String email;
    private List<String> roleList;

    public UserResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public UserResponseDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public UserResponseDto setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserResponseDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public List<String> getRoleList() {
        return roleList;
    }

    public UserResponseDto setRoleList(List<String> roleList) {
        this.roleList = roleList;
        return this;
    }
}
