package com.example.hospital.exception;

import com.example.hospital.exception.exceptions.UserRegistrationNullPointException;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

@Schema(description = "Модель передачи данных об ошибке клиентскому приложению в теле запроса.")
public class ResponseModel {
    @Schema(description = "Исключение вызванное в результате работы программы.",
    example =  "UserRegistrationNullPointException"
    )
    private String exception;
    @Schema(description = "Статус ошибки вызванное исключением.",
    example = " 400 BAD_REQUEST"
    )
    private HttpStatus status;
    @Schema(description = "Описание ошибки.",
    example = "Данные пользователя не должны быть пустыми"
    )
    private String message;
    @Schema(description = "Рекомендации что бы в будущем не получать данное исключение",
    example = "Что бы данная ошибка не повторялась убедитесь что вы правильно отправляете данные и все поля заполнены."
    )
    private String advice;

    public ResponseModel(Exception exception, HttpStatus status, String advice) {
        this.exception = exception.getClass().getSimpleName();
        this.message = exception.getMessage();
        this.status = status;
        this.advice = advice;
    }

    public String getException() {
        return exception;
    }

    public ResponseModel setException(String exception) {
        this.exception = exception;
        return this;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public ResponseModel setStatus(HttpStatus status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseModel setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getAdvice() {
        return advice;
    }

    public ResponseModel setAdvice(String advice) {
        this.advice = advice;
        return this;
    }
}
