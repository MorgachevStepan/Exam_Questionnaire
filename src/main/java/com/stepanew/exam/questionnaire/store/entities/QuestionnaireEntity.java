package com.stepanew.exam.questionnaire.store.entities;

import com.stepanew.exam.questionnaire.api.enums.Category;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "questionnaires")
public class QuestionnaireEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "questionnaire_id")
    Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    UserEntity creatorId;

    @Column(name = "title")
    String title;

    @Column(name = "description")
    String description;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    Category category;

    @Builder.Default
    LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "questionnaire",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    List<QuestionEntity> questions;

}
