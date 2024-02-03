package com.stepanew.exam.questionnaire.api.DTOs.Dto;

import com.stepanew.exam.questionnaire.store.entities.UserEntity;
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
