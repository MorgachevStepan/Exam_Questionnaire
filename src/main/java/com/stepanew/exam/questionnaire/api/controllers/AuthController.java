package com.stepanew.exam.questionnaire.api.controllers;

import com.stepanew.exam.questionnaire.api.DTOs.Dto.UserDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.UserRegisterRequestDto;
import com.stepanew.exam.questionnaire.api.DTOs.auth.JwtRequest;
import com.stepanew.exam.questionnaire.api.DTOs.auth.JwtResponse;
import com.stepanew.exam.questionnaire.api.services.AuthService;
import com.stepanew.exam.questionnaire.api.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    final AuthService authService;
    final UserService userService;

    @PostMapping("/login")
    public JwtResponse login(@RequestBody @Validated JwtRequest loginRequest){
        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Validated UserRegisterRequestDto requestDto){
        UserDto response = userService.create(requestDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@RequestBody String refreshToken){
        return authService.refresh(refreshToken);
    }

}
