package ru.romaberendeev.numbers.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.romaberendeev.numbers.di.scopes.PerActivity;
import ru.romaberendeev.numbers.domain.StateKeeper;
import ru.romaberendeev.numbers.domain.entity.NumbersState;
import ru.romaberendeev.numbers.presentation.NumbersView;

/**
 * Created by Admin on 23.01.2017.
 */

@Module
public class NumbersModule {

    @Singleton
    @Provides
    StateKeeper<NumbersState> provideStateKeeper(){
        return new StateKeeper<>(NumbersState.EMPTY);
    }
}
