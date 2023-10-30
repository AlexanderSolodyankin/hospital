package com.example.hospital.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Jwt token: Токен авторизированного пользователя в виде JSON в теле запроса.")
public class JwtResponseDto {
    @Schema(
            description = "Значение JWT без \'Bearer \'",
            example = "eyJhbGciOiJIUzUxMiJ9." +
                    "eyJpYXQiOjE2OTQ1MjkzODgsImV4cCI6MTY5NDUzMjk4OCwic3ViIjoidGVzdF9sb2dpbiJ9." +
                    "o5rmtYicumRNhh8J2FSnYGae8kUFB4aQzwyKtGP6530L6u7Ci7IKAOho79TN__y2I4NI3i-pc_J5u1_4mdWqJw",
            required = true)
    private String jwtValue;

    public JwtResponseDto() {
    }

    public String getJwtValue() {
        return jwtValue;
    }

    public JwtResponseDto setJwtValue(String jwtValue) {
        this.jwtValue = jwtValue;
        return this;
    }
}
