package com.stepanew.exam.questionnaire.api.DTOs.Response;

import com.stepanew.exam.questionnaire.api.enums.Status;
import com.stepanew.exam.questionnaire.store.entities.QuestionnaireStatusEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "Response for answering questionnaire")
public class QuestionnaireAnsweredResponseDto {

    @Schema(description = "User id", example = "1")
    Long userId;

    @Schema(description = "Questionnaire id", example = "1")
    Long questionnaireId;

    @Schema(description = "Questionnaire status", example = "IN_PROCESS")
    Status status;

    @Schema(description = "Correct answers", example = "0")
    Integer correctAnswers;

    @Schema(description = "Incorrect answers", example = "0")
    Integer incorrectAnswers;

    public static QuestionnaireAnsweredResponseDto mapFromEntity(QuestionnaireStatusEntity entity){
        return QuestionnaireAnsweredResponseDto
                .builder()
                .userId(entity.getUser().getId())
                .questionnaireId(entity.getQuestionnaire().getId())
                .status(entity.getStatus())
                .correctAnswers(entity.getCorrectAnswers())
                .incorrectAnswers(entity.getIncorrectAnswers())
                .build();
    }

}
