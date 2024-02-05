package com.stepanew.exam.questionnaire.store.repositories;

import com.stepanew.exam.questionnaire.store.entities.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

    List<QuestionEntity> findAllByQuestionnaireId(Long questionnaireId);

}
