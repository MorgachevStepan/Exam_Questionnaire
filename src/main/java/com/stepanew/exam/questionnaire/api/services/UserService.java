package com.stepanew.exam.questionnaire.api.services;

import com.stepanew.exam.questionnaire.api.DTOs.Dto.UserDto;
import com.stepanew.exam.questionnaire.store.entities.UserEntity;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {

    @Transactional(readOnly = true)
    UserEntity getById(Long id);

    @Transactional(readOnly = true)
    UserEntity getByUsername(String username);

    @Transactional
    UserEntity update(UserEntity user);

    @Transactional
    UserEntity create(UserEntity user);

    @Transactional(readOnly = true)
    boolean isQuestionnaireOwner(Long userId, Long questionnaireId);

    @Transactional
    void delete(Long id);

}
