package com.stepanew.exam.questionnaire.api.controllers;

import com.stepanew.exam.questionnaire.api.DTOs.Dto.QuestionDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.QuestionCreateRequestDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.QuestionUpdateRequestDto;
import com.stepanew.exam.questionnaire.api.services.QuestionService;
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

import java.net.URI;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionController {

    final QuestionService questionService;

    @GetMapping("/{id}")
    @PreAuthorize("@customSecurityExpression.canAccessUserToQuestion(#id)")
    public ResponseEntity<QuestionDto> getById(@PathVariable Long id){
        QuestionDto question = questionService.getById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(question);
    }

    @GetMapping("/")
    @PreAuthorize("@customSecurityExpression.canAccessUserToStartedQuestionnaire(#questionnaireId)")
    public ResponseEntity<Page<QuestionDto>> getAllByQuestionnaireId(
            @RequestParam Long questionnaireId,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "offset", required = false, defaultValue = "20") Integer offset) {
        Page<QuestionDto> response = questionService
                .getAllByQuestionnaireId(questionnaireId, PageRequest.of(page, offset));

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
    @PreAuthorize("@customSecurityExpression.canAccessUserToQuestionnaire(#requestDto.getQuestionnaireId())")
    public ResponseEntity<QuestionDto> addNewQuestion(@RequestBody @Validated QuestionCreateRequestDto requestDto) {
        QuestionDto response = questionService.create(requestDto);
        return ResponseEntity
                .created(URI.create("/api/v1/questions/" + response.getId()))
                .body(response);
    }

    @PutMapping("/")
    @PreAuthorize("@customSecurityExpression.canAccessUserToQuestion(#requestDto.getQuestionId())")
    public ResponseEntity<QuestionDto> updateQuestion(@RequestBody @Validated QuestionUpdateRequestDto requestDto){
        QuestionDto response = questionService.update(requestDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@customSecurityExpression.canAccessUserToQuestion(#id)")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id){
        questionService.delete(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}
