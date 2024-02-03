package com.stepanew.exam.questionnaire.api.DTOs.Dto;

import com.stepanew.exam.questionnaire.store.entities.QuestionEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionDto {
    @NonNull
    Long id;

    @NonNull
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
