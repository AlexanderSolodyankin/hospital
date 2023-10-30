package com.example.hospital.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO модель передачи данных о пользователе на сервер в тее запроса.")
public class UserRequestDto {
    @Schema(
            description = "Логин пользователя. \n" +
                    "Ограничения: \n" +
                    "Не должен быть пустым или null \n" +
                    "Должен быть уникальным.",
            example = "vitalistepanov",
            required = true
    )
    private String login;
    @Schema(
            description = "Почтовый адрес пользователя. \n" +
                    "Ограничения: \n" +
                    "Не должен быть пустым или null",
            example = "vitalistepanov1997@mail.ru",
            required = true
    )
    private String email;
    @Schema(
            description = "Пароль пользователя. \n" +
                    "Ограничения: \n" +
                    "Не должен быть пустым, null или содержать пробелы \n" +
                    "Должен содержать хотя бы одну заглавную букву, одну цифру, один спец сивол.",
            example = "Qwerty123@",
            required = true
    )
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
