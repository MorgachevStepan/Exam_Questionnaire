package com.stepanew.exam.questionnaire.api.DTOs;

import com.stepanew.exam.questionnaire.store.entities.RoleEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleDto {

    @NonNull
    Long id;

    @NonNull
    String name;

    public static RoleDto mapFromEntity(RoleEntity role){
        return RoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }

}
