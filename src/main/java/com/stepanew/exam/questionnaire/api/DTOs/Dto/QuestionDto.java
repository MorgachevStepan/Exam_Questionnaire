package com.stepanew.exam.questionnaire.api.DTOs.Dto;

import com.stepanew.exam.questionnaire.store.entities.QuestionEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "DTO for question")
public class QuestionDto {
    @NotNull
    @Schema(description = "Question id", example = "1")
    Long id;

    @NotNull
    @Schema(description = "Question description", example = "1 + 1 = ?")
    String task;

    @NonNull
    @Schema(description = "Question answer", example = "2")
    String answer;

    public static QuestionDto mapFromEntity(QuestionEntity questionEntity) {
        return QuestionDto.builder()
                .id(questionEntity.getId())
                .task(questionEntity.getTask())
                .answer(questionEntity.getAnswer())
                .build();
    }
}
