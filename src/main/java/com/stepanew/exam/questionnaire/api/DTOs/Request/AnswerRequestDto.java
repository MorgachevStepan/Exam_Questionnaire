package com.stepanew.exam.questionnaire.api.DTOs.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "Answer request")
public class AnswerRequestDto {

    @NotNull(message = "Question id is must be not null")
    @Schema(description = "Question id", example = "1")
    Long questionId;

    @NotNull(message = "Answer is must be not null")
    @Schema(description = "Answer", example = "YES")
    String answer;

}
