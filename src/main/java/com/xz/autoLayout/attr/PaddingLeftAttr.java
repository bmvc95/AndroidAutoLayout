package com.xz.autoLayout.attr;

import android.view.View;

import com.xz.autoLayout.AutoLayoutManager;


/**
 * Created by noahkong on 17-8-23.
 */

public class PaddingLeftAttr extends AutoAttr {
    public PaddingLeftAttr(boolean isDefault, int baseWidth, int baseHeight, float size) {
        super(isDefault, baseWidth, baseHeight, size);
    }


    @Override
    public void auto(View v) {
        if (isDefault || baseWidth == SELF) {
            return;
        }
        int left = (int) (size * AutoLayoutManager.getWidthPercent());

        if (baseWidth == HEIGHT||!isVertical) {
            left = (int) (size* AutoLayoutManager.getHeightPercent());
        }

        v.setPadding(left, v.getPaddingTop(), v.getPaddingRight(), v.getPaddingBottom());
    }
}
