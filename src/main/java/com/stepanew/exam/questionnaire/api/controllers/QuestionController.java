package com.stepanew.exam.questionnaire.api.controllers;

import com.stepanew.exam.questionnaire.api.DTOs.Dto.QuestionDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.QuestionCreateRequestDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.QuestionUpdateRequestDto;
import com.stepanew.exam.questionnaire.api.services.QuestionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionController {

    final QuestionService questionService;

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDto> getById(@PathVariable Long id){
        QuestionDto question = questionService.getById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(question);
    }

    @GetMapping("/")
    public ResponseEntity<List<QuestionDto>> getByAllByQuestionnaireId(@RequestParam Long questionnaireId) {
        List<QuestionDto> response = questionService
                .getAllByQuestionnaireId(questionnaireId);

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
    public ResponseEntity<QuestionDto> addNewQuestion(@RequestBody @Validated QuestionCreateRequestDto requestDto) {
        QuestionDto response = questionService.create(requestDto);
        return ResponseEntity
                .created(URI.create("/api/v1/questions/" + response.getId()))
                .body(response);
    }

    @PutMapping("/")
    public ResponseEntity<QuestionDto> updateQuestion(@RequestBody @Validated QuestionUpdateRequestDto requestDto){
        QuestionDto response = questionService.update(requestDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id){
        questionService.delete(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}
