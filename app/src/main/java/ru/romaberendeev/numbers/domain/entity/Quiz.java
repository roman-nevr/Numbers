package ru.romaberendeev.numbers.domain.entity;

import com.google.auto.value.AutoValue;

/**
 * Created by Admin on 23.01.2017.
 */
@AutoValue
public abstract class Quiz {

    public static Quiz EMPTY = create("", 0);

    public static Quiz create(String quiz, int answer) {
        return builder()
                .quiz(quiz)
                .answer(answer)
                .build();
    }

    public static Builder builder() {return new AutoValue_Quiz.Builder();}

    public abstract String quiz();

    public abstract int answer();

    @AutoValue.Builder public abstract static class Builder {
        public abstract Builder quiz(String quiz);

        public abstract Builder answer(int answer);

        public abstract Quiz build();
    }
}
