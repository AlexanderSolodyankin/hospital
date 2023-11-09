package com.example.hospital.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Dto Модель для передачи данных клиенту в теле запроса")
public class UserResponseDto {
    @Schema(description = "ID  пользователя ",
            example = "1",
            required = true,
            maximum = "1")
    private Long id;
    @Schema(description = "Логин пользователя ",
            example = "vitaliystepanov",
            required = true)
    private String login;
    @Schema(
            description = "Почтовый ящик пользователя",
            example = "vitaliystep@mail.ru",
            required = true
    )
    private String email;
    @Schema(
            description = "Дата создания пользователя",
            example = "2023-10-31T02:02:09.711917200",
            required = true
    )
    private LocalDateTime dateCreate;

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

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    public UserResponseDto setDateCreate(LocalDateTime dateCreate) {
        this.dateCreate = dateCreate;
        return this;
    }

}
