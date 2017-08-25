package com.xz.autoLayout.attr;

import android.view.View;
import android.view.ViewGroup;

import com.xz.autoLayout.AutoLayoutManager;


/**
 * Created by noahkong on 17-8-23.
 */

public class HeightAttr extends AutoAttr {
    public HeightAttr(boolean isDefault, int baseWidth, int baseHeight, float size) {
        super(isDefault, baseWidth, baseHeight, size);
    }



    @Override
    public void auto(View view) {
        if (isDefault||baseHeight==SELF) {
            return;
        }
        ViewGroup.LayoutParams lp = view.getLayoutParams();

        if(baseHeight==WIDTH||!isVertical){
            lp.height = (int) (size * AutoLayoutManager.getWidthPercent());
        }else if(baseHeight==HEIGHT){
            lp.height = (int) (size * AutoLayoutManager.getHeightPercent());
        }

    }
}
