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
public class QuestionnaireStartedResponseDto {

    Long userId;

    Long questionnaireId;

    Status status;

    public static QuestionnaireStartedResponseDto mapFromEntity(QuestionnaireStatusEntity entity){
        return QuestionnaireStartedResponseDto
                .builder()
                .userId(entity.getUser().getId())
                .questionnaireId(entity.getQuestionnaire().getId())
                .status(entity.getStatus())
                .build();
    }

}
