package com.stepanew.exam.questionnaire.api.controllers.api;

import com.stepanew.exam.questionnaire.api.DTOs.Dto.QuestionDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.QuestionCreateRequestDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.QuestionUpdateRequestDto;
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

@Tag(name = "Question API", description = "API для взаимодействия с вопросами")
public interface QuestionApi {

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный возврат вопроса",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = QuestionDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Вопрос по переданному id не был найден",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionBody.class)
                            )
                    }
            )
    })
    @Operation(summary = "Получение вопроса по id")
    ResponseEntity<QuestionDto> getById(
            @Parameter(description = "Id вопроса", example = "1")
            Long id
    );

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный возврат вопросов",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = QuestionDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Неверный id опросника",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionBody.class)
                            )
                    }
            )
    })
    @Operation(summary = "Получение всех вопросов вопросника с текущим id")
    ResponseEntity<Page<QuestionDto>> getAllByQuestionnaireId(
            @Parameter(description = "Id вопросника", example = "1")
            Long questionnaireId,
            @Parameter(description = "Номер страницы", example = "0")
            Integer page,
            @Parameter(description = "Количество элементов на странице", example = "20")
            Integer offset
    );

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Вопрос был успешно создан",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = QuestionDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Вопросник по переданному id не был найден",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionBody.class)
                            )
                    }
            )
    })
    @Operation(summary = "Создание нового вопроса")
    ResponseEntity<QuestionDto> addNewQuestion(
            @RequestBody @Validated QuestionCreateRequestDto requestDto
    );

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Вопрос был успешно обновлен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = QuestionDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Вопрос по переданному id не был найден",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionBody.class)
                            )
                    }
            )
    })
    @Operation(summary = "Обновление вопроса")
    ResponseEntity<QuestionDto> updateQuestion(
            @RequestBody @Validated QuestionUpdateRequestDto requestDto
    );

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Вопрос был успешно удален",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseEntity.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Вопрос по переданному id не был найден",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionBody.class)
                            )
                    }
            )
    })
    @Operation(summary = "Удаление вопроса")
    ResponseEntity<Void> deleteQuestion(
            @Parameter(description = "Id вопроса", example = "1")
            Long id
    );

}
