package com.stepanew.exam.questionnaire.api.DTOs.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stepanew.exam.questionnaire.store.entities.QuestionnaireEntity;
import com.stepanew.exam.questionnaire.api.enums.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionnaireDto {
    @NonNull
    Long id;

    @NonNull
    String description;

    @NonNull
    Category category;

    @JsonProperty("created_at")
    Instant createdAt = Instant.now();

    public static QuestionnaireDto MapFromEntity(QuestionnaireEntity questionnaireEntity){
        return QuestionnaireDto.builder()
                .id(questionnaireEntity.getId())
                .description(questionnaireEntity.getDescription())
                .category(questionnaireEntity.getCategory())
                .createdAt(questionnaireEntity.getCreatedAt())
                .build();
    }
}
