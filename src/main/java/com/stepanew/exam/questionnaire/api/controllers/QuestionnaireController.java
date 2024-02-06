package com.stepanew.exam.questionnaire.api.controllers;

import com.stepanew.exam.questionnaire.api.DTOs.Dto.QuestionDto;
import com.stepanew.exam.questionnaire.api.DTOs.Dto.QuestionnaireDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.QuestionCreateRequestDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.QuestionnaireCreateRequestDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.QuestionnaireUpdateRequestDto;
import com.stepanew.exam.questionnaire.api.services.QuestionnaireService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/questionnaires")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionnaireController {

    final QuestionnaireService questionnaireService;

    @GetMapping("/{id}")
    public ResponseEntity<QuestionnaireDto> getById(@PathVariable Long id) {
        QuestionnaireDto response = questionnaireService.getById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/")
    public ResponseEntity<Page<QuestionnaireDto>> getAllByUserId(
            @RequestParam(name = "user_id") Long userId,
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
    public ResponseEntity<QuestionnaireDto> addNewQuestionnaire(@RequestBody @Validated QuestionnaireCreateRequestDto requestDto) {
        QuestionnaireDto response = questionnaireService.create(requestDto);
        return ResponseEntity
                .created(URI.create("/api/v1/questionnaires/" + response.getId()))
                .body(response);
    }

    @PutMapping("/")
    public ResponseEntity<QuestionnaireDto> updateQuestionnaire(@RequestBody @Validated QuestionnaireUpdateRequestDto requestDto){
        QuestionnaireDto response = questionnaireService.update(requestDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestionnaire(@PathVariable Long id){
        questionnaireService.delete(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}
