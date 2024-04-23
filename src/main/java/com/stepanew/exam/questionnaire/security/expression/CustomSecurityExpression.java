package com.stepanew.exam.questionnaire.security.expression;

import com.stepanew.exam.questionnaire.api.enums.Status;
import com.stepanew.exam.questionnaire.security.JwtEntity;
import com.stepanew.exam.questionnaire.store.entities.QuestionnaireStatusEntity;
import com.stepanew.exam.questionnaire.store.repositories.QuestionRepository;
import com.stepanew.exam.questionnaire.store.repositories.QuestionnaireRepository;
import com.stepanew.exam.questionnaire.store.repositories.QuestionnaireStatusRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service("customSecurityExpression")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomSecurityExpression {

    final QuestionnaireRepository questionnaireRepository;
    final QuestionRepository questionRepository;
    final QuestionnaireStatusRepository questionnaireStatusRepository;

    public boolean canAccessUserToQuestion(Long questionId) {
        Long userId = getAuthenticationId();

        return questionRepository.isUsersQuestion(userId, questionId);
    }

    public boolean canAccessUserToUser(String username) {
        String checkingUsername = getAuthenticationUsername();

        return Objects.equals(username, checkingUsername) || hasAnyRole("ROLE_ADMIN");
    }

    public boolean canAccessUserToQuestionnaire(Long questionnaireId) {
        Long userId = getAuthenticationId();

        return questionnaireRepository.isUsersQuestionnaire(userId, questionnaireId)
                || hasAnyRole("ROLE_ADMIN");
    }

    public boolean canAccessUserToStartedQuestionnaire(Long questionnaireId) {
        boolean canAccess = false;
        Long userId = getAuthenticationId();

        Optional<QuestionnaireStatusEntity> questionnaireStatus = questionnaireStatusRepository
                .findByUser_IdAndQuestionnaireId(userId, questionnaireId);

        if (questionnaireStatus.isPresent()) {
            canAccess = questionnaireStatus.get().getStatus().equals(Status.IN_PROCESS);
        }

        return canAccess || canAccessUserToQuestionnaire(questionnaireId);
    }

    public boolean canAccessUser(Long userId) {
        Long currentUserId = getAuthenticationId();

        System.out.println(userId);

        return userId.equals(currentUserId) || hasAnyRole("ROLE_ADMIN");
    }

    private Long getAuthenticationId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        JwtEntity user = (JwtEntity) authentication.getPrincipal();

        return user.getId();
    }

    private String getAuthenticationUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        JwtEntity user = (JwtEntity) authentication.getPrincipal();

        return user.getUsername();
    }

    private boolean hasAnyRole(String... roles) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        for (String role : roles) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
            if (authentication.getAuthorities().contains(authority)) {
                return true;
            }
        }
        return false;
    }

}
