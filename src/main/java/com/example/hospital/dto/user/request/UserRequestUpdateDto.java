package com.example.hospital.dto.user.request;

import com.example.hospital.dto.user.response.RoleResponseDto;
import com.example.hospital.entity.Status;

import java.util.List;

public class UserRequestUpdateDto {
    private Long id;
    private String email;
    private String password;
    private Status status;
    private List<RoleResponseDto> roleLists;

    public UserRequestUpdateDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<RoleResponseDto> getRoleLists() {
        return roleLists;
    }

    public void setRoleLists(List<RoleResponseDto> roleLists) {
        this.roleLists = roleLists;
    }


}
