package com.example.hospital.dto.user.request;

import com.example.hospital.dto.user.response.RoleResponseDto;
import com.example.hospital.entity.Status;

import java.util.List;

public class UserRequestTransferDto {
    private Long id;
    private String login;
    private String email;

    public UserRequestTransferDto() {
    }

    public Long getId() {
        return id;
    }

    public UserRequestTransferDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public UserRequestTransferDto setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRequestTransferDto setEmail(String email) {
        this.email = email;
        return this;
    }
}
