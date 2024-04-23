package com.stepanew.exam.questionnaire.api.services.impl;

import com.stepanew.exam.questionnaire.api.DTOs.Dto.UserDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.UserRegisterRequestDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.UserUpdateRequestDto;
import com.stepanew.exam.questionnaire.api.services.UserService;
import com.stepanew.exam.questionnaire.exception.ResourceNotFoundException;
import com.stepanew.exam.questionnaire.exception.UserBadRequestException;
import com.stepanew.exam.questionnaire.store.entities.RoleEntity;
import com.stepanew.exam.questionnaire.store.entities.UserEntity;
import com.stepanew.exam.questionnaire.store.repositories.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

import static com.stepanew.exam.questionnaire.exception.ResourceNotFoundException.resourceNotFoundExceptionSupplier;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity getById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(
                        resourceNotFoundExceptionSupplier(
                                "User with id = %d not found",
                                id
                        )
                );
    }

    @Override
    public UserEntity getByUsername(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(
                        resourceNotFoundExceptionSupplier(
                                "User with username = %s not found",
                                username
                        )
                );
    }

    @Override
    public UserEntity update(UserUpdateRequestDto user) {
        if (Objects.equals(user.getOldUsername(), user.getNewUsername())) {
            throw new UserBadRequestException("You already have this username");
        }

        UserEntity updatedUser = userRepository
                .findByUsername(user.getOldUsername())
                .orElseThrow(
                        resourceNotFoundExceptionSupplier(
                                "User with username = %s is not exist", user.getOldUsername()
                        )
                );
        if (user.getNewUsername() != null) {
            if (userRepository.findByUsername(user.getNewUsername()).isPresent()) {
                throw new UserBadRequestException("User with name %s already exists", user.getNewUsername());
            }
            updatedUser.setUsername(user.getNewUsername());
        }
        userRepository.save(updatedUser);
        return updatedUser;
    }

    @Override
    public UserDto create(UserRegisterRequestDto user) {
        UserEntity userEntity = UserRegisterRequestDto.mapToEntity(user);
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserBadRequestException("User with name %s already exists", user.getUsername());
        }
        if (!user.getPassword().equals(user.getPasswordConfirmation())) {
            throw new UserBadRequestException("Password and password confirmation do not match");
        }
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<RoleEntity> roles = Set.of(new RoleEntity(2L, "ROLE_USER"));
        userEntity.setRoles(roles);
        userRepository.save(userEntity);
        return UserDto.mapFromEntity(userEntity);
    }

    @Override
    public boolean isQuestionnaireOwner(Long userId, Long questionnaireId) {
        return false;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Page<UserDto> getAllWithFilter(Pageable pageable, String username) {
        Page<UserEntity> response = userRepository
                .findAllByUsernameContainingIgnoreCase(
                        username,
                        pageable
                );

        if (response.isEmpty()) {
            throw new ResourceNotFoundException(
                    "Nothing was found on page number %d", pageable.getPageNumber()
            );
        }

        return response.map(UserDto::mapFromEntity);

    }
}
