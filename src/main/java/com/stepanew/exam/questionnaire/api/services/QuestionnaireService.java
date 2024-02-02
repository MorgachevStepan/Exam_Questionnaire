package com.stepanew.exam.questionnaire.api.services;

import com.stepanew.exam.questionnaire.store.entities.QuestionEntity;
import com.stepanew.exam.questionnaire.store.entities.QuestionnaireEntity;

import java.util.List;

public interface QuestionnaireService {

    QuestionnaireEntity getById(Long id);
    List<QuestionnaireEntity> getAllByUserId(Long id);
    QuestionnaireEntity create(QuestionnaireEntity questionnaire, Long userId);
    QuestionnaireEntity update(QuestionnaireEntity questionnaire);
    void delete(Long id);
}
