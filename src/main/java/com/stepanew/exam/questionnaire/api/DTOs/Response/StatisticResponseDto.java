package com.stepanew.exam.questionnaire.api.DTOs.Response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "Response for statistic")
public class StatisticResponseDto {

    @Schema(description = "User id", example = "1")
    Long userId;

    @Schema(description = "Questionnaire id", example = "1")
    Long questionnaireId;

    @Schema(description = "Correct answers", example = "0")
    Integer correctAnswers;

    @Schema(description = "Incorrect answers", example = "0")
    Integer incorrectAnswers;

}
