package com.stepanew.exam.questionnaire.api.controllers.api;

import com.stepanew.exam.questionnaire.api.DTOs.Dto.UserDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.UserUpdateRequestDto;
import com.stepanew.exam.questionnaire.exception.ExceptionBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

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

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный возврат пользователей",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UserDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "Пользователи не были найдены"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Отказано в доступе",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionBody.class)
                            )
                    })
    })
    @Operation(summary = "Получение всех пользователей")
    ResponseEntity<Page<UserDto>> getAll(
            @Parameter(description = "Номер страницы", example = "0")
            Integer page,
            @Parameter(description = "Количество элементов на странице", example = "20")
            Integer offset,
            @Parameter(description = "Username пользователя", example = "User")
            String title
    );

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Пользователь был успешно обновлен",
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
    @Operation(summary = "Обновление пользователя")
    ResponseEntity<UserDto> updateUser(
            @RequestBody @Validated UserUpdateRequestDto requestDto);

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Пользователь был успешно удален",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseEntity.class)
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
    @Operation(summary = "Удаление пользователя")
    ResponseEntity<Void> deleteQuestion(
            @Parameter(description = "Id пользователя", example = "1")
            Long id
    );

}
