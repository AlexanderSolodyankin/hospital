package com.example.hospital.controllers.v1;

import com.example.hospital.dto.response.JwtResponseDto;
import com.example.hospital.exception.ResponseModel;
import com.example.hospital.exception.exceptions.*;
import com.example.hospital.dto.request.UserResponseDto;
import com.example.hospital.dto.response.UserRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

@RestController
@RequestMapping(value = "/v/1/auth")
@Tag(
        name = "Authentication Controller",
        description = "Контроллеры для регистрации и авторизации пользователя"
)
public class AuthenticationController {


    @Operation(
            summary = "Регистрация нового пользователя.",
            description = "Регистрирует пользователя в системе. " +
                    "В теле запроса принимает данные в формате JSON касса UserRequestDTO. ",
            responses = {
                    @ApiResponse(
                            responseCode = "200 OK ",
                            description = " Возвращает информацию о зарегистрированном пользователе в JSON формате. ",
                            content = @Content(schema = @Schema(implementation = UserResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "400 BAD_REQUEST. \n ",
                            description = " Возвращает информацию об ошибке в JSON формате. ",
                            content = @Content(schema = @Schema(implementation = ResponseModel.class))
                    )
            }
    )
    @PostMapping(value = "/register")
    public ResponseEntity<UserResponseDto> registerNewUser(
            @RequestBody UserRequestDto userRequestDto
    ) throws UserRegistrationNullPointException, UserRegisterParameterException {
        /* =======================  переместить в сервисный класс =========================================*/

        if (Objects.isNull(userRequestDto))
            throw new UserRegistrationNullPointException("Данные пользователя не могут быть пустыми");

        if (Objects.isNull(userRequestDto.getLogin()) && userRequestDto.getLogin().isEmpty())
            throw new UserRegisterParameterException("Логин пользователя не может быть пустым");
        /*============================================================================================================*/

        return ResponseEntity.ok(
                new UserResponseDto()
                        .setId(new Random().nextInt(101) + 1l)
                        .setLogin(userRequestDto.getLogin())
                        .setEmail(userRequestDto.getEmail())
                        .setDateCreate(LocalDateTime.now())
        );
    }


    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(
            @RequestParam(required = false) String login,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String password
    )
            throws UserAuthPasswordIncorrectException,
            UserAuthenticationLoginParameterIsNullException, UserAuthenticationParameterIsEmptyException {

        if (Objects.isNull(password) || password.isEmpty())
            throw new UserAuthPasswordIncorrectException("Пароль пользователя не должен быть пустым");
        if (Objects.isNull(email) && Objects.isNull(login))
            throw new UserAuthenticationLoginParameterIsNullException(
                    "Не были переданы Логин или почта при помощи которой можно проверить пользователя системы"
            );
        if (email.isEmpty() && login.isEmpty())
            throw new UserAuthenticationParameterIsEmptyException("Логин и пароль пусты");

        return ResponseEntity.ok(
                new JwtResponseDto()
                        .setJwtValue("Тут должен быть токен пользователя" +
                                " но на данный момент контроллер на стадии разработки")
        );

    }
}
