package com.stepanew.exam.questionnaire.store.entities;

import com.stepanew.exam.questionnaire.api.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "questionnaire_status")
public class QuestionnaireStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "questionnaire_status_id")
    Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", nullable = false)
    UserEntity user;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "questionnaire_id", nullable = false)
    QuestionnaireEntity questionnaire;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    Status status;

    @Column(name = "correct_answers")
    Integer correctAnswers;

    @Column(name = "incorrect_answers")
    Integer incorrectAnswers;

}
