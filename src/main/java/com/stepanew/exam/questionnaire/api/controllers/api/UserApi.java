package com.stepanew.exam.questionnaire.api.controllers.api;

import com.stepanew.exam.questionnaire.api.DTOs.Dto.UserDto;
import com.stepanew.exam.questionnaire.exception.ExceptionBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "User API", description = "API для взаимодействия с пользователями")
public interface UserApi {

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный возврат пользователя",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UserDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Отказано в доступе",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionBody.class)
                            )
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Пользователь по переданному id не был найден",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionBody.class)
                            )
                    }
            )
    })
    @Operation(summary = "Получение пользователя по id")
    ResponseEntity<UserDto> getById(
            @Parameter(description = "Id пользователя", example = "1")
            Long id
    );

}
