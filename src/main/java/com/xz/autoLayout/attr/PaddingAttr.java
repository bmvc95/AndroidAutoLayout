package com.xz.autoLayout.attr;

import android.view.View;

import com.xz.autoLayout.AutoLayoutManager;


/**
 * Created by noahkong on 17-8-23.
 */

public class PaddingAttr extends AutoAttr {
    public PaddingAttr(boolean isDefault, int baseWidth, int baseHeight, float size) {
        super(isDefault, baseWidth, baseHeight, size);
    }

    @Override
    public void auto(View v) {
        if (isDefault) {
            return;
        }
        int left = 0, top = 0, right = 0, bottom = 0;
        left = (int) (size * AutoLayoutManager.getWidthPercent());
        top = (int) (size * AutoLayoutManager.getHeightPercent());
        right = (int) (size * AutoLayoutManager.getWidthPercent());
        bottom = (int) (size * AutoLayoutManager.getHeightPercent());
        if (baseHeight == WIDTH) {
            top = (int) (size * AutoLayoutManager.getWidthPercent());
            bottom = (int) (size * AutoLayoutManager.getWidthPercent());
        } else if (baseHeight == SELF) {
            top = v.getPaddingTop();
            bottom = v.getPaddingBottom();
        }
        if (baseWidth == HEIGHT) {
            left = (int) (size * AutoLayoutManager.getHeightPercent());
            right = (int) (size * AutoLayoutManager.getHeightPercent());
        } else if (baseWidth == SELF) {
            left = v.getPaddingLeft();
            right = v.getPaddingRight();
        }

        v.setPadding(left, top, right, bottom);
    }
}
