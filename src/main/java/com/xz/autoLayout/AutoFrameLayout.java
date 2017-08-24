package com.xz.autoLayout;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.xz.autoLayout.attr.AutoAttr;

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

    public AutoFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }



    @Override
    public List<AutoAttr> getAttrs(String[] attrs) {
        return null;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        AutoLayoutInfo autoLayoutInfo = new AutoLayoutInfo(this);
        autoLayoutInfo.execute(this);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
}
