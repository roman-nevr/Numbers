package ru.romaberendeev.numbers.android;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.romaberendeev.numbers.R;
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
    @BindView(R.id.llColorable) LinearLayout llColorable;

    public static final String DIFFICULTY = "difficulty";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ButterKnife.bind(this);
        initDi();
        presenter.init(getIntent().getExtras().getInt(DIFFICULTY));
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

    @Override
    public void showRightAnimation() {
        if(Build.VERSION.SDK_INT > 22){
            showBackgroundAnimation(llColorable, getResources().getColor(R.color.green, getTheme()), 500, 2, ValueAnimator.RESTART);
        }else {
            showBackgroundAnimation(llColorable, getResources().getColor(R.color.green), 500, 2, ValueAnimator.RESTART);
        }
    }

    @Override
    public void showWrongAnimation() {
        if(Build.VERSION.SDK_INT > 22){
            showBackgroundAnimation(llColorable, getResources().getColor(R.color.red, getTheme()), 500, 2, ValueAnimator.RESTART);
        }else {
            showBackgroundAnimation(llColorable, getResources().getColor(R.color.red), 500, 2, ValueAnimator.RESTART);
        }
    }

    @Override
    public Context provideContext() {
        return getApplicationContext();
    }

    private void showBackgroundAnimation(View view, int color, long duration, int repeatNumber, int repeatMode){

        llColorable.setBackgroundColor(color);
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        if (repeatNumber > 1){
            animator.setDuration(duration / repeatNumber);
        }else {
            animator.setDuration(duration);
        }
        if (repeatNumber > 0){
            repeatNumber --;
        }
        animator.setRepeatCount(repeatNumber);
        if ((repeatMode != ValueAnimator.RESTART) || (repeatMode != ValueAnimator.REVERSE)){
            repeatMode = ValueAnimator.RESTART;
        }
        animator.setRepeatMode(repeatMode);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                llColorable.setBackgroundColor(getResources().getColor(R.color.neutral));
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }

    @Override
    public void onBackPressed() {
        presenter.exit();
        super.onBackPressed();
    }

    public static void start(Context context, int difficulty){
        Intent intent = new Intent(context, NumbersActivity.class);
        intent.putExtra(DIFFICULTY, difficulty);
        context.startActivity(intent);
    }
}
