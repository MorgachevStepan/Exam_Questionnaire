package com.stepanew.exam.questionnaire.api.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stepanew.exam.questionnaire.store.entities.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserDto {

    @NonNull
    Long id;
    @NonNull
    String username;

    public static UserDto mapFromEntity(UserEntity user){
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }

}
