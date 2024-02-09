package com.stepanew.exam.questionnaire.security.expression;

import com.stepanew.exam.questionnaire.security.JwtEntity;
import com.stepanew.exam.questionnaire.store.repositories.QuestionRepository;
import com.stepanew.exam.questionnaire.store.repositories.QuestionnaireRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("customSecurityExpression")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomSecurityExpression {

    final QuestionnaireRepository questionnaireRepository;
    final QuestionRepository questionRepository;

    public boolean canAccessUserToQuestion(Long questionId){
        Long userId = getAuthenticationId();

        return questionRepository.isUsersQuestion(userId, questionId);
    }

    public boolean canAccessUserToQuestionnaire(Long questionnaireId) {
        Long userId = getAuthenticationId();

        return questionnaireRepository.isUsersQuestionnaire(userId, questionnaireId)
                || hasAnyRole("ROLE_ADMIN");
    }

    public boolean canAccessUser(Long userId){
        Long currentUserId = getAuthenticationId();

        System.out.println(userId);

        return userId.equals(currentUserId) || hasAnyRole("ROLE_ADMIN");
    }

    private Long getAuthenticationId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        JwtEntity user = (JwtEntity) authentication.getPrincipal();

        return user.getId();
    }

    private boolean hasAnyRole(String... roles){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        for(String role: roles){
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
            if(authentication.getAuthorities().contains(authority)){
                return true;
            }
        }
        return false;
    }

}
