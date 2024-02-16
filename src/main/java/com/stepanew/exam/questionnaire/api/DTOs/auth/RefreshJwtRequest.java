package com.stepanew.exam.questionnaire.api.DTOs.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "RefreshToken")
public class RefreshJwtRequest {

    @Schema(description = "Refresh token", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBZG1pbiIsImlkIjoxLCJpYXQiOjE3MDgwNzM2MTksImV4cCI6MTcwODA3NzIxOX0.0PgetJ_vFEPXpklfM3FmHRhDcqauGgRG8msmlO07V6g068eHHkrlSsc4dNvngfB5rfK64cRCABNQIu7OUOVPUw")
    String refreshToken;

}
