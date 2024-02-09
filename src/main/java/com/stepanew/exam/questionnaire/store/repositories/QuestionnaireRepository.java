package com.stepanew.exam.questionnaire.store.repositories;

import com.stepanew.exam.questionnaire.store.entities.QuestionnaireEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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


    @Query("""
           SELECT CASE
                      WHEN COUNT(q) > 0 THEN TRUE
                      ELSE FALSE
                      END
           FROM QuestionnaireEntity q
           WHERE q.creatorId.id = :userId
             AND q.id = :questionnaireId""")
    boolean isUsersQuestionnaire(Long userId, Long questionnaireId);

}
