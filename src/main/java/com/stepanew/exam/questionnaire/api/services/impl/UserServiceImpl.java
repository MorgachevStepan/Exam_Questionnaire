package com.stepanew.exam.questionnaire.api.services.impl;

import com.stepanew.exam.questionnaire.api.services.UserService;
import com.stepanew.exam.questionnaire.exception.ResourceNotFoundException;
import com.stepanew.exam.questionnaire.store.entities.UserEntity;
import com.stepanew.exam.questionnaire.store.repositories.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;
    //final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity getById(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User with this id not found")
        );
        return user;
    }

    @Override
    public UserEntity getByUsername(String username) {
        return null;
    }

    @Override
    public UserEntity update(UserEntity user) {
        return null;
    }

    @Override
    public UserEntity create(UserEntity user) {
        return null;
    }

    @Override
    public boolean isQuestionnaireOwner(Long userId, Long questionnaireId) {
        return false;
    }

    @Override
    public void delete(Long id) {

    }
}
