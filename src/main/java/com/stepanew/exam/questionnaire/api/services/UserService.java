package com.stepanew.exam.questionnaire.api.services;

import com.stepanew.exam.questionnaire.api.DTOs.Dto.UserDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.UserRegisterRequestDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.UserUpdateRequestDto;
import com.stepanew.exam.questionnaire.store.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {

    @Transactional(readOnly = true)
    UserEntity getById(Long id);

    @Transactional(readOnly = true)
    UserEntity getByUsername(String username);

    @Transactional
    UserEntity update(UserUpdateRequestDto user);

    @Transactional
    UserDto create(UserRegisterRequestDto user);

    @Transactional(readOnly = true)
    boolean isQuestionnaireOwner(Long userId, Long questionnaireId);

    @Transactional
    void delete(Long id);

    @Transactional(readOnly = true)
    Page<UserDto> getAllWithFilter(Pageable pageable, String username);

}
