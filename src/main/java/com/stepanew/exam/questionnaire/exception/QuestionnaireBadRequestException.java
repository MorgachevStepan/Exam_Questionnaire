package com.stepanew.exam.questionnaire.exception;

import java.util.function.Supplier;

public class QuestionnaireBadRequestException extends RuntimeException{

    public QuestionnaireBadRequestException(String message) {
        super(message);
    }

    public QuestionnaireBadRequestException(String message, Object... args){
        super(String.format(message, args));
    }

    public static Supplier<QuestionnaireBadRequestException> questionnaireWasStartedExceptionSupplier(String message){
        return () -> new QuestionnaireBadRequestException(message);
    }

    public static Supplier<QuestionnaireBadRequestException> questionnaireWasStartedExceptionSupplier(String message, Object... args){
        return () -> new QuestionnaireBadRequestException(message, args);
    }

}
