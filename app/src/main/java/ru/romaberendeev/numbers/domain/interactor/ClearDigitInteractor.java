package ru.romaberendeev.numbers.domain.interactor;

import javax.inject.Inject;

import ru.romaberendeev.numbers.domain.StateKeeper;
import ru.romaberendeev.numbers.domain.entity.NumbersState;

/**
 * Created by Admin on 23.01.2017.
 */

public class ClearDigitInteractor extends Interactor {

    @Inject StateKeeper<NumbersState> stateKeeper;

    @Inject ClearDigitInteractor() {}

    @Override
    protected void operation() {
        stateKeeper.change(state -> state.toBuilder()
                .playersAnswer(state.playersAnswer()/10)
                .build());
    }
}
