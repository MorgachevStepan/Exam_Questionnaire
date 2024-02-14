package com.stepanew.exam.questionnaire.api.controllers;

import com.stepanew.exam.questionnaire.api.DTOs.Dto.QuestionnaireDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.AnswerListRequestDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.QuestionnaireCreateRequestDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.QuestionnaireUpdateRequestDto;
import com.stepanew.exam.questionnaire.api.DTOs.Response.QuestionnaireAnsweredResponseDto;
import com.stepanew.exam.questionnaire.api.DTOs.Response.QuestionnaireStartedResponseDto;
import com.stepanew.exam.questionnaire.api.services.QuestionnaireService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/questionnaires")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionnaireController {

    final QuestionnaireService questionnaireService;

    @GetMapping("/{id}")
    @PreAuthorize("@customSecurityExpression.canAccessUserToQuestionnaire(#id)")
    public ResponseEntity<QuestionnaireDto> getById(@PathVariable Long id) {
        QuestionnaireDto response = questionnaireService.getById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#userId)")
    public ResponseEntity<Page<QuestionnaireDto>> getAllByUserId(
            @RequestParam Long userId,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "offset", required = false, defaultValue = "20") Integer offset,
            @RequestParam(name = "title", required = false, defaultValue = "") String title,
            @RequestParam(name = "date_from", required = false, defaultValue = "1970-01-01T00:00:00") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime dateFrom,
            @RequestParam(name = "date_to", required = false, defaultValue = "3000-01-01T23:59:59") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime dateTo) {
        Page<QuestionnaireDto> response = questionnaireService.getAllByUserIdWithFilter(
                userId,
                PageRequest.of(page, offset),
                title,
                dateFrom,
                dateTo
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

    @GetMapping("/all")
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<Page<QuestionnaireDto>> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "offset", required = false, defaultValue = "20") Integer offset,
            @RequestParam(name = "title", required = false, defaultValue = "") String title,
            @RequestParam(name = "date_from", required = false, defaultValue = "1970-01-01T00:00:00") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime dateFrom,
            @RequestParam(name = "date_to", required = false, defaultValue = "3000-01-01T23:59:59") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime dateTo) {
        Page<QuestionnaireDto> response = questionnaireService.getAllWithFilter(
                PageRequest.of(page, offset),
                title,
                dateFrom,
                dateTo
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

    @PostMapping("/")
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN, ROLE_USER')")
    public ResponseEntity<QuestionnaireDto> addNewQuestionnaire(@RequestBody @Validated QuestionnaireCreateRequestDto requestDto) {
        QuestionnaireDto response = questionnaireService.create(requestDto);
        return ResponseEntity
                .created(URI.create("/api/v1/questionnaires/" + response.getId()))
                .body(response);
    }

    @PutMapping("/")
    @PreAuthorize("@customSecurityExpression.canAccessUserToQuestionnaire(#requestDto.getQuestionnaireId())")
    public ResponseEntity<QuestionnaireDto> updateQuestionnaire(@RequestBody @Validated QuestionnaireUpdateRequestDto requestDto){
        QuestionnaireDto response = questionnaireService.update(requestDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@customSecurityExpression.canAccessUserToQuestionnaire(#id)")
    public ResponseEntity<Void> deleteQuestionnaire(@PathVariable Long id){
        questionnaireService.delete(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PostMapping("/{id}/start")
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<QuestionnaireStartedResponseDto> startQuestionnaire(@PathVariable Long id, Principal principal){
        QuestionnaireStartedResponseDto response = questionnaireService.startQuestionnaire(id, principal.getName());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PostMapping("/answer")
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<QuestionnaireAnsweredResponseDto> answerQuestionnaire(@RequestBody @Validated AnswerListRequestDto answerRequestDto,
                                                                                Principal principal){
        QuestionnaireAnsweredResponseDto response = questionnaireService.answerQuestionnaire(answerRequestDto, principal.getName());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

}
