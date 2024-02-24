package com.stepanew.exam.questionnaire.api.DTOs.Dto;

import com.stepanew.exam.questionnaire.api.enums.Category;
import com.stepanew.exam.questionnaire.store.entities.QuestionnaireEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "DTO for questionnaire")
public class QuestionnaireDto {
    @NonNull
    @Schema(description = "Questionnaire id", example = "1")
    Long id;

    @NotNull
    @Schema(description = "Questionnaire title", example = "Example title")
    String title;

    @NonNull
    @Schema(description = "Questionnaire description", example = "Example description")
    String description;

    @NonNull
    @Schema(description = "Questionnaire category", example = "MATH")
    Category category;

    @Schema(description = "Questionnaire created at", example = "2024-01-29 09:00:00.000000 +00:00")
    LocalDateTime createdAt;

    public static QuestionnaireDto MapFromEntity(QuestionnaireEntity questionnaireEntity){
        return QuestionnaireDto.builder()
                .id(questionnaireEntity.getId())
                .title(questionnaireEntity.getTitle())
                .description(questionnaireEntity.getDescription())
                .category(questionnaireEntity.getCategory())
                .createdAt(questionnaireEntity.getCreatedAt())
                .build();
    }
}
