package com.stepanew.exam.questionnaire.api.services.impl;

import com.stepanew.exam.questionnaire.api.DTOs.Dto.QuestionDto;
import com.stepanew.exam.questionnaire.api.DTOs.Dto.QuestionnaireDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.QuestionCreateRequestDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.QuestionnaireCreateRequestDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.QuestionnaireUpdateRequestDto;
import com.stepanew.exam.questionnaire.api.enums.Category;
import com.stepanew.exam.questionnaire.api.services.QuestionnaireService;
import com.stepanew.exam.questionnaire.exception.ResourceNotFoundException;
import com.stepanew.exam.questionnaire.store.entities.QuestionEntity;
import com.stepanew.exam.questionnaire.store.entities.QuestionnaireEntity;
import com.stepanew.exam.questionnaire.store.entities.UserEntity;
import com.stepanew.exam.questionnaire.store.repositories.QuestionnaireRepository;
import com.stepanew.exam.questionnaire.store.repositories.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionnaireServiceImpl implements QuestionnaireService {

    final QuestionnaireRepository questionnaireRepository;
    final UserRepository userRepository;

    @Override
    public QuestionnaireDto getById(Long id) {
        return QuestionnaireDto.MapFromEntity(questionnaireRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                String.format("Questionnaire with id = %d is not exist", id)
                        )
                ));
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
                        String.format("Nothing was found on page number %d", pageable.getPageNumber())
                );
            }
            return response.map(QuestionnaireDto::MapFromEntity);
        } else {
            throw new ResourceNotFoundException(String.format("User with id = %d is not exist", id));
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
                    String.format("Nothing was found on page number %d", pageable.getPageNumber())
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
                        () -> new ResourceNotFoundException(
                                String.format("User with id = %d is not exist", requestDto.getUserId())
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
                .orElseThrow(() -> new ResourceNotFoundException(
                                String.format("Questionnaire with id = %d is not exist", requestDto.getQuestionnaireId())
                        )
                );
        if(requestDto.getCategory() != null){
            updatedQuestionnaire.setCategory(requestDto.getCategory());
        }
        if(requestDto.getTitle() != null){
            updatedQuestionnaire.setTitle(requestDto.getTitle());
        }
        if(requestDto.getDescription() != null){
            updatedQuestionnaire.setDescription(requestDto.getDescription());
        }
        questionnaireRepository.save(updatedQuestionnaire);
        return QuestionnaireDto.MapFromEntity(updatedQuestionnaire);
    }

    @Override
    public void delete(Long id) {
        QuestionnaireEntity deletedQuestionnaire = questionnaireRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Questionnaire with id = %d is not exist", id)
                ));
        questionnaireRepository.delete(deletedQuestionnaire);
    }

    private boolean isExistById(Long id) {
        return userRepository.existsById(id);
    }

}
