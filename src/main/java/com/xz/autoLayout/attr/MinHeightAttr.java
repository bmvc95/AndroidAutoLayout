package com.xz.autoLayout.attr;

import android.view.View;

import com.xz.autoLayout.AutoLayoutManager;


/**
 * Created by noahkong on 17-8-24.
 */

public class MinHeightAttr extends AutoAttr {
    public MinHeightAttr(boolean isDefault, int baseWidth, int baseHeight, float size) {
        super(isDefault, baseWidth, baseHeight, size);
    }



    @Override
    public void auto(View v) {
        if (isDefault || baseHeight == SELF) {
            return;
        }
        int height = (int) (size * AutoLayoutManager.getHeightPercent());
        if (baseHeight == WIDTH) {
            height = (int) (size * AutoLayoutManager.getWidthPercent());
        }
        v.setMinimumHeight(height);
    }
}
