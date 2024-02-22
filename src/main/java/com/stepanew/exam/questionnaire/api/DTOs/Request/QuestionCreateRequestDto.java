package com.stepanew.exam.questionnaire.api.DTOs.Request;

import com.stepanew.exam.questionnaire.store.entities.QuestionEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "Request for creating question")
public class QuestionCreateRequestDto {

    @NotNull(message = "QuestionnaireId is must be not null")
    @Schema(description = "Questionnaire id", example = "1")
    Long questionnaireId;

    @NotNull(message = "Task is must be not null")
    @Length(max = 255, message = "Max task`s length is 255 characters")
    @Schema(description = "Question description", example = "1 + 1 = ?")
    String task;

    @NotNull(message = "Answer is must be not null")
    @Length(max = 255, message = "Max answer`s length is 255 characters")
    @Schema(description = "Question answer", example = "2")
    String answer;

    public static QuestionEntity mapToEntity(QuestionCreateRequestDto requestDto){
        return QuestionEntity
                .builder()
                .answer(requestDto.getAnswer())
                .task(requestDto.getTask())
                .build();
    }

}
