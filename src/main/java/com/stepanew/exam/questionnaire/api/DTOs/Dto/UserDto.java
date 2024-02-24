package com.stepanew.exam.questionnaire.api.DTOs.Dto;

import com.stepanew.exam.questionnaire.store.entities.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Schema(description = "DTO for user")
public class UserDto {

    @NonNull
    @Schema(description = "User id", example = "1")
    Long id;

    @NonNull
    @Schema(description = "Username", example = "Test")
    String username;

    public static UserDto mapFromEntity(UserEntity user){
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }

}
