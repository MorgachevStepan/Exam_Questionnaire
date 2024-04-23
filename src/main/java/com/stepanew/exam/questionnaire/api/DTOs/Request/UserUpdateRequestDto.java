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
@Schema(description = "Request for user updating")
public class UserUpdateRequestDto {

    @NotNull(message = "Username must be not null")
    @Length(max = 255, message = "Username length must be smaller than 255 symbols")
    @Schema(description = "username", example = "Test")
    String oldUsername;

    @NotNull(message = "Username must be not null")
    @Length(max = 255, message = "Username length must be smaller than 255 symbols")
    @Schema(description = "username", example = "Test")
    String newUsername;

}
