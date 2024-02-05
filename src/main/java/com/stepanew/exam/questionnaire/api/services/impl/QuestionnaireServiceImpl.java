package com.stepanew.exam.questionnaire.api.services.impl;

import com.stepanew.exam.questionnaire.api.DTOs.Dto.QuestionnaireDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.QuestionnaireCreateRequestDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.QuestionnaireUpdateRequestDto;
import com.stepanew.exam.questionnaire.api.services.QuestionnaireService;
import com.stepanew.exam.questionnaire.store.entities.QuestionnaireEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

    @Override
    public QuestionnaireDto getById(Long id) {
        return null;
    }

    @Override
    public List<QuestionnaireDto> getAllByUserId(Long id) {
        return null;
    }

    @Override
    public QuestionnaireDto create(QuestionnaireCreateRequestDto requestDto) {
        return null;
    }

    @Override
    public QuestionnaireDto update(QuestionnaireUpdateRequestDto requestDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

}
