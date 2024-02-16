package com.stepanew.exam.questionnaire.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExceptionBody {

    @Schema(description = "Сообщение ошибки", example = "Authentication failed")
    String message;

    @Schema(description = "Конкретизация ошибки", example = "null")
    Map<String, String> errors;

    public ExceptionBody(String message){
        this.message = message;
    }

}
