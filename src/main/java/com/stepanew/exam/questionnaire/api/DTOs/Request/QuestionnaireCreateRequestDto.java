package com.stepanew.exam.questionnaire.api.DTOs.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stepanew.exam.questionnaire.api.enums.Category;
import com.stepanew.exam.questionnaire.store.entities.QuestionnaireEntity;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionnaireCreateRequestDto {

    @NotNull(message = "UserId is must be not null")
    @JsonProperty("user_id")
    Long userId;

    @NotNull(message = "Title is must be not null")
    @Length(max = 255, message = "Max title`s length is 255 characters")
    String title;

    @NotNull(message = "Description is must be not null")
    @Length(max = 255, message = "Max description`s length is 255 characters")
    String description;

    @NotNull(message = "Category is must be not null")
    Category category;

    public static QuestionnaireEntity mapToEntity(QuestionnaireCreateRequestDto requestDto){
        return QuestionnaireEntity
                .builder()
                .title(requestDto.getTitle())
                .description(requestDto.getDescription())
                .category(requestDto.getCategory())
                .build();
    }

}
