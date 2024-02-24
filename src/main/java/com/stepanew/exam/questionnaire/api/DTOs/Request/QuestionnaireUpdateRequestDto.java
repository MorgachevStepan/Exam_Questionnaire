package com.stepanew.exam.questionnaire.api.DTOs.Request;

import com.stepanew.exam.questionnaire.api.enums.Category;
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
@Schema(description = "Request for updating questionnaire")
public class QuestionnaireUpdateRequestDto {

    @NotNull(message = "Id must be not null")
    @Schema(description = "Questionnaire id", example = "1")
    Long questionnaireId;

    @Length(max = 255, message = "Max title`s length is 255 characters")
    @Schema(description = "Questionnaire title", example = "Title")
    String title;

    @Length(max = 255, message = "Max description`s length is 255 characters")
    @Schema(description = "Questionnaire description", example = "Description")
    String description;

    @Schema(description = "Questionnaire category", example = "MATH")
    Category category;

}
