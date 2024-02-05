package com.stepanew.exam.questionnaire.api.DTOs.Dto;

import com.stepanew.exam.questionnaire.store.entities.QuestionEntity;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionDto {
    @NotNull
    Long id;

    @NotNull
    String task;

    @NonNull
    String answer;

    public static QuestionDto mapFromEntity(QuestionEntity questionEntity) {
        return QuestionDto.builder()
                .id(questionEntity.getId())
                .task(questionEntity.getTask())
                .answer(questionEntity.getAnswer())
                .build();
    }
}
