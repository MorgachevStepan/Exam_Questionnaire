package com.stepanew.exam.questionnaire.store.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    Long id;
    @Column(name = "username")
    String username;
    @Column(name = "password")
    String password;
    @Column(name = "password_confirmation")
    String passwordConfirmation;
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "user_id"))
    Set<RoleEntity> roles;
    @OneToMany(mappedBy = "creatorId",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    List<QuestionnaireEntity> questionnaires;
}
