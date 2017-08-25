package com.xz.autoLayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;



import java.util.List;

/**
 * Created by noahkong on 17-8-24.
 */

public class AutoRelativeLayout extends RelativeLayout implements IAuto {
    public AutoRelativeLayout(Context context) {
        super(context);
        init();
    }

    public AutoRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AutoRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    @TargetApi(value = Build.VERSION_CODES.LOLLIPOP)
    public AutoRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private void init(){
        post(new Runnable() {
            @Override
            public void run() {
                AutoUtil.auto(AutoRelativeLayout.this);
            }
        });
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
}
