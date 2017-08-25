package com.xz.autoLayout.attr;

import android.view.View;

import com.xz.autoLayout.AutoLayoutManager;


/**
 * Created by noahkong on 17-8-23.
 */

public class PaddingTopAttr extends AutoAttr {
    public PaddingTopAttr(boolean isDefault, int baseWidth, int baseHeight, float size) {
        super(isDefault, baseWidth, baseHeight, size);
    }



    @Override
    public void auto(View v) {
        if (isDefault||baseHeight==SELF) {
            return;
        }

        int top = (int) (size * AutoLayoutManager.getHeightPercent());

        if (baseHeight == WIDTH) {
            top = (int) (size * AutoLayoutManager.getWidthPercent());
        }


        v.setPadding(v.getPaddingLeft(), top, v.getPaddingRight(), v.getPaddingBottom());
    }
}
