package net.nineoneww.mobile.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import net.nineoneww.mobile.R;

/**
 * Created by lilian on 2017/8/2.
 */

public class IconFontTextView extends AppCompatTextView {
    public IconFontTextView(Context context) {
        super(context);
    }

    public IconFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (attrs == null) {
            return;
        }
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.IconFontTextView);
        final int N = a.getIndexCount();
        for (int i = 0; i < N; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.IconFontTextView_value:
                    String value = a.getString(attr);
                    setText(value);
                    break;
                case R.styleable.IconFontTextView_fontFile:
                    String fontFile = a.getString(attr);
                    try {
                        Typeface typeface = Typeface.createFromAsset(context.getAssets(), fontFile);
                        setTypeface(typeface);
                    } catch (Throwable e) {

                    }
                    break;
            }
        }
        a.recycle();
    }
}
