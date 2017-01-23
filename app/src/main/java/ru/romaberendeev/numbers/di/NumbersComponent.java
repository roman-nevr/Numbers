package ru.romaberendeev.numbers.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.romaberendeev.numbers.domain.StateKeeper;
import ru.romaberendeev.numbers.domain.entity.NumbersState;

/**
 * Created by Admin on 23.01.2017.
 */

@Component(modules = NumbersModule.class)
@Singleton
public interface NumbersComponent {
    StateKeeper<NumbersState> provideState();
    //void inject(NumbersActivity activity);
}
