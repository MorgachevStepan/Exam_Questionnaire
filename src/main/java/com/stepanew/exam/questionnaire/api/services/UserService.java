package com.stepanew.exam.questionnaire.api.services;

import com.stepanew.exam.questionnaire.store.entities.UserEntity;

public interface UserService {

    UserEntity getById(Long id);
    UserEntity getByUsername(String username);
    UserEntity update(UserEntity user);
    UserEntity create(UserEntity user);
    boolean isQuestionnaireOwner(Long userId, Long questionnaireId);
    void delete(Long id);

}
