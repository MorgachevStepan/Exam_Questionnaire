package com.stepanew.exam.questionnaire.api.services;

import com.stepanew.exam.questionnaire.api.DTOs.Dto.QuestionDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.QuestionCreateRequestDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.QuestionUpdateRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface QuestionService {

    @Transactional(readOnly = true)
    QuestionDto getById(Long id);

    @Transactional(readOnly = true)
    Page<QuestionDto> getAllByQuestionnaireId(Long questionnaireId, Pageable pageable);

    @Transactional
    QuestionDto create(QuestionCreateRequestDto requestDto);

    @Transactional
    QuestionDto update(QuestionUpdateRequestDto question);

    @Transactional
    void delete(Long questionId);

}
