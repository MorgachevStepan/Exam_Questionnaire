package com.stepanew.exam.questionnaire.api.services.impl;

import com.stepanew.exam.questionnaire.api.services.QuestionnaireService;
import com.stepanew.exam.questionnaire.store.entities.QuestionnaireEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {
    @Override
    public QuestionnaireEntity getById(Long id) {
        return null;
    }

    @Override
    public List<QuestionnaireEntity> getAllByUserId(Long id) {
        return null;
    }

    @Override
    public QuestionnaireEntity create(QuestionnaireEntity questionnaire, Long userId) {
        return null;
    }

    @Override
    public QuestionnaireEntity update(QuestionnaireEntity questionnaire) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
