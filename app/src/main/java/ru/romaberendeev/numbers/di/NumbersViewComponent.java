package ru.romaberendeev.numbers.di;

import dagger.Component;
import ru.romaberendeev.numbers.android.NumbersActivity;
import ru.romaberendeev.numbers.di.scopes.PerActivity;
import ru.romaberendeev.numbers.presentation.NumbersView;

/**
 * Created by Admin on 23.01.2017.
 */
@Component(dependencies = NumbersComponent.class, modules = NumbersViewModule.class)
@PerActivity
public interface NumbersViewComponent {
    void inject(NumbersActivity activity);
}
