package com.stepanew.exam.questionnaire.api.DTOs.Dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionnaireStatusDto {

    Long id;


}
