package com.stepanew.exam.questionnaire.api.services;

import com.stepanew.exam.questionnaire.api.DTOs.Request.UserRegisterRequestDto;
import com.stepanew.exam.questionnaire.api.DTOs.auth.JwtRequest;
import com.stepanew.exam.questionnaire.api.DTOs.auth.JwtResponse;
import com.stepanew.exam.questionnaire.api.DTOs.auth.RefreshJwtRequest;

public interface AuthService {

    JwtResponse login(JwtRequest loginRequest);
    JwtResponse refresh(RefreshJwtRequest refreshToken);
    JwtResponse register(UserRegisterRequestDto registerRequest);

}
