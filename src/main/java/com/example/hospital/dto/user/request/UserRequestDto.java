package com.example.hospital.dto.user.request;

public class UserRequestDto {
    private String login;
    private String email;
    private String password;

    public UserRequestDto() {
    }

    public String getLogin() {
        return login;
    }

    public UserRequestDto setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRequestDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRequestDto setPassword(String password) {
        this.password = password;
        return this;
    }
}
