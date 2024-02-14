package com.stepanew.exam.questionnaire.api.DTOs.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatisticResponseDto {

    Long userId;

    Long questionnaireId;

    Integer correctAnswers;

    Integer incorrectAnswers;

}
