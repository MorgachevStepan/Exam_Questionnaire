package com.stepanew.exam.questionnaire.api.services.impl;

import com.stepanew.exam.questionnaire.api.DTOs.Dto.QuestionnaireDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.AnswerListRequestDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.AnswerRequestDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.QuestionnaireCreateRequestDto;
import com.stepanew.exam.questionnaire.api.DTOs.Request.QuestionnaireUpdateRequestDto;
import com.stepanew.exam.questionnaire.api.DTOs.Response.QuestionnaireAnsweredResponseDto;
import com.stepanew.exam.questionnaire.api.DTOs.Response.QuestionnaireStartedResponseDto;
import com.stepanew.exam.questionnaire.api.DTOs.Response.StatisticResponseDto;
import com.stepanew.exam.questionnaire.api.enums.Answer;
import com.stepanew.exam.questionnaire.api.enums.Status;
import com.stepanew.exam.questionnaire.api.services.QuestionnaireService;
import com.stepanew.exam.questionnaire.exception.QuestionnaireBadRequestException;
import com.stepanew.exam.questionnaire.exception.ResourceNotFoundException;
import com.stepanew.exam.questionnaire.store.entities.QuestionnaireEntity;
import com.stepanew.exam.questionnaire.store.entities.QuestionnaireStatusEntity;
import com.stepanew.exam.questionnaire.store.entities.UserEntity;
import com.stepanew.exam.questionnaire.store.repositories.QuestionRepository;
import com.stepanew.exam.questionnaire.store.repositories.QuestionnaireRepository;
import com.stepanew.exam.questionnaire.store.repositories.QuestionnaireStatusRepository;
import com.stepanew.exam.questionnaire.store.repositories.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.stepanew.exam.questionnaire.exception.ResourceNotFoundException.resourceNotFoundExceptionSupplier;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionnaireServiceImpl implements QuestionnaireService {

    final QuestionnaireRepository questionnaireRepository;

    final UserRepository userRepository;

    final QuestionnaireStatusRepository questionnaireStatusRepository;

    final QuestionRepository questionRepository;


    @Override
    public QuestionnaireDto getById(Long id) {
        return QuestionnaireDto.MapFromEntity(getQuestionnaire(id));
    }

    @Override
    public Page<QuestionnaireDto> getAllByUserIdWithFilter(Long id,
                                                           Pageable pageable,
                                                           String title,
                                                           LocalDateTime dateFrom,
                                                           LocalDateTime dateTo) {
        if (isExistById(id)) {
            Page<QuestionnaireEntity> response = questionnaireRepository
                    .findAllByCreatorIdIdAndTitleContainingIgnoreCaseAndCreatedAtBeforeAndCreatedAtAfter(
                            id,
                            title,
                            dateTo,
                            dateFrom,
                            pageable
                    );
            if (response.isEmpty()) {
                throw new ResourceNotFoundException(
                       "Nothing was found on page number %d", pageable.getPageNumber()
                );
            }
            return response.map(QuestionnaireDto::MapFromEntity);
        } else {
            throw new ResourceNotFoundException("User with id = %d is not exist", id);
        }
    }

    @Override
    public Page<QuestionnaireDto> getAllWithFilter(Pageable pageable,
                                                   String title,
                                                   LocalDateTime dateFrom,
                                                   LocalDateTime dateTo) {
        Page<QuestionnaireEntity> response = questionnaireRepository
                .findAllByTitleContainingIgnoreCaseAndCreatedAtBeforeAndCreatedAtAfter(
                        title,
                        dateTo,
                        dateFrom,
                        pageable
                );

        if(response.isEmpty()){
            throw new ResourceNotFoundException(
                    "Nothing was found on page number %d", pageable.getPageNumber()
            );
        }

        return response.map(QuestionnaireDto::MapFromEntity);
    }

    @Override
    public QuestionnaireDto create(QuestionnaireCreateRequestDto requestDto) {
        QuestionnaireEntity questionnaire = QuestionnaireCreateRequestDto.mapToEntity(requestDto);
        questionnaire.setCreatedAt(LocalDateTime.now());
        UserEntity user = userRepository
                .findById(requestDto.getUserId())
                .orElseThrow(
                        resourceNotFoundExceptionSupplier(
                                "User with id = %d is not exist",
                                requestDto.getUserId()
                        )
                );
        questionnaire.setCreatorId(user);
        user.getQuestionnaires().add(questionnaire);
        questionnaireRepository.save(questionnaire);
        return QuestionnaireDto.MapFromEntity(questionnaire);
    }

    @Override
    public QuestionnaireDto update(QuestionnaireUpdateRequestDto requestDto) {
        QuestionnaireEntity updatedQuestionnaire = getQuestionnaire(requestDto.getQuestionnaireId());
        if (requestDto.getCategory() != null) {
            updatedQuestionnaire.setCategory(requestDto.getCategory());
        }
        if (requestDto.getTitle() != null) {
            updatedQuestionnaire.setTitle(requestDto.getTitle());
        }
        if (requestDto.getDescription() != null) {
            updatedQuestionnaire.setDescription(requestDto.getDescription());
        }
        questionnaireRepository.save(updatedQuestionnaire);
        return QuestionnaireDto.MapFromEntity(updatedQuestionnaire);
    }

    @Override
    public void delete(Long id) {
        QuestionnaireEntity deletedQuestionnaire = getQuestionnaire(id);
        questionnaireRepository.delete(deletedQuestionnaire);
    }

    @Override
    public QuestionnaireStartedResponseDto startQuestionnaire(Long questionnaireId, String username) {
        UserEntity user = getUser(username);
        QuestionnaireEntity questionnaire = getQuestionnaire(questionnaireId);

        Optional<QuestionnaireStatusEntity> questionnaireStatusEntityOptional = questionnaireStatusRepository
                .findByUser_IdAndQuestionnaireId(user.getId(), questionnaire.getId());

        if(questionnaireStatusEntityOptional.isPresent()){
            QuestionnaireStatusEntity questionnaireStatus = questionnaireStatusEntityOptional.get();
            if(questionnaireStatus.getStatus().equals(Status.IN_PROCESS)) {
                throw new QuestionnaireBadRequestException(
                        "User with id = %d already have started questionnaire with id = %d",
                        user.getId(),
                        questionnaire.getId()
                );
            } else{
                questionnaireStatusRepository.delete(questionnaireStatus);
            }
        }

        QuestionnaireStatusEntity questionnaireStatus = QuestionnaireStatusEntity
                .builder()
                .user(user)
                .questionnaire(questionnaire)
                .status(Status.IN_PROCESS)
                .build();

        questionnaireStatusRepository.save(questionnaireStatus);

        return QuestionnaireStartedResponseDto.mapFromEntity(questionnaireStatus);
    }

    @Override
    public QuestionnaireAnsweredResponseDto answerQuestionnaire(AnswerListRequestDto answerListRequestDto, String username) {
        UserEntity user = getUser(username);
        QuestionnaireEntity questionnaire = getQuestionnaire(answerListRequestDto.getQuestionnaireId());
        QuestionnaireAnsweredResponseDto response;

        Optional<QuestionnaireStatusEntity> questionnaireStatusOptional = questionnaireStatusRepository
                .findByUser_IdAndQuestionnaireId(user.getId(), questionnaire.getId());

        if(questionnaireStatusOptional.isPresent()){
            int correctAnswers = 0;
            int incorrectAnswers = 0;

            QuestionnaireStatusEntity questionnaireStatus = questionnaireStatusOptional.get();
            if(questionnaireStatus.getStatus().equals(Status.DONE)){
                throw new QuestionnaireBadRequestException("Questionnaire with id = %s is done", questionnaire.getId());
            }

            List<AnswerRequestDto> answersList = answerListRequestDto
                    .getAnswers()
                    .stream()
                    .filter(answer -> checkAnswer(answer, questionnaire.getId()))
                    .toList();

            if(answersList.size() > questionnaire.getQuestions().size()){
                throw new QuestionnaireBadRequestException("Too much answers");
            }

            for (AnswerRequestDto answer: answersList){
                switch (answer.getAnswer().toUpperCase()){
                    case "NO" -> incorrectAnswers++;
                    case "YES" -> correctAnswers++;
                }
            }

            response = QuestionnaireAnsweredResponseDto
                    .builder()
                    .userId(user.getId())
                    .questionnaireId(questionnaire.getId())
                    .correctAnswers(correctAnswers)
                    .status(Status.IN_PROCESS)
                    .incorrectAnswers(incorrectAnswers)
                    .build();

            questionnaireStatus.setCorrectAnswers(correctAnswers);
            questionnaireStatus.setIncorrectAnswers(incorrectAnswers);

            if (correctAnswers + incorrectAnswers == questionnaire.getQuestions().size()){
                questionnaireStatus.setStatus(Status.DONE);
                response.setStatus(Status.DONE);
            }

            questionnaireStatusRepository.save(questionnaireStatus);
        } else{
            throw new ResourceNotFoundException("User with id = %d did not start questionnaire with id = %d",
                    user.getId(),
                    questionnaire.getId());
        }

        return response;
    }

    @Override
    public StatisticResponseDto getStatistic(Long id, String username) {
        UserEntity user = getUser(username);
        QuestionnaireEntity questionnaire = getQuestionnaire(id);
        StatisticResponseDto response;

        Optional<QuestionnaireStatusEntity> questionnaireStatusOptional = questionnaireStatusRepository
                .findByUser_IdAndQuestionnaireId(user.getId(), questionnaire.getId());

        if(questionnaireStatusOptional.isPresent()){
            QuestionnaireStatusEntity questionnaireStatus = questionnaireStatusOptional.get();
            if(!questionnaireStatus.getStatus().equals(Status.DONE)){
                throw new QuestionnaireBadRequestException(
                        "Questionnaire with id = %d is not done by user with id = %d",
                        id,
                        user.getId()
                );
            }

            response = StatisticResponseDto.builder()
                    .userId(user.getId())
                    .questionnaireId(id)
                    .correctAnswers(questionnaireStatus.getCorrectAnswers())
                    .incorrectAnswers(questionnaireStatus.getIncorrectAnswers())
                    .build();

        } else {
            throw new ResourceNotFoundException("User with id = %d did not start questionnaire with id = %d",
                    user.getId(),
                    questionnaire.getId());
        }

        return response;
    }

    private boolean isExistById(Long id) {
        return userRepository.existsById(id);
    }

    private boolean checkAnswer(AnswerRequestDto answer, Long questionnaireId){
        if(!(answer.getAnswer().toUpperCase().equals(Answer.NO.getMessage())
                || answer.getAnswer().toUpperCase().equals(Answer.YES.getMessage()))){
            throw new QuestionnaireBadRequestException("Incorrect answer - %s (yes/no)", answer.getAnswer());
        }
        if (!questionRepository.isQuestionnaireQuestion(questionnaireId, answer.getQuestionId())) {
            throw new QuestionnaireBadRequestException("Question with id = %d is not exists", answer.getQuestionId());
        }
        return true;
    }

    private QuestionnaireEntity getQuestionnaire(Long id) {
        return questionnaireRepository
                .findById(id)
                .orElseThrow(resourceNotFoundExceptionSupplier(
                                "Questionnaire with id = %d is not exist",
                                id
                        )
                );
    }

    private UserEntity getUser(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(
                        resourceNotFoundExceptionSupplier(
                                "User with username = %s is not exist ",
                                username
                        )
                );
    }

}
