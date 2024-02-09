package com.stepanew.exam.questionnaire.api.DTOs.Dto;

import com.stepanew.exam.questionnaire.store.entities.QuestionnaireEntity;
import com.stepanew.exam.questionnaire.api.enums.Category;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionnaireDto {
    @NonNull
    Long id;

    @NotNull
    String title;

    @NonNull
    String description;

    @NonNull
    Category category;

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
