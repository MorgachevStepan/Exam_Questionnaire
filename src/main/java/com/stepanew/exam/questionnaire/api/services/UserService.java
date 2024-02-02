package com.stepanew.exam.questionnaire.api.services;

import com.stepanew.exam.questionnaire.store.entities.UserEntity;

public interface UserService {

    UserEntity getById(Long id);
    UserEntity getByUsername(String username);

}
