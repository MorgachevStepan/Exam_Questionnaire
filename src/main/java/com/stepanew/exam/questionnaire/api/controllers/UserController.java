package com.stepanew.exam.questionnaire.api.controllers;

import com.stepanew.exam.questionnaire.api.DTOs.Dto.UserDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.UserUpdateRequestDto;
import com.stepanew.exam.questionnaire.api.controllers.api.UserApi;
import com.stepanew.exam.questionnaire.api.services.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserController implements UserApi {

    final UserService userService;

    @GetMapping("/{id}")
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<UserDto> getById(@PathVariable Long id){
        UserDto userDto = UserDto.mapFromEntity(userService.getById(id));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userDto);
    }

    @GetMapping("")
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<Page<UserDto>> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "offset", required = false, defaultValue = "20") Integer offset,
            @RequestParam(name = "title", required = false, defaultValue = "") String username
    ){
        Page<UserDto> response = userService.getAllWithFilter(
                PageRequest.of(page, offset),
                username
        );

        if (response.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);
        }
    }

    @PutMapping("/")
    @PreAuthorize("@customSecurityExpression.canAccessUserToUser(#requestDto.getOldUsername())")
    public ResponseEntity<UserDto> updateUser(@RequestBody @Validated UserUpdateRequestDto requestDto){
        UserDto response = UserDto.mapFromEntity(userService.update(requestDto));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

}
