package ru.romaberendeev.numbers.presentation;

import ru.romaberendeev.numbers.android.custom_views.KeyPad;

/**
 * Created by Admin on 23.01.2017.
 */

public interface NumbersView {
    void setAnswer(String answer);
    void addDigitToAnswer(String digit);
    void setTimer(int seconds);
    void setQuiz(String quiz);
    KeyPad getKeyPad();
    void setRightAnswers(int number);
    void setWrongAnswers(int number);
}
