package com.example.hospital.controllers.v1;

import com.example.hospital.dto.response.JwtResponseDto;
import com.example.hospital.exception.ResponseModel;
import com.example.hospital.dto.request.UserResponseDto;
import com.example.hospital.dto.response.UserRequestDto;
import com.example.hospital.exception.exceptions.DuplicateLoginValueException;
import com.example.hospital.exception.exceptions.IllLegalUserArgumentException;
import com.example.hospital.exception.exceptions.InvalidUserCredentialsException;
import com.example.hospital.exception.exceptions.UnknownException;
import com.example.hospital.mapper.UserMapper;
import com.example.hospital.service.UserService;
import com.example.hospital.validator.UserValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v/1/auth")
@Tag(
        name = "Authentication Controller",
        description = "Контроллеры для регистрации и авторизации пользователя"
)
public class AuthenticationController {

    private UserService userService;

    @Autowired
    public AuthenticationController(
            UserService userService
    ) {
        this.userService = userService;
    }


    @Operation(
            summary = "Регистрация нового пользователя.",
            description = "Регистрирует пользователя в системе. <br/>" +
                    "В теле запроса принимает данные в формате JSON касса UserRequestDTO. ",
            responses = {
                    @ApiResponse(
                            responseCode = "OK ",
                            description = "Возвращает информацию о зарегистрированном пользователе в JSON формате. ",
                            content = @Content(schema = @Schema(implementation = UserResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "FAILED",
                            description = "Возвращает информацию об ошибке в JSON формате. ",
                            content = @Content(schema = @Schema(implementation = ResponseModel.class))
                    )
            }
    )
    @PostMapping(value = "/register")
    public ResponseEntity<UserResponseDto> registerNewUser(
            @RequestBody UserRequestDto userRequestDto
    )
            throws
            IllLegalUserArgumentException,
            InvalidUserCredentialsException,
            DuplicateLoginValueException,
            UnknownException
    {
        UserValidator.userRequestDtoParamValidate(userRequestDto);
        return ResponseEntity.ok(
                UserMapper.mapEntityToResponseDtoModel(
                        userService.newUser(
                                UserMapper.mapDtoModelToEntity(userRequestDto)
                        )
                )
        );
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
                            content = @Content(schema = @Schema(implementation = JwtResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "FAILED",
                            description = "Возвращает информацию об ошибке в JSON формате. ",
                            content = @Content(schema = @Schema(implementation = ResponseModel.class))
                    )
            }
    )
    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(
            @RequestParam(required = false) String login,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String password
    ){
        return ResponseEntity.ok(new UserResponseDto());

    }
}
