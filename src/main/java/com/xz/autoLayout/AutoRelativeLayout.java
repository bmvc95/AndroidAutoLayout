package com.xz.autoLayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

import com.xz.autoLayout.attr.AutoAttr;

import java.util.Arrays;
import java.util.List;

/**
 * Created by noahkong on 17-8-24.
 */

public class AutoRelativeLayout extends RelativeLayout implements IAuto {
    public AutoRelativeLayout(Context context) {
        super(context);
    }

    public AutoRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @TargetApi(value = Build.VERSION_CODES.LOLLIPOP)
    public AutoRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {

        return super.generateLayoutParams(attrs);
    }
}
