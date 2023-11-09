package com.example.hospital.controllers.v1;

import com.example.hospital.dto.response.UserResponseDto;
import com.example.hospital.exception.ResponseExceptionModel;
import com.example.hospital.exception.exceptions.IllLegalUserArgumentException;
import com.example.hospital.exception.exceptions.InvalidAuthorizeException;
import com.example.hospital.exception.exceptions.UserNotFoundException;
import com.example.hospital.mapper.UserMapper;
import com.example.hospital.service.AuthUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/app/v/1/auth")
@Tag(
        name = "Authentication Controller",
        description = "Контроллеры для регистрации и авторизации пользователя"
)
public class AuthenticationController {

    private final AuthUserService authUserService;

    @Autowired
    public AuthenticationController(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @Operation(
            summary = "Авторизация пользователя.",
            description = "Инициирует вход пользователя в систему. <br/>" +
                    "В теле запроса принимает данные в виде отдельных параметров. <br/>" +
                    "Ограничения: <br/>" +
                    "Логин или почта не должны быть пустыми. <br/>" +
                    "Пароль не должен быть пустым.",
            responses = {
                    @ApiResponse(
                            responseCode = "OK ",
                            description = " Возвращает токен зарегистрированного пользователя в JSON формате. ",
                            content = @Content(schema = @Schema(implementation = UserResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "FAILED",
                            description = "Возвращает информацию об ошибке в JSON формате. ",
                            content = @Content(schema = @Schema(implementation = ResponseExceptionModel.class))
                    )
            }
    )
    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(
            @RequestParam(required = false) String login,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String password
    ) throws
            UserNotFoundException,
            InvalidAuthorizeException,
            IllLegalUserArgumentException
    {
        return ResponseEntity.ok(
                UserMapper.mapEntityToResponseDtoModel(
                        authUserService.login(login, password,email)
                )
        );

    }
}
