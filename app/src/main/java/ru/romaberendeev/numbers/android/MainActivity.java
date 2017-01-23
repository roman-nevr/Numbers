package ru.romaberendeev.numbers.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.romaberendeev.numbers.R;
import ru.romaberendeev.numbers.android.custom_views.KeyPad;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.key_pad) KeyPad keyPad;
    @BindView(R.id.answer) TextView tvAnswer;

    private CompositeSubscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ButterKnife.bind(this);
        subscription = new CompositeSubscription();
    }

    @Override
    protected void onStart() {
        super.onStart();
        subscription.add(digitalKeyEvent());
    }

    private Subscription digitalKeyEvent() {
        return keyPad.getObservable().filter(this::isDigit).subscribe(d -> addDigitToAnswer(d));
    }

    private Boolean isDigit(Integer integer) {
        return integer >= 0 && integer < 10;
    }

    private void addDigitToAnswer(Integer digit){
        tvAnswer.setText(tvAnswer.getText().toString() + digit);
    }
}
