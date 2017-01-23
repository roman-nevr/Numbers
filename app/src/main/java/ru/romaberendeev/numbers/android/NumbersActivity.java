package ru.romaberendeev.numbers.android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.romaberendeev.numbers.R;
import ru.romaberendeev.numbers.android.custom_views.CursiveTextView;
import ru.romaberendeev.numbers.android.custom_views.KeyPad;
import ru.romaberendeev.numbers.di.DaggerNumbersViewComponent;
import ru.romaberendeev.numbers.di.NumbersComponent;
import ru.romaberendeev.numbers.di.NumbersViewModule;
import ru.romaberendeev.numbers.presentation.NumbersPresenter;
import ru.romaberendeev.numbers.presentation.NumbersView;

/**
 * Created by Admin on 23.01.2017.
 */

public class NumbersActivity extends AppCompatActivity implements NumbersView {

    @Inject NumbersPresenter presenter;

    @BindView(R.id.key_pad) KeyPad keyPad;
    @BindView(R.id.answer) TextView tvAnswer;
    @BindView(R.id.quiz) TextView tvQuiz;
    @BindView(R.id.timer) TextView tvTimer;
    @BindView(R.id.right_counter) TextView tvRightCounter;
    @BindView(R.id.wrong_counter) TextView tvWrongCounter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ButterKnife.bind(this);
        initDi();
        presenter.init();
    }

    private void initDi(){
        NumbersComponent component = ((App)getApplicationContext()).getNumbersComponent();
        DaggerNumbersViewComponent.builder().numbersComponent(component)
                .numbersViewModule(new NumbersViewModule(this)).build().inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stop();
    }

    @Override
    public void setAnswer(String answer) {
        tvAnswer.setText(answer);
    }

    @Override
    public void addDigitToAnswer(String digit) {
        tvAnswer.setText(tvAnswer.getText().toString() + digit);
    }

    @Override
    public void setTimer(int time) {
        int seconds, minutes;
        seconds = time % 60;
        minutes = time / 60;
        String result = ""+(minutes / 10) + minutes % 10 + ":" + (seconds / 10) + seconds % 10;
        tvTimer.setText(result);
    }

    @Override
    public void setQuiz(String quiz) {
        tvQuiz.setText(quiz);
        //tvAnswer.setText("");
    }

    @Override
    public KeyPad getKeyPad() {
        return keyPad;
    }

    @Override
    public void setRightAnswers(int number) {
        tvRightCounter.setText("" + number);
    }

    @Override
    public void setWrongAnswers(int number) {
        tvWrongCounter.setText("" + number);
    }
}
