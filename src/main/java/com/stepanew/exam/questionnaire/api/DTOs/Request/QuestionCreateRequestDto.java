package com.stepanew.exam.questionnaire.api.DTOs.Request;

import com.stepanew.exam.questionnaire.store.entities.QuestionEntity;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionCreateRequestDto {

    @NotNull(message = "QuestionnaireId is must be not null")
    Long questionnaireId;

    @NotNull(message = "Task is must be not null")
    @Length(max = 255, message = "Max task`s length is 255 characters")
    String task;

    @NotNull(message = "Answer is must be not null")
    @Length(max = 255, message = "Max answer`s length is 255 characters")
    String answer;

    public static QuestionEntity mapToEntity(QuestionCreateRequestDto requestDto){
        return QuestionEntity
                .builder()
                .answer(requestDto.getAnswer())
                .task(requestDto.getTask())
                .build();
    }

}
