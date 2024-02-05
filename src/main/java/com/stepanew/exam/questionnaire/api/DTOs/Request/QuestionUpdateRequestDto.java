package com.stepanew.exam.questionnaire.api.DTOs.Request;

import jakarta.validation.constraints.NotNull;
import jdk.jfr.Label;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionUpdateRequestDto {

    @NotNull(message = "Question id must be not null")
    Long questionId;

    @Length(max = 255, message = "Max task`s length is 255 characters")
    String task;

    @Length(max = 255, message = "Max answer`s length is 255 characters")
    String answer;
}
