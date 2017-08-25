package com.xz.autoLayout.attr;

import android.view.View;


import com.xz.autoLayout.AutoLayoutManager;

import java.lang.reflect.Method;

/**
 * Created by noahkong on 17-8-24.
 */

public class MaxHeightAttr extends AutoAttr {
    public MaxHeightAttr(boolean isDefault, int baseWidth, int baseHeight, float size) {
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
        try {
            Method setMaxWidthMethod = v.getClass().getMethod("setMaxHeight", int.class);
            setMaxWidthMethod.invoke(v, height);
        } catch (Exception ignore) {
        }
    }
}
