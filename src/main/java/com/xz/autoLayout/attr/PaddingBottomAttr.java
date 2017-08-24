package com.xz.autoLayout.attr;

import android.view.View;

import com.xz.autoLayout.AutoLayoutManager;


/**
 * Created by noahkong on 17-8-23.
 */

public class PaddingBottomAttr extends AutoAttr {
    public PaddingBottomAttr(boolean isDefault, int baseWidth, int baseHeight, float size) {
        super(isDefault, baseWidth, baseHeight, size);
    }

    @Override
    public void auto(View v) {
        if (isDefault||baseHeight==SELF) {
            return;
        }
        int bottom = 0;
        bottom = (int) (size * AutoLayoutManager.getHeightPercent());

        if (baseHeight == WIDTH) {
            bottom = (int) (size * AutoLayoutManager.getWidthPercent());
        }
        else if(baseHeight==SELF){
            bottom = v.getPaddingBottom();
        }


        v.setPadding(v.getPaddingLeft(), v.getPaddingTop(), v.getPaddingRight(),bottom);
    }
}
