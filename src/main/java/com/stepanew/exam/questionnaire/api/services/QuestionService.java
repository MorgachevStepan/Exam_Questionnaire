package com.stepanew.exam.questionnaire.api.services;

import com.stepanew.exam.questionnaire.store.entities.QuestionEntity;

import java.util.List;

public interface QuestionService {

    QuestionEntity getById(Long id);
    List<QuestionEntity> getAllByQuestionnaireId(Long id);
    QuestionEntity create(QuestionEntity question, Long QuestionnaireId);

}
