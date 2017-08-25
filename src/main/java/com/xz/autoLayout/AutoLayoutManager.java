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


    private static float landWidthPercent;
    private static float landHeightPercent;

    private static float portWidthPercent;
    private static float portHeightPercent;

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
        int deviceHeight = displayMetrics.heightPixels - AutoUtil.getNavigationBarHeight(context);
        if (!containStatusBar) {
            deviceHeight -= AutoUtil.getStatusBarHeight(context);
        }
        portWidthPercent = deviceWidth * 1f / width;
        portHeightPercent = deviceHeight * 1f / height;

        landWidthPercent = deviceHeight * 1f / height;
        landHeightPercent = deviceWidth * 1f / width;
        setScreenPortrait(true);
    }

    public static void setScreenPortrait(boolean b) {
        if (b) {
            widthPercent = portWidthPercent;
            heightPercent = portHeightPercent;
        } else {
            heightPercent = landHeightPercent;
            widthPercent = landWidthPercent;
        }
    }

    public static float getWidthPercent() {
        return widthPercent;
    }

    public static float getHeightPercent() {
        return heightPercent;
    }


}
