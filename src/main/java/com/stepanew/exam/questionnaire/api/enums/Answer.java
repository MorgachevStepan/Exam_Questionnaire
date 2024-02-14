package com.stepanew.exam.questionnaire.api.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum Answer {

    YES("YES"),
    NO("NO");

    final String message;

    Answer(String message){
        this.message = message;
    }

}
