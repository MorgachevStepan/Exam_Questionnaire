package com.stepanew.exam.questionnaire.store.repositories;

import com.stepanew.exam.questionnaire.store.entities.QuestionnaireStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionnaireStatusRepository extends JpaRepository<QuestionnaireStatusEntity, Long> {

    Optional<QuestionnaireStatusEntity> findByUser_IdAndQuestionnaireId(Long userId, Long questionnaireId);

}
