package com.xz.autoLayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;


import java.lang.annotation.Target;
import java.util.List;

/**
 * Created by noahkong on 17-8-24.
 */

public class AutoFrameLayout extends FrameLayout implements IAuto {
    public AutoFrameLayout(@NonNull Context context) {
        super(context);
    }

    public AutoFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    public AutoFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AutoFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

    }



    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        AutoUtil.auto(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
}
