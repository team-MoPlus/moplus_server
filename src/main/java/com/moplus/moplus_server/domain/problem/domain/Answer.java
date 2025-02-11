package com.moplus.moplus_server.domain.problem.domain;

import com.moplus.moplus_server.domain.problem.domain.problem.AnswerType;
import com.moplus.moplus_server.global.error.exception.ErrorCode;
import com.moplus.moplus_server.global.error.exception.InvalidValueException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Answer {

    @Column(name = "answer")
    private String value;

    public Answer(String value, AnswerType answerType) {
        validateByType(value, answerType);
        this.value = value;
    }

    private void validateByType(String answer, AnswerType answerType) {
        if (answer.isBlank()) {
            throw new InvalidValueException(ErrorCode.BLANK_INPUT_VALUE);
        }
        if (answerType == AnswerType.MULTIPLE_CHOICE) {
            if (!answer.matches("^[1-5]*$")) {
                throw new InvalidValueException(ErrorCode.INVALID_MULTIPLE_CHOICE_ANSWER);
            }
        }
        if (answerType == AnswerType.SHORT_NUMBER_ANSWER) {
            try {
                int numericAnswer = Integer.parseInt(answer);
                if (numericAnswer < 0 || numericAnswer > 999) {
                    throw new InvalidValueException(ErrorCode.INVALID_SHORT_NUMBER_ANSWER);
                }
            } catch (NumberFormatException e) {
                throw new InvalidValueException(ErrorCode.INVALID_SHORT_NUMBER_ANSWER);
            }
        }
    }
}
