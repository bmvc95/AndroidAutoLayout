package com.xz.autoLayout;

import android.content.Context;
import android.view.View;

/**
 * Created by noahkong on 17-8-24.
 */

public class AutoUtil {

    public static float getPercentWidth(float w) {
        return w * AutoLayoutManager.getWidthPercent();
    }

    public static float getPercentHeight(float h) {
        return h * AutoLayoutManager.getHeightPercent();
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static void auto(View v) {
        AutoLayoutInfo info = new AutoLayoutInfo(null);
        info.execute(v);
    }
}
