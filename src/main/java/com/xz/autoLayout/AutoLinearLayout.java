package com.xz.autoLayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

import com.xz.autoLayout.attr.AutoAttr;

import java.util.List;

/**
 * Created by noahkong on 17-8-23.
 */

public class AutoLinearLayout extends LinearLayout implements IAuto {
    public AutoLinearLayout(Context context) {
        super(context);
    }

    public AutoLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(value = Build.VERSION_CODES.LOLLIPOP)
    public AutoLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
