package com.xz.autoLayout.attr;

import android.view.View;


import com.xz.autoLayout.AutoLayoutManager;

import java.lang.reflect.Method;

/**
 * Created by noahkong on 17-8-24.
 */

public class MaxWidthAttr extends AutoAttr {
    public MaxWidthAttr(boolean isDefault, int baseWidth, int baseHeight, float size) {
        super(isDefault, baseWidth, baseHeight, size);
    }

    @Override
    public void auto(View v) {
        if (isDefault || baseWidth == SELF) {
            return;
        }
        int width = (int) (size * AutoLayoutManager.getWidthPercent());
        if (baseWidth == HEIGHT) {
            width = (int) (size * AutoLayoutManager.getHeightPercent());
        }
        try {
            Method setMaxWidthMethod = v.getClass().getMethod("setMaxWidth", int.class);
            setMaxWidthMethod.invoke(v, width);
        } catch (Exception ignore) {
        }
    }
}
