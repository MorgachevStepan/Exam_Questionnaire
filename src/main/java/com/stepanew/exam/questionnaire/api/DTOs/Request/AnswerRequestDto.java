package com.stepanew.exam.questionnaire.api.DTOs.Request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnswerRequestDto {

    @NotNull(message = "Question id is must be not null")
    Long questionId;

    @NotNull(message = "Answer is must be not null")
    String answer;

}
