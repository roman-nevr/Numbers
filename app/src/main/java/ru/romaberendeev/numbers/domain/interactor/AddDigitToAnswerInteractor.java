package ru.romaberendeev.numbers.domain.interactor;

import javax.inject.Inject;

import ru.romaberendeev.numbers.domain.StateKeeper;
import ru.romaberendeev.numbers.domain.entity.NumbersState;

/**
 * Created by Admin on 23.01.2017.
 */

public class AddDigitToAnswerInteractor extends Interactor {

    @Inject StateKeeper<NumbersState> stateKeeper;

    private Integer digit;

    public Interactor setDigit(int digit) {
        this.digit = digit;
        return this;
    }

    @Inject AddDigitToAnswerInteractor() {}

    @Override
    protected void operation() {
        if(digit == null){
            throw new IllegalArgumentException();
        }
        stateKeeper.change(state -> state.toBuilder()
                .playersAnswer(state.playersAnswer()*10+digit)
                .build());
        digit = null;
    }
}
