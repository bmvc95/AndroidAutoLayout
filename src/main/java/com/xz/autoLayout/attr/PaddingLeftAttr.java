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
        int left = 0;
        left = (int) (size * AutoLayoutManager.getWidthPercent());

        if (baseWidth == HEIGHT) {
            left = (int) (size* AutoLayoutManager.getHeightPercent());
        } else if (baseWidth == SELF) {
            left = v.getPaddingLeft();
        }
        v.setPadding(left, v.getPaddingTop(), v.getPaddingRight(), v.getPaddingBottom());
    }
}
