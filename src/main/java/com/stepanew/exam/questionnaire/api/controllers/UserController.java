package com.stepanew.exam.questionnaire.api.controllers;

import com.stepanew.exam.questionnaire.api.DTOs.UserDto;
import com.stepanew.exam.questionnaire.api.services.QuestionnaireService;
import com.stepanew.exam.questionnaire.api.services.UserService;
import com.stepanew.exam.questionnaire.store.entities.UserEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserController {

    final UserService userService;
    final QuestionnaireService questionnaireService;

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id){
        UserEntity user = userService.getById(id);
        return UserDto.mapFromEntity(user);
    }

}
