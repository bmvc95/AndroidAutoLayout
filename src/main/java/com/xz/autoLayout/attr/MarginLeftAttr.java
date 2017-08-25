package com.xz.autoLayout.attr;

import android.view.View;
import android.view.ViewGroup;

import com.xz.autoLayout.AutoLayoutManager;


/**
 * Created by noahkong on 17-8-24.
 */

public class MarginLeftAttr extends AutoAttr {
    public MarginLeftAttr(boolean isDefault, int baseWidth, int baseHeight, float size) {
        super(isDefault, baseWidth, baseHeight, size);
    }



    @Override
    public void auto(View v) {
        if(isDefault||baseWidth==SELF){
            return;
        }
        if (!(v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            return;
        }
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
        int left = (int) (size * AutoLayoutManager.getWidthPercent());
        if (baseWidth == HEIGHT||!isVertical) {
            left = (int) (size * AutoLayoutManager.getHeightPercent());


        }
        lp.leftMargin = left;
    }
}
