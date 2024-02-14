package com.stepanew.exam.questionnaire.api.DTOs.Request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnswerListRequestDto {

    @NotNull(message = "Questionnaire id is must be not null")
    Long questionnaireId;

    @NotNull(message = "Answers list is must be not null")
    List<AnswerRequestDto> answers;

}
