package com.stepanew.exam.questionnaire.api.services.impl;

import com.stepanew.exam.questionnaire.api.DTOs.Request.UserRegisterRequestDto;
import com.stepanew.exam.questionnaire.api.DTOs.auth.JwtRequest;
import com.stepanew.exam.questionnaire.api.DTOs.auth.JwtResponse;
import com.stepanew.exam.questionnaire.api.DTOs.auth.RefreshJwtRequest;
import com.stepanew.exam.questionnaire.api.services.AuthService;
import com.stepanew.exam.questionnaire.api.services.UserService;
import com.stepanew.exam.questionnaire.security.JwtTokenProvider;
import com.stepanew.exam.questionnaire.store.entities.UserEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthServiceImpl implements AuthService {

    final AuthenticationManager authenticationManager;
    final UserService userService;
    final JwtTokenProvider jwtTokenProvider;

    @Override
    public JwtResponse login(JwtRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        UserEntity user = userService.getByUsername(loginRequest.getUsername());
        return createJwtResponse(user);
    }

    @Override
    public JwtResponse refresh(RefreshJwtRequest refreshToken) {
        return jwtTokenProvider.refreshUserTokens(refreshToken.getRefreshToken());
    }

    @Override
    public JwtResponse register(UserRegisterRequestDto registerRequest) {
        UserEntity user = userService.getByUsername(userService.create(registerRequest).getUsername());
        return createJwtResponse(user);
    }

    private JwtResponse createJwtResponse(UserEntity user){
        return JwtResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .accessToken(jwtTokenProvider.createAccessToken(user.getId(), user.getUsername(), user.getRoles()))
                .refreshToken(jwtTokenProvider.createRefreshToken(user.getId(), user.getUsername()))
                .build();
    }

}
