package com.stepanew.exam.questionnaire.api.services;

import com.stepanew.exam.questionnaire.api.DTOs.Dto.QuestionnaireDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.QuestionnaireCreateRequestDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.QuestionnaireUpdateRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;

public interface QuestionnaireService {

    @Transactional(readOnly = true)
    QuestionnaireDto getById(Long id);

    @Transactional(readOnly = true)
    Page<QuestionnaireDto> getAllByUserIdWithFilter(Long id, Pageable pageable, String title, LocalDateTime dateFrom, LocalDateTime dateTo);

    @Transactional(readOnly = true)
    Page<QuestionnaireDto> getAllWithFilter(Pageable pageable, String title, LocalDateTime dateFrom, LocalDateTime dateTo);

    @Transactional
    QuestionnaireDto create(QuestionnaireCreateRequestDto requestDto);

    @Transactional
    QuestionnaireDto update(QuestionnaireUpdateRequestDto requestDto);

    @Transactional
    void delete(Long id);
}
