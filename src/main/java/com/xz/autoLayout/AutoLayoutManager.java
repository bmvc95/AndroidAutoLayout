package com.xz.autoLayout;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * Created by noahkong on 17-8-23.
 */

public class AutoLayoutManager {


    private static float widthPercent;
    private static float heightPercent;

    public static void init(Context context, int width, int height, boolean containStatusBar) {
        try {
            Field filed = View.class.getDeclaredField("mDebugViewAttributes");
            filed.setAccessible(true);
            View v = new View(context);
            filed.setBoolean(v, true);

        } catch (Exception e) {
            e.printStackTrace();
        }

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight = displayMetrics.heightPixels;
        if (!containStatusBar) {
            deviceHeight -= AutoUtil.getStatusBarHeight(context);
        }
        widthPercent = deviceWidth * 1f / width;
        heightPercent = deviceHeight * 1f / height;
    }

    public static float getWidthPercent() {
        return widthPercent;
    }

    public static float getHeightPercent() {
        return heightPercent;
    }


}
