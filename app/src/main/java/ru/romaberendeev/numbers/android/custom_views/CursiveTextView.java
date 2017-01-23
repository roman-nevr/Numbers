package ru.romaberendeev.numbers.android.custom_views;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Admin on 23.01.2017.
 */

public class CursiveTextView extends TextView {
    public CursiveTextView(Context context) {
        super(context);
        createFont();
    }

    public CursiveTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        createFont();
    }

    public CursiveTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        createFont();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CursiveTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        createFont();
    }

    public void createFont() {
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "arial.ttf");
        setTypeface(font, Typeface.ITALIC);
    }
}
