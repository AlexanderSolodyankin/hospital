package com.example.hospital.controllers.v1;

import com.example.hospital.dto.request.UserRequestDto;
import com.example.hospital.dto.response.UserResponseDto;
import com.example.hospital.exception.ResponseExceptionModel;
import com.example.hospital.exception.exceptions.*;
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

import java.util.List;

@RestController
@RequestMapping(value = "/app/v/1/users")
@Tag(
        name = "User Controller",
        description = "Эндпойнты данного контроллера предназначены для манипуляции над пользователями."
)
public class UsersController {

    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
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
                            content = @Content(schema = @Schema(implementation = ResponseExceptionModel.class))
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
                        userService.register(
                                UserMapper.mapDtoModelToEntity(userRequestDto)
                        )
                )
        );
    }

    @Operation(
            summary = "Список пользователей в системе",
            description = "Возвращает список всех пользователей зарегистрированных.",
            responses = {
                    @ApiResponse(
                            responseCode = "OK ",
                            description = " Возвращает список зарегистрированных пользователей в JSON формате. ",
                            content = @Content(schema = @Schema(implementation = UserResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "FAILED",
                            description = "Возвращает информацию об ошибке в JSON формате. ",
                            content = @Content(schema = @Schema(implementation = ResponseExceptionModel.class))
                    )
            }
    )
    @GetMapping(value = "/get_all")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(UserMapper.mapListEntityToResponseDtoModelList(userService.getAllUsers()));
    }

    @Operation(
            summary = "Поиск пользователя по логину",
            responses = {
                    @ApiResponse(
                            responseCode = "OK ",
                            description = "Возвращает пользователя найденного по логину в JSON формате. ",
                            content = @Content(schema = @Schema(implementation = UserResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "FAILED",
                            description = "Возвращает информацию об ошибке в JSON формате. ",
                            content = @Content(schema = @Schema(implementation = ResponseExceptionModel.class))
                    )
            }
    )
    @PostMapping("/get_by_login")
    public ResponseEntity<UserResponseDto> getUserByLogin(@RequestParam String login) throws UserNotFoundException {
        return ResponseEntity.ok(UserMapper.mapEntityToResponseDtoModel(userService.getUserByLogin(login)));
    }

    @Operation(
            summary = "Поиск пользователя по почтовому адресу",
            responses = {
                    @ApiResponse(
                            responseCode = "OK ",
                            description = "Возвращает пользователя найденного по почтовому адресу в JSON формате. ",
                            content = @Content(schema = @Schema(implementation = UserResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "FAILED",
                            description = "Возвращает информацию об ошибке в JSON формате. ",
                            content = @Content(schema = @Schema(implementation = ResponseExceptionModel.class))
                    )
            }
    )
    @PostMapping("/get_by_email")
    public ResponseEntity<UserResponseDto> getUserByEmail(@RequestParam String email) throws UserNotFoundException {
        return ResponseEntity.ok(UserMapper.mapEntityToResponseDtoModel(userService.getUserByEmail(email)));
    }

    @Operation(
            summary = "Контроллер предназначенный для изменения данных авторизированного пользователя",
            description = "Данный контроллер принимает несколько параметров два из которых это два обязательных параметра. <br/>" +
                    "id - данный параметр это идентификационный номер авторизированного пользователя системы. <br/>" +
                    "oldPassword - данный параметр принимает текущий пароль пользователя для подтверждения его авторизации " +
                    "и защиты его данные от изменения третьими лицами.",
            responses = {
                    @ApiResponse(
                            responseCode = "OK ",
                            description = "Возвращает пользователя с обновленными данными в JSON формате. ",
                            content = @Content(schema = @Schema(implementation = UserResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "FAILED",
                            description = "Возвращает информацию об ошибке в JSON формате. ",
                            content = @Content(schema = @Schema(implementation = ResponseExceptionModel.class))
                    )
            }
    )
    @PostMapping("/update_user")
    public ResponseEntity<UserResponseDto> updateUser(
            @RequestParam Long id,
            @RequestParam String oldPassword,
            @RequestParam(required = false) String newPassword,
            @RequestParam(required = false) Boolean active
    )
            throws
            UserNotFoundException,
            IllLegalUserArgumentException,
            InvalidUserCredentialsException
    {
        return ResponseEntity.ok(
                UserMapper.mapEntityToResponseDtoModel(
                        userService.updateUser(id,oldPassword,newPassword,active)
                )
        );
    }


}
