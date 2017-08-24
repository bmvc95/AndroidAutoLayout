package com.xz.autoLayout.attr;

import android.view.View;
import android.view.ViewGroup;

import com.xz.autoLayout.AutoLayoutManager;


/**
 * Created by noahkong on 17-8-23.
 */

public class MarginAttr extends AutoAttr {
    public MarginAttr(boolean isDefault, int baseWidth, int baseHeight, float size) {
        super(isDefault, baseWidth, baseHeight, size);
    }

    @Override
    public void auto(View v) {
        if (isDefault) {
            return;
        }

        if (!(v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            return;
        }
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
        int left = (int) (size * AutoLayoutManager.getWidthPercent());
        int top = (int) (size * AutoLayoutManager.getHeightPercent());
        int right = (int) (size * AutoLayoutManager.getWidthPercent());
        int bottom = (int) (size * AutoLayoutManager.getHeightPercent());

        if (baseWidth == HEIGHT) {
            left = (int) (size * AutoLayoutManager.getHeightPercent());

            right = (int) (size * AutoLayoutManager.getHeightPercent());
        } else if (baseWidth == SELF) {
            left = lp.leftMargin;
            right = lp.rightMargin;
        }

        if (baseHeight == WIDTH) {
            top = (int) (size * AutoLayoutManager.getWidthPercent());

            bottom = (int) (size * AutoLayoutManager.getWidthPercent());
        } else if (baseHeight == SELF) {
            top = lp.topMargin;
            bottom = lp.bottomMargin;
        }

        lp.leftMargin = left;
        lp.topMargin = top;
        lp.rightMargin = right;
        lp.bottomMargin = bottom;
    }
}
