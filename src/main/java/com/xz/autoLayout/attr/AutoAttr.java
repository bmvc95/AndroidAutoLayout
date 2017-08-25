package com.xz.autoLayout.attr;

import android.view.View;

/**
 * Created by noahkong on 17-8-23.
 */

public abstract class AutoAttr {

    protected static final int SELF = -1;
    protected static final int HEIGHT = 1;
    protected static final int WIDTH = 2;



    protected boolean isDefault = false;
    protected int baseWidth = WIDTH;
    protected int baseHeight = HEIGHT;
    protected float size;
    protected boolean isVertical=true;

    public AutoAttr( boolean isDefault, int baseWidth, int baseHeight, float size) {

        this.isDefault = isDefault;
        baseWidth = baseWidth == 0 ? WIDTH : baseWidth;
        baseHeight = baseHeight == 0 ? HEIGHT : baseHeight;
        this.baseWidth = baseWidth;
        this.baseHeight = baseHeight;
        this.size = size;
    }

    public AutoAttr(boolean isDefault, int baseWidth, int baseHeight, float size, boolean isVertical) {
        this.isDefault = isDefault;
        baseWidth = baseWidth == 0 ? WIDTH : baseWidth;
        baseHeight = baseHeight == 0 ? HEIGHT : baseHeight;
        this.baseWidth = baseWidth;
        this.baseHeight = baseHeight;
        this.size = size;
        this.isVertical = isVertical;
    }

    public abstract void auto(View v);
}
