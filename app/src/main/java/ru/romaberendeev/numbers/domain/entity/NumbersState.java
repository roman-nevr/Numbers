package ru.romaberendeev.numbers.domain.entity;

import com.google.auto.value.AutoValue;

/**
 * Created by Admin on 23.01.2017.
 */

@AutoValue
public abstract class NumbersState {

    public static NumbersState EMPTY = create(Quiz.EMPTY, 0, 0, 0, 0);

    public abstract Quiz quiz();

    public abstract int playersAnswer();

    public abstract int time();

    public abstract int rightAnswers();

    public abstract int wrongAnswers();

    public abstract Builder toBuilder();

    public static NumbersState create(Quiz quiz, int playersAnswer, int time, int rightAnswers, int wrongAnswers) {
        return builder()
                .quiz(quiz)
                .playersAnswer(playersAnswer)
                .time(time)
                .rightAnswers(rightAnswers)
                .wrongAnswers(wrongAnswers)
                .build();
    }

    public static Builder builder() {return new AutoValue_NumbersState.Builder();}

    @AutoValue.Builder public abstract static class Builder {
        public abstract Builder quiz(Quiz quiz);

        public abstract Builder playersAnswer(int playersAnswer);

        public abstract Builder time(int time);

        public abstract Builder rightAnswers(int rightAnswers);

        public abstract Builder wrongAnswers(int wrongAnswers);

        public abstract NumbersState build();
    }
}
