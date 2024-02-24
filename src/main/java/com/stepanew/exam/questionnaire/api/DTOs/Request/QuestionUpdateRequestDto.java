package com.stepanew.exam.questionnaire.api.DTOs.Request;

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
@Schema(description = "Request for updating question")
public class QuestionUpdateRequestDto {

    @NotNull(message = "Question id must be not null")
    @Schema(description = "Question id", example = "1")
    Long questionId;

    @Length(max = 255, message = "Max task`s length is 255 characters")
    @Schema(description = "Question description", example = "1 + 1 = ?")
    String task;

    @Length(max = 255, message = "Max answer`s length is 255 characters")
    @Schema(description = "Question answer", example = "2")
    String answer;
}
