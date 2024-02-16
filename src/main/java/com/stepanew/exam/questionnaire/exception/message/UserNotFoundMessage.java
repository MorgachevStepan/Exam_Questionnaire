package com.stepanew.exam.questionnaire.exception.message;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

public record UserNotFoundMessage(

        @Schema(description = "Сообщение ошибки", example = "Authentication failed")
        String message,

        @Schema(description = "Конкретизация ошибки", example = "null")
        Map<String, String> errors
) {
}
