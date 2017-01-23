package ru.romaberendeev.numbers.di;

import dagger.Module;
import dagger.Provides;
import ru.romaberendeev.numbers.di.scopes.PerActivity;
import ru.romaberendeev.numbers.presentation.NumbersView;

/**
 * Created by Admin on 23.01.2017.
 */

@Module
public class NumbersViewModule {
    private NumbersView view;

    public NumbersViewModule(NumbersView view) {
        this.view = view;
    }

    @PerActivity
    @Provides
    NumbersView provideNumbersView(){
        return view;
    }
}
