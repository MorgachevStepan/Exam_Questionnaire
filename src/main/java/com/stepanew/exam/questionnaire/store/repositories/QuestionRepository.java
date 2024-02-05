package com.stepanew.exam.questionnaire.store.repositories;

import com.stepanew.exam.questionnaire.store.entities.QuestionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

    Page<QuestionEntity> findAllByQuestionnaireId(Long questionnaireId, Pageable pageable);

}
