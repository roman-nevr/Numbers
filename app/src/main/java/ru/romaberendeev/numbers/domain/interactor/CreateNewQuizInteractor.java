package ru.romaberendeev.numbers.domain.interactor;

import javax.inject.Inject;

import ru.romaberendeev.numbers.domain.StateKeeper;
import ru.romaberendeev.numbers.domain.entity.NumbersState;
import ru.romaberendeev.numbers.domain.entity.Quiz;
import ru.romaberendeev.numbers.utils.Equation;

/**
 * Created by Admin on 23.01.2017.
 */

public class CreateNewQuizInteractor extends Interactor {

    @Inject StateKeeper<NumbersState> stateKeeper;

    private int difficulty;

    public Interactor setDifficulty(int difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    @Inject CreateNewQuizInteractor() {}

    @Override
    protected void operation() {
        if(difficulty == 0){
            throw new IllegalArgumentException("difficulty can't be zero");
        }
        stateKeeper.change(state -> state.toBuilder()
                .quiz(newQuiz(difficulty))
                .playersAnswer(0)
                .build());
    }

    private Quiz newQuiz(int difficulty) {
        return Equation.calc(difficulty);
    }
}
