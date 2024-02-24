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
@Schema(description = "Response for starting questionnaire")
public class QuestionnaireStartedResponseDto {

    @Schema(description = "User id", example = "1")
    Long userId;

    @Schema(description = "Questionnaire id", example = "1")
    Long questionnaireId;

    @Schema(description = "Status", example = "IN_PROCESS")
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
