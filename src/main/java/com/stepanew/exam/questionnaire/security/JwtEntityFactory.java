package com.stepanew.exam.questionnaire.security;

import com.stepanew.exam.questionnaire.store.entities.RoleEntity;
import com.stepanew.exam.questionnaire.store.entities.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JwtEntityFactory {

    public static JwtEntity create(UserEntity user){
        return JwtEntity.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(mapToGrantedAuthorities(new ArrayList<>(user.getRoles())))
                .build();
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<RoleEntity> roles){
        return roles.stream()
                .map(RoleEntity::getName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

}
