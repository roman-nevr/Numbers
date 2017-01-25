package ru.romaberendeev.numbers.android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.romaberendeev.numbers.R;
import ru.romaberendeev.numbers.android.custom_views.KeyPad;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.new_game) Button btnNewGame;
    @BindView(R.id.options) Button btnOptions;
    @BindView(R.id.records) Button btnRecord;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        ButterKnife.bind(this);
        btnNewGame.setOnClickListener(v -> NumbersActivity.start(this, 1));
    }
}
