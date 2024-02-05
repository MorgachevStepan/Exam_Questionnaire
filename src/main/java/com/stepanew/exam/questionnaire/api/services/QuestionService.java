package com.stepanew.exam.questionnaire.api.services;

import com.stepanew.exam.questionnaire.api.DTOs.Dto.QuestionDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.QuestionCreateRequestDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.QuestionUpdateRequestDto;
import com.stepanew.exam.questionnaire.store.entities.QuestionEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QuestionService {

    @Transactional(readOnly = true)
    QuestionDto getById(Long id);

    @Transactional(readOnly = true)
    List<QuestionDto> getAllByQuestionnaireId(Long questionnaireId);

    @Transactional
    QuestionDto create(QuestionCreateRequestDto requestDto);

    @Transactional
    QuestionDto update(QuestionUpdateRequestDto question);

    @Transactional
    void delete(Long questionId);

}
