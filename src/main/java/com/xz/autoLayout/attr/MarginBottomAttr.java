package com.xz.autoLayout.attr;

import android.view.View;
import android.view.ViewGroup;

import com.xz.autoLayout.AutoLayoutManager;


/**
 * Created by noahkong on 17-8-24.
 */

public class MarginBottomAttr extends AutoAttr {
    public MarginBottomAttr(boolean isDefault, int baseWidth, int baseHeight, float size) {
        super(isDefault, baseWidth, baseHeight, size);
    }



    @Override
    public void auto(View v) {
        if (isDefault || baseHeight == SELF) {
            return;
        }

        if (!(v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            return;
        }
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) v.getLayoutParams();

        int bottom = (int) (size * AutoLayoutManager.getHeightPercent());


        if (baseHeight == WIDTH||!isVertical) {

            bottom = (int) (size * AutoLayoutManager.getWidthPercent());
        }

        lp.bottomMargin = bottom;
    }
}
