package com.stepanew.exam.questionnaire.exception;

import java.util.function.Supplier;

public class QuestionnaireWasStartedException extends RuntimeException{

    public QuestionnaireWasStartedException(String message) {
        super(message);
    }

    public QuestionnaireWasStartedException(String message, Object... args){
        super(String.format(message, args));
    }

    public static Supplier<QuestionnaireWasStartedException> questionnaireWasStartedExceptionSupplier(String message){
        return () -> new QuestionnaireWasStartedException(message);
    }

    public static Supplier<QuestionnaireWasStartedException> questionnaireWasStartedExceptionSupplier(String message, Object... args){
        return () -> new QuestionnaireWasStartedException(message, args);
    }

}
