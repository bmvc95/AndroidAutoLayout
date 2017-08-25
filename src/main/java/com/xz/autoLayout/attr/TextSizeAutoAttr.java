package com.xz.autoLayout.attr;

import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.xz.autoLayout.AutoLayoutManager;


/**
 * Created by noahkong on 17-8-23.
 */

public class TextSizeAutoAttr extends AutoAttr {
    public TextSizeAutoAttr(boolean isDefault, int baseWidth, int baseHeight, float size) {
        super( isDefault, baseWidth, baseHeight, size);
    }



    @Override
    public void auto(View view) {
        if(isDefault||baseHeight==SELF){
            return;
        }
        if(view instanceof TextView){
            float  textSize  = AutoLayoutManager.getHeightPercent()*size;
            if(baseHeight==WIDTH){
                textSize = AutoLayoutManager.getWidthPercent()*size;
            }

            ((TextView) view).setIncludeFontPadding(false);
            ((TextView) view).setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        }
    }
}
