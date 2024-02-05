package com.stepanew.exam.questionnaire.api.services;

import com.stepanew.exam.questionnaire.api.DTOs.Dto.QuestionnaireDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.QuestionnaireCreateRequestDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.QuestionnaireUpdateRequestDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QuestionnaireService {

    @Transactional(readOnly = true)
    QuestionnaireDto getById(Long id);

    @Transactional(readOnly = true)
    List<QuestionnaireDto> getAllByUserId(Long id);

    @Transactional
    QuestionnaireDto create(QuestionnaireCreateRequestDto requestDto);

    @Transactional
    QuestionnaireDto update(QuestionnaireUpdateRequestDto requestDto);

    @Transactional
    void delete(Long id);
}
