package ru.romaberendeev.numbers.android.custom_views;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.romaberendeev.numbers.R;
import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by Admin on 23.01.2017.
 */

public class KeyPad extends FrameLayout {

    public Observable<Integer> observable;

    PublishSubject<Integer> subject = PublishSubject.create();

    public static final int CLEAR = -1;
    public static final int ENTER = 10;

    @BindView(R.id.button0) Button button0;
    @BindView(R.id.button1) Button button1;
    @BindView(R.id.button2) Button button2;
    @BindView(R.id.button3) Button button3;
    @BindView(R.id.button4) Button button4;
    @BindView(R.id.button5) Button button5;
    @BindView(R.id.button6) Button button6;
    @BindView(R.id.button7) Button button7;
    @BindView(R.id.button8) Button button8;
    @BindView(R.id.button9) Button button9;
    @BindView(R.id.button_clear) Button buttonClear;
    @BindView(R.id.button_enter) Button buttonEnter;

    public KeyPad(Context context) {
        super(context);
    }

    public KeyPad(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KeyPad(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public KeyPad(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.key_pad, this, true);
        ButterKnife.bind(this);
        subject = PublishSubject.create();
        button0.setOnClickListener(v -> subject.onNext(0));
        button1.setOnClickListener(v -> subject.onNext(1));
        button2.setOnClickListener(v -> subject.onNext(2));
        button3.setOnClickListener(v -> subject.onNext(3));
        button4.setOnClickListener(v -> subject.onNext(4));
        button5.setOnClickListener(v -> subject.onNext(5));
        button6.setOnClickListener(v -> subject.onNext(6));
        button7.setOnClickListener(v -> subject.onNext(7));
        button8.setOnClickListener(v -> subject.onNext(8));
        button9.setOnClickListener(v -> subject.onNext(9));
        buttonClear.setOnClickListener(v -> subject.onNext(-1));
        buttonEnter.setOnClickListener(v -> subject.onNext(10));
    }

    public Observable<Integer> getObservable(){
        return subject;
    }
}
