package com.stepanew.exam.questionnaire.api.DTOs.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stepanew.exam.questionnaire.store.entities.UserEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRegisterRequestDto {

    @NotNull(message = "Username must be not null")
    @Length(max = 255, message = "Username length must be smaller than 255 symbols")
    String username;

    @NotNull(message = "Password must be not null")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;

    @NotNull(message = "Confirmation must be not null")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String passwordConfirmation;

    public static UserEntity mapToEntity(UserRegisterRequestDto requestDto){
        return UserEntity.builder()
                .username(requestDto.username)
                .build();
    }

}
