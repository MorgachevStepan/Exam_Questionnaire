package com.stepanew.exam.questionnaire.api.DTOs.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "Request for answering questionnaire")
public class AnswerListRequestDto {

    @NotNull(message = "Questionnaire id is must be not null")
    @Schema(description = "Questionnaire id", example = "1")
    Long questionnaireId;

    @NotNull(message = "Answers list is must be not null")
    @Schema(description = "Answers")
    List<AnswerRequestDto> answers;

}
