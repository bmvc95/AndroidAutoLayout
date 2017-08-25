package com.xz.autoLayout.attr;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.xz.autoLayout.AutoLayoutManager;
import com.xz.autoLayout.AutoLinearLayout;


/**
 * Created by noahkong on 17-8-23.
 */

public class WidthAttr extends AutoAttr {

    public WidthAttr(boolean isDefault, int baseWidth, int baseHeight, float size) {
        super(isDefault, baseWidth, baseHeight, size);
    }


    @Override
    public void auto(View view) {
        if (isDefault || baseWidth == SELF) {
            return;
        }
        ViewGroup.LayoutParams lp = view.getLayoutParams();

        if (baseWidth == WIDTH) {
            lp.width = (int) (size * AutoLayoutManager.getWidthPercent());
        } else if (baseWidth == HEIGHT) {
            lp.width = (int) (size * AutoLayoutManager.getHeightPercent());
        }

    }
}
