package com.stepanew.exam.questionnaire.api.DTOs.Request;

import com.stepanew.exam.questionnaire.api.enums.Category;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionnaireUpdateRequestDto {

    @NotNull(message = "Id must be not null")
    Long questionnaireId;

    @Length(max = 255, message = "Max title`s length is 255 characters")
    String title;

    @Length(max = 255, message = "Max description`s length is 255 characters")
    String description;

    Category category;

}
