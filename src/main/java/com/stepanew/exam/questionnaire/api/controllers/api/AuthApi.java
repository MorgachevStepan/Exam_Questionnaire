package com.stepanew.exam.questionnaire.api.controllers.api;

import com.stepanew.exam.questionnaire.api.DTOs.Request.UserRegisterRequestDto;
import com.stepanew.exam.questionnaire.api.DTOs.auth.JwtRequest;
import com.stepanew.exam.questionnaire.api.DTOs.auth.JwtResponse;
import com.stepanew.exam.questionnaire.api.DTOs.auth.RefreshJwtRequest;
import com.stepanew.exam.questionnaire.exception.ExceptionBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Auth API", description = "API для входа в систему")
public interface AuthApi {

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный вход в аккаунт",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = JwtResponse.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Неверные данные учетной записи пользователя",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionBody.class)
                            )
                    }
            )
    })
    @Operation(summary = "Login -> возвращает access и refresh token")
    ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest);

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешная регистрация",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = JwtResponse.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Ошибка регистрации -> пароли совпадают или пользователь с таким именем существует",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionBody.class)
                            )
                    }
            )
    })
    @Operation(summary = "Register -> возвращает access и refresh token")
    ResponseEntity<JwtResponse> register(@RequestBody UserRegisterRequestDto requestDto);

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешное обновление jwt токенов",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = JwtResponse.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Неверный username в refresh токене",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionBody.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Jwt токен просрочен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionBody.class)
                            )
                    }
            )
    })
    @Operation(summary = "Возвращает обновленные access и refresh токены")
    ResponseEntity<JwtResponse> refresh(@RequestBody RefreshJwtRequest refreshToken);

}
