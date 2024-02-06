package com.stepanew.exam.questionnaire.store.repositories;

import com.stepanew.exam.questionnaire.api.enums.Category;
import com.stepanew.exam.questionnaire.store.entities.QuestionnaireEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.time.LocalDateTime;

public interface QuestionnaireRepository extends JpaRepository<QuestionnaireEntity, Long> {

    Page<QuestionnaireEntity> findAllByCreatorIdIdAndTitleContainingIgnoreCaseAndCreatedAtBeforeAndCreatedAtAfter(
            Long creatorId,
            String title,
            LocalDateTime creationDateTo,
            LocalDateTime creationDateFrom,
            Pageable pageable
    );

    Page<QuestionnaireEntity> findAllByTitleContainingIgnoreCaseAndCreatedAtBeforeAndCreatedAtAfter(
            String title,
            LocalDateTime creationDateTo,
            LocalDateTime creationDateFrom,
            Pageable pageable
    );

}
