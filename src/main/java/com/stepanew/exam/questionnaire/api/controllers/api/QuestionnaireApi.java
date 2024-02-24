package com.stepanew.exam.questionnaire.api.controllers.api;

import com.stepanew.exam.questionnaire.api.DTOs.Dto.QuestionnaireDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.AnswerListRequestDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.QuestionnaireCreateRequestDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.QuestionnaireUpdateRequestDto;
import com.stepanew.exam.questionnaire.api.DTOs.Response.QuestionnaireAnsweredResponseDto;
import com.stepanew.exam.questionnaire.api.DTOs.Response.QuestionnaireStartedResponseDto;
import com.stepanew.exam.questionnaire.api.DTOs.Response.StatisticResponseDto;
import com.stepanew.exam.questionnaire.exception.ExceptionBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.time.LocalDateTime;

@Tag(name = "Questionnaire API", description = "API для взаимодействия с опросниками")
public interface QuestionnaireApi {

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный возврат вопросника",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = QuestionnaireDto.class)
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
                    description = "Вопросник по переданному id не был найден",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionBody.class)
                            )
                    }
            )
    })
    @Operation(summary = "Получение вопросника по id")
    ResponseEntity<QuestionnaireDto> getById(
            @Parameter(description = "Id вопросника", example = "1")
            Long id
    );

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный возврат вопросников",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = QuestionnaireDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "Вопросники не были найдены"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Неверный id пользователя",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionBody.class)
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
                    })
    })
    @Operation(summary = "Получение всех вопросников по id пользователя")
    ResponseEntity<Page<QuestionnaireDto>> getAllByUserId(
            @Parameter(description = "Id пользователя", example = "1")
            Long userId,
            @Parameter(description = "Номер страницы", example = "0")
            Integer page,
            @Parameter(description = "Количество элементов на странице", example = "20")
            Integer offset,
            @Parameter(description = "Заголовок вопросника", example = "Title")
            String title,
            @Parameter(description = "Дата от", example = "1970-01-01T00:00:00")
            @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
            LocalDateTime dateFrom,
            @Parameter(description = "Дата до", example = "3000-01-01T23:59:59")
            @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
            LocalDateTime dateTo
    );

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный возврат вопросников",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = QuestionnaireDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "Вопросники не были найдены"
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
    @Operation(summary = "Получение всех вопросников")
    ResponseEntity<Page<QuestionnaireDto>> getAll(
            @Parameter(description = "Номер страницы", example = "0")
            Integer page,
            @Parameter(description = "Количество элементов на странице", example = "20")
            Integer offset,
            @Parameter(description = "Заголовок вопросника", example = "Title")
            String title,
            @Parameter(description = "Дата от", example = "1970-01-01T00:00:00")
            @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
            LocalDateTime dateFrom,
            @Parameter(description = "Дата до", example = "3000-01-01T23:59:59")
            @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
            LocalDateTime dateTo
    );

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Вопросник был успешно создан",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = QuestionnaireDto.class)
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
    @Operation(summary = "Создание нового вопросника")
    ResponseEntity<QuestionnaireDto> addNewQuestionnaire(
            @RequestBody @Validated QuestionnaireCreateRequestDto requestDto);

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Вопросник был успешно обновлен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = QuestionnaireDto.class)
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
                    description = "Вопросник по переданному id не был найден",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionBody.class)
                            )
                    }
            )
    })
    @Operation(summary = "Обновление опросника")
    ResponseEntity<QuestionnaireDto> updateQuestionnaire(
            @RequestBody @Validated QuestionnaireUpdateRequestDto requestDto);

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Вопросник был успешно удален"
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
                    description = "Вопросник по переданному id не был найден",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionBody.class)
                            )
                    }
            )
    })
    @Operation(summary = "Удаление опросника")
    ResponseEntity<Void> deleteQuestionnaire(
            @Parameter(description = "Id опросника", example = "1")
            Long questionnaireId
    );

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Вопросник успешно начат",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = QuestionnaireStartedResponseDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Пользователь уже начал этот опросник",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionBody.class)
                            )
                    }),
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
                    description = "Вопросник по переданному id не был найден",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionBody.class)
                            )
                    }
            )
    })
    @Operation(summary = "Начать опросник")
    ResponseEntity<QuestionnaireStartedResponseDto> startQuestionnaire(
            @Parameter(description = "Id опросника", example = "1")
            Long id,
            Principal principal
    );

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ответы были успешно отправлены",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = QuestionnaireAnsweredResponseDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Слишком много ответов или такой вопросник уже завершен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionBody.class)
                            )
                    }),
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
                    description = "Вопросник по переданному id не был найден",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionBody.class)
                            )
                    }
            )
    })
    @Operation(summary = "Отправить ответы на вопросы")
    ResponseEntity<QuestionnaireAnsweredResponseDto> answerQuestionnaire(
            @RequestBody @Validated AnswerListRequestDto answerRequestDto,
            Principal principal
    );

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Статистика успешно получена",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StatisticResponseDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Опросник не был завершен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionBody.class)
                            )
                    }),
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
                    description = "Вопросник по переданному id не был найден",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionBody.class)
                            )
                    }
            )
    })
    @Operation(summary = "Получение статистики по вопроснику")
    ResponseEntity<StatisticResponseDto> getStatistic(
            @Parameter(description = "Id опросника", example = "1")
            Long id,
            Principal principal
    );

}
