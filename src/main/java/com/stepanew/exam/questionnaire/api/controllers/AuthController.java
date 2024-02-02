/*
package com.stepanew.exam.questionnaire.api.controllers;


import com.stepanew.exam.questionnaire.api.DTOs.auth.JwtRequest;
import com.stepanew.exam.questionnaire.api.DTOs.auth.JwtResponse;
import com.stepanew.exam.questionnaire.api.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    final AuthService authService;

    @PostMapping("/login")
    public JwtResponse login(@RequestBody JwtRequest loginRequest){
        return authService.login(loginRequest);
    }

}
*/
