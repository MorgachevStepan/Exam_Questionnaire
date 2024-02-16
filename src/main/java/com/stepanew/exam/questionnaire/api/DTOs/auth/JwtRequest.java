package com.stepanew.exam.questionnaire.api.DTOs.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "Request for login")
public class JwtRequest {

    @NotNull(message = "Username must be not null")
    @Schema(description = "username", example = "Username")
    String username;

    @NotNull(message = "Password must be not null")
    @Schema(description = "password", example = "1234")
    String password;

}
