package com.stepanew.exam.questionnaire.api.services.impl;

import com.stepanew.exam.questionnaire.api.DTOs.Dto.QuestionDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.QuestionCreateRequestDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.QuestionUpdateRequestDto;
import com.stepanew.exam.questionnaire.api.services.QuestionService;
import com.stepanew.exam.questionnaire.exception.ResourceNotFoundException;
import com.stepanew.exam.questionnaire.store.entities.QuestionEntity;
import com.stepanew.exam.questionnaire.store.entities.QuestionnaireEntity;
import com.stepanew.exam.questionnaire.store.repositories.QuestionRepository;
import com.stepanew.exam.questionnaire.store.repositories.QuestionnaireRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionServiceImpl implements QuestionService {

    final QuestionRepository questionRepository;
    final QuestionnaireRepository questionnaireRepository;

    @Override
    public QuestionDto getById(Long id) {
        QuestionEntity question = questionRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                String.format("Question with id = %d is not found", id)
                        )
                );
        return QuestionDto.mapFromEntity(question);
    }

    @Override
    public Page<QuestionDto> getAllByQuestionnaireId(Long questionnaireId, Pageable pageable) {
        Page<QuestionEntity> response = questionRepository
                .findAllByQuestionnaireId(questionnaireId, pageable);

        if(response.isEmpty()){
            throw new ResourceNotFoundException(
                    String.format("Nothing was found on page number %d", pageable.getPageNumber())
            );
        }

        return response.map(QuestionDto::mapFromEntity);
    }

    @Override
    public QuestionDto create(QuestionCreateRequestDto requestDto) {
        QuestionEntity question = QuestionCreateRequestDto.mapToEntity(requestDto);
        QuestionnaireEntity questionnaire = questionnaireRepository
                .findById(requestDto.getQuestionnaireId())
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                String.format("Questionnaire with id = %d is not exist", requestDto.getQuestionnaireId())
                        )
                );
        question.setQuestionnaire(questionnaire);
        questionnaire.getQuestions().add(question);
        questionRepository.save(question);
        return QuestionDto.mapFromEntity(question);
    }

    @Override
    public QuestionDto update(QuestionUpdateRequestDto question) {
        QuestionEntity updatedQuestion = questionRepository
                .findById(question.getQuestionId())
                .orElseThrow(() -> new ResourceNotFoundException(
                                String.format("Question with id = %d is not exist", question.getQuestionId())
                        )
                );
        if(question.getTask() != null){
            updatedQuestion.setTask(question.getTask());
        }
        if(question.getAnswer() != null){
            updatedQuestion.setAnswer(question.getAnswer());
        }
        questionRepository.save(updatedQuestion);
        return QuestionDto.mapFromEntity(updatedQuestion);
    }

    @Override
    public void delete(Long questionId) {
        QuestionEntity deletedQuestion = questionRepository
                .findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Question with id = %d is not exist", questionId)
                ));
        questionRepository.delete(deletedQuestion);
    }

}
