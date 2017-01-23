package ru.romaberendeev.numbers.android;

import android.app.Application;

import ru.romaberendeev.numbers.di.DaggerNumbersComponent;
import ru.romaberendeev.numbers.di.NumbersComponent;

/**
 * Created by Admin on 23.01.2017.
 */

public class App extends Application {
    private NumbersComponent numbersComponent;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public NumbersComponent getNumbersComponent(){
        if(numbersComponent == null){
            numbersComponent = DaggerNumbersComponent.create();
        }
        return numbersComponent;
    }

    public void clearNumbersComponent(){
        numbersComponent = null;
    }
}
