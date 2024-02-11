package com.stepanew.exam.questionnaire.api.services.impl;

import com.stepanew.exam.questionnaire.api.DTOs.Dto.QuestionnaireDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.QuestionnaireCreateRequestDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.QuestionnaireUpdateRequestDto;
import com.stepanew.exam.questionnaire.api.DTOs.Response.QuestionnaireStartedResponseDto;
import com.stepanew.exam.questionnaire.api.enums.Status;
import com.stepanew.exam.questionnaire.api.services.QuestionnaireService;
import com.stepanew.exam.questionnaire.exception.QuestionnaireWasStartedException;
import com.stepanew.exam.questionnaire.exception.ResourceNotFoundException;
import com.stepanew.exam.questionnaire.store.entities.QuestionnaireEntity;
import com.stepanew.exam.questionnaire.store.entities.QuestionnaireStatusEntity;
import com.stepanew.exam.questionnaire.store.entities.UserEntity;
import com.stepanew.exam.questionnaire.store.repositories.QuestionnaireRepository;
import com.stepanew.exam.questionnaire.store.repositories.QuestionnaireStatusRepository;
import com.stepanew.exam.questionnaire.store.repositories.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.stepanew.exam.questionnaire.exception.ResourceNotFoundException.resourceNotFoundExceptionSupplier;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionnaireServiceImpl implements QuestionnaireService {

    final QuestionnaireRepository questionnaireRepository;

    final UserRepository userRepository;

    final QuestionnaireStatusRepository questionnaireStatusRepository;


    @Override
    public QuestionnaireDto getById(Long id) {
        return QuestionnaireDto.MapFromEntity(
                questionnaireRepository
                        .findById(id)
                        .orElseThrow(
                                resourceNotFoundExceptionSupplier(
                                        "Questionnaire with id = %d is not exist", id
                                )
                        )
        );
    }

    @Override
    public Page<QuestionnaireDto> getAllByUserIdWithFilter(Long id,
                                                           Pageable pageable,
                                                           String title,
                                                           LocalDateTime dateFrom,
                                                           LocalDateTime dateTo) {
        if (isExistById(id)) {
            Page<QuestionnaireEntity> response = questionnaireRepository
                    .findAllByCreatorIdIdAndTitleContainingIgnoreCaseAndCreatedAtBeforeAndCreatedAtAfter(
                            id,
                            title,
                            dateTo,
                            dateFrom,
                            pageable
                    );
            if (response.isEmpty()) {
                throw new ResourceNotFoundException(
                       "Nothing was found on page number %d", pageable.getPageNumber()
                );
            }
            return response.map(QuestionnaireDto::MapFromEntity);
        } else {
            throw new ResourceNotFoundException("User with id = %d is not exist", id);
        }
    }

    @Override
    public Page<QuestionnaireDto> getAllWithFilter(Pageable pageable,
                                                   String title,
                                                   LocalDateTime dateFrom,
                                                   LocalDateTime dateTo) {
        Page<QuestionnaireEntity> response = questionnaireRepository
                .findAllByTitleContainingIgnoreCaseAndCreatedAtBeforeAndCreatedAtAfter(
                        title,
                        dateTo,
                        dateFrom,
                        pageable
                );

        if(response.isEmpty()){
            throw new ResourceNotFoundException(
                    "Nothing was found on page number %d", pageable.getPageNumber()
            );
        }

        return response.map(QuestionnaireDto::MapFromEntity);
    }

    @Override
    public QuestionnaireDto create(QuestionnaireCreateRequestDto requestDto) {
        QuestionnaireEntity questionnaire = QuestionnaireCreateRequestDto.mapToEntity(requestDto);
        questionnaire.setCreatedAt(LocalDateTime.now());
        UserEntity user = userRepository
                .findById(requestDto.getUserId())
                .orElseThrow(
                        resourceNotFoundExceptionSupplier(
                                "User with id = %d is not exist",
                                requestDto.getUserId()
                        )
                );
        questionnaire.setCreatorId(user);
        user.getQuestionnaires().add(questionnaire);
        questionnaireRepository.save(questionnaire);
        return QuestionnaireDto.MapFromEntity(questionnaire);
    }

    @Override
    public QuestionnaireDto update(QuestionnaireUpdateRequestDto requestDto) {
        QuestionnaireEntity updatedQuestionnaire = questionnaireRepository
                .findById(requestDto.getQuestionnaireId())
                .orElseThrow(
                        resourceNotFoundExceptionSupplier(
                                "Questionnaire with id = %d is not exist",
                                requestDto.getQuestionnaireId()
                        )
                );
        if (requestDto.getCategory() != null) {
            updatedQuestionnaire.setCategory(requestDto.getCategory());
        }
        if (requestDto.getTitle() != null) {
            updatedQuestionnaire.setTitle(requestDto.getTitle());
        }
        if (requestDto.getDescription() != null) {
            updatedQuestionnaire.setDescription(requestDto.getDescription());
        }
        questionnaireRepository.save(updatedQuestionnaire);
        return QuestionnaireDto.MapFromEntity(updatedQuestionnaire);
    }

    @Override
    public void delete(Long id) {
        QuestionnaireEntity deletedQuestionnaire = questionnaireRepository
                .findById(id)
                .orElseThrow(
                        resourceNotFoundExceptionSupplier(
                                "Questionnaire with id = %d is not exist",
                                id
                        )
                );
        questionnaireRepository.delete(deletedQuestionnaire);
    }

    @Override
    public QuestionnaireStartedResponseDto startQuestionnaire(Long questionnaireId, String username) {
        UserEntity user = userRepository
                .findByUsername(username)
                .orElseThrow(
                        resourceNotFoundExceptionSupplier(
                                "User with username = %s is not exist ",
                                username
                        )
                );
        QuestionnaireEntity questionnaire = questionnaireRepository
                .findById(questionnaireId)
                .orElseThrow(resourceNotFoundExceptionSupplier(
                        "Questionnaire with id = %d is not exist",
                        questionnaireId
                        )
                   );

        boolean isStarted = questionnaireStatusRepository
                .findByUser_IdAndQuestionnaireId(user.getId(), questionnaire.getId())
                .isPresent();

        if(isStarted){
            throw new QuestionnaireWasStartedException(
                    "User with id = %d already have started questionnaire with id = %d",
                    user.getId(),
                    questionnaire.getId()
            );
        }

        QuestionnaireStatusEntity questionnaireStatus = QuestionnaireStatusEntity
                .builder()
                .user(user)
                .questionnaire(questionnaire)
                .status(Status.IN_PROCESS)
                .build();

        questionnaireStatusRepository.save(questionnaireStatus);

        return QuestionnaireStartedResponseDto.mapFromEntity(questionnaireStatus);
    }

    private boolean isExistById(Long id) {
        return userRepository.existsById(id);
    }

}
