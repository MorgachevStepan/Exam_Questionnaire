package com.stepanew.exam.questionnaire.api.services;

import com.stepanew.exam.questionnaire.api.DTOs.auth.JwtRequest;
import com.stepanew.exam.questionnaire.api.DTOs.auth.JwtResponse;

public interface AuthService {

    JwtResponse login(JwtRequest loginRequest);
    JwtResponse refresh(String refreshToken);

}
