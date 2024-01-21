package com.stepanew.exam.questionnaire.store.entities;

import com.stepanew.exam.questionnaire.api.enums.Category;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "questionnaire_entity")
public class QuestionnaireEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "questionnaire_entity_id")
    Long id;

    @Column(name = "description")
    String description;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    Category category;

    @Builder.Default
    Instant createdAt = Instant.now();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "questionnaire")
    List<QuestionEntity> questions = new ArrayList<>();
}
