package com.stepanew.exam.questionnaire.api.DTOs.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Schema(description = "Response after login")
public class JwtResponse {

    @Schema(description = "Current user id", example = "1")
    Long id;

    @Schema(description = "Current user username", example = "Test")
    String username;

    @Schema(description = "Access token", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBZG1pbiIsImlkIjoxLCJyb2xlcyI6WyJST0xFX1VTRVIiLCJST0xFX0FETUlOIl0sImlhdCI6MTcwODA3MzYxOSwiZXhwIjoxNzA4MDc3MjE5fQ.LoAeZKoTYeC8R5obPehniC5EdTCTkbvikKyJ0DBi9hbY35NLa-Ucb7uVFJtB7VDoAIWEypMTZd62B9Ub6A82TQ")
    String accessToken;

    @Schema(description = "Refresh token", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBZG1pbiIsImlkIjoxLCJpYXQiOjE3MDgwNzM2MTksImV4cCI6MTcwODA3NzIxOX0.0PgetJ_vFEPXpklfM3FmHRhDcqauGgRG8msmlO07V6g068eHHkrlSsc4dNvngfB5rfK64cRCABNQIu7OUOVPUw")
    String refreshToken;

}
