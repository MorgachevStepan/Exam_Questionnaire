package com.stepanew.exam.questionnaire.api.DTOs.Response;

import com.stepanew.exam.questionnaire.api.enums.Status;
import com.stepanew.exam.questionnaire.store.entities.QuestionnaireStatusEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionnaireAnsweredResponseDto {

    Long userId;

    Long questionnaireId;

    Status status;

    Integer correctAnswers;

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
