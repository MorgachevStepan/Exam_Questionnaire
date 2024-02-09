package com.stepanew.exam.questionnaire.store.repositories;

import com.stepanew.exam.questionnaire.store.entities.QuestionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

    Page<QuestionEntity> findAllByQuestionnaireId(Long questionnaireId, Pageable pageable);

    @Query("""
            SELECT CASE 
                       WHEN COUNT(q) > 0 THEN TRUE
                       ELSE FALSE
                       END
            FROM QuestionEntity q
            WHERE q.questionnaire.creatorId.id = :userId 
              AND q.id = :questionId""")
    boolean isUsersQuestion(Long userId, Long questionId);
}
