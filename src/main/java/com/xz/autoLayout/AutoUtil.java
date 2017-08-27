package com.xz.autoLayout;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by noahkong on 17-8-24.
 */

public class AutoUtil {

    private final static String W = "!w";
    private final static String H = "!h";

    private final static String M = "!m";
    private final static String ML = "!ml";
    private final static String MT = "!mt";
    private final static String MR = "!mr";
    private final static String MB = "!mb";

    private final static String P = "!p";

    private final static String PL = "!pl";
    private final static String PT = "!pt";
    private final static String PR = "!pr";
    private final static String PB = "!pb";

    private final static String MAW = "!maw";
    private final static String MAH = "!mah";

    private final static String MIW = "!miw";
    private final static String MIH = "!mih";

    private final static String TS = "!ts";

    private final static String NOT = "not";


    private final static String TS_W = "ts-w";

    private final static String W_H = "w-h";
    private final static String H_W = "h-w";


    private final static String ML_H = "ml-h";
    private final static String MT_W = "mt-w";
    private final static String MR_H = "mr-h";
    private final static String MB_W = "mb-w";


    private final static String PL_H = "pl-h";
    private final static String PT_W = "pt-w";
    private final static String PR_H = "pr-h";
    private final static String PB_W = "pb-w";

    private final static String MAW_H = "maw-h";
    private final static String MAH_W = "mah-w";

    private final static String MIW_H = "miw-h";
    private final static String MIH_W = "mih-w";

    private final static int WIDTH = 0;
    private final static int HEIGHT = 1;


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

    public static boolean checkDeviceHasNavigationBar(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {

        }

        return hasNavigationBar;

    }

    public static int getNavigationBarHeight(Context context) {
        int result = 0;
        if (checkDeviceHasNavigationBar(context)) {
            Resources res = context.getResources();
            int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = res.getDimensionPixelSize(resourceId);
            }
        }
        return result;
    }

    public static boolean isScreenOriatationPortrait(Context context) {

        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    public static void auto(View v) {
        AutoLayoutManager.setScreenPortrait(AutoUtil.isScreenOriatationPortrait(v.getContext()));
        autoView(v);
    }

    private static void autoView(View v) {
        autoChild(v);
        if (v instanceof ViewGroup) {

            int count = ((ViewGroup) v).getChildCount();
            for (int i = 0; i < count; i++) {
                View v1 = ((ViewGroup) v).getChildAt(i);
                if (v1 instanceof IAuto) {
                    continue;
                }
                autoView(v1);
            }
        }
    }

    private static void autoChild(View v) {
        List<String> arraysList = new ArrayList<>();

        String tag = (String) v.getTag();
        if (tag != null) {
            tag = tag.trim();
            String[] arrays = tag.split(",");

            for (String str : arrays) {
                arraysList.add(str.trim().toLowerCase());
            }
        }


        if (arraysList.contains(NOT)) {
            return;
        }
        if (!arraysList.contains(W)) {
            if (arraysList.contains(W_H)) {
                autoWidth(v, HEIGHT);
            } else {
                autoWidth(v, WIDTH);
            }

        }


        if (!arraysList.contains(H)) {

            if (arraysList.contains(H_W)) {
                autoHeight(v, WIDTH);
            } else {
                autoHeight(v, HEIGHT);
            }
        }

        if (!arraysList.contains(M)) {
            if (!arraysList.contains(ML)) {
                if (arraysList.contains(ML_H)) {
                    autoMarginLeft(v, HEIGHT);
                } else {
                    autoMarginLeft(v, WIDTH);
                }
            }

            if (!arraysList.contains(MT)) {
                if (arraysList.contains(MT_W)) {
                    autoMarginTop(v, WIDTH);
                } else {
                    autoMarginTop(v, HEIGHT);
                }
            }
            if (!arraysList.contains(MR)) {

                if (arraysList.contains(MR_H)) {
                    autoMarginRight(v, HEIGHT);
                } else {
                    autoMarginRight(v, WIDTH);

                }
            }
            if (!arraysList.contains(MB)) {
                if (arraysList.contains(MB_W)) {
                    autoMarginBottom(v, WIDTH);
                } else {
                    autoMarginBottom(v, HEIGHT);
                }
            }
        }

        if (!arraysList.contains(P)) {
            if (!arraysList.contains(PL)) {
                if (arraysList.contains(PL_H)) {
                    autoPaddingLeft(v, HEIGHT);
                } else {
                    autoPaddingLeft(v, WIDTH);
                }
            }
            if (!arraysList.contains(PT)) {

                if (arraysList.contains(PT_W)) {
                    autoPaddingTop(v, WIDTH);
                } else {
                    autoPaddingTop(v, HEIGHT);
                }
            }
            if (!arraysList.contains(PR)) {
                if (arraysList.contains(PR_H)) {
                    autoPaddingRight(v, HEIGHT);
                } else {
                    autoPaddingRight(v, WIDTH);
                }
            }
            if (!arraysList.contains(PB)) {
                if (arraysList.contains(PB_W)) {
                    autoPaddingBottom(v, WIDTH);
                } else {
                    autoPaddingBottom(v, HEIGHT);
                }
            }
        }

        if (!arraysList.contains(MAW)) {
            if (arraysList.contains(MAW_H)) {
                autoMaxWidth(v, HEIGHT);
            } else {
                autoMaxWidth(v, WIDTH);
            }
        }
        if (!arraysList.contains(MAH)) {
            if (arraysList.contains(MAH_W)) {
                autoMaxHeight(v, WIDTH);
            } else {
                autoMaxHeight(v, HEIGHT);
            }
        }
        if (!arraysList.contains(MIW)) {

            if (arraysList.contains(MIW_H)) {
                autoMinWidth(v, HEIGHT);
            } else {
                autoMinWidth(v, WIDTH);
            }
        }
        if (!arraysList.contains(MIH)) {
            if (arraysList.contains(MIH_W)) {
                autoMinHeight(v, WIDTH);
            } else {
                autoMinHeight(v, HEIGHT);
            }
        }


        if (!arraysList.contains(TS)) {
            if (arraysList.contains(TS_W)) {
                autoTextSize(v, WIDTH);
            } else {
                autoTextSize(v, HEIGHT);
            }
        }


    }

    private static void autoTextSize(View v, int base) {
        if (v instanceof TextView) {
            float size = ((TextView) v).getTextSize();
            if (base == WIDTH) {
                size = size * AutoLayoutManager.getWidthPercent()*5/6;
            } else if (base == HEIGHT) {
                size = size * AutoLayoutManager.getHeightPercent()*5/6;
            }
            ((TextView) v).setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
        }
    }

    private static void autoWidth(View v, int base) {

        ViewGroup.LayoutParams lp = v.getLayoutParams();
        if (lp == null) {
            return;
        }
        if (lp.width > 0) {
            int size = lp.width;

            if (base == WIDTH) {
                size = (int) (size * AutoLayoutManager.getWidthPercent());
            } else if (base == HEIGHT) {
                size = (int) (size * AutoLayoutManager.getHeightPercent());
            }

            lp.width = size;
        }
    }

    private static void autoHeight(View v, int base) {
        ViewGroup.LayoutParams lp = v.getLayoutParams();
        if (lp == null) {
            return;
        }
        if (lp.height > 0) {
            int size = lp.height;
            if (base == WIDTH) {
                size = (int) (size * AutoLayoutManager.getWidthPercent());
            } else if (base == HEIGHT) {
                size = (int) (size * AutoLayoutManager.getHeightPercent());
            }

            lp.height = size;
        }
    }

    private static void autoMargin(View v, int base) {
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
        if (lp != null) {
            int left = (int) (lp.leftMargin * AutoLayoutManager.getWidthPercent());
            int top = (int) (lp.topMargin * AutoLayoutManager.getHeightPercent());
            int right = (int) (lp.rightMargin * AutoLayoutManager.getWidthPercent());
            int bottom = (int) (lp.bottomMargin * AutoLayoutManager.getHeightPercent());
            lp.leftMargin = left;
            lp.topMargin = top;
            lp.rightMargin = right;
            lp.bottomMargin = bottom;

        }
    }

    private static void autoMarginLeft(View v, int base) {
        try {
            ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            if (lp != null) {
                int size = lp.leftMargin;
                if (base == WIDTH) {
                    size = (int) (size * AutoLayoutManager.getWidthPercent());
                } else if (base == HEIGHT) {
                    size = (int) (size * AutoLayoutManager.getHeightPercent());
                }
                lp.leftMargin = size;

            }

        } catch (Exception e) {

        }

    }

    private static void autoMarginRight(View v, int base) {
        try {
            ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            if (lp != null) {
                int size = lp.rightMargin;
                if (base == WIDTH) {
                    size = (int) (size * AutoLayoutManager.getWidthPercent());
                } else if (base == HEIGHT) {
                    size = (int) (size * AutoLayoutManager.getHeightPercent());
                }
                lp.rightMargin = size;

            }
        } catch (Exception e) {

        }

    }

    private static void autoMarginTop(View v, int base) {
        try {
            ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            if (lp != null) {
                int size = lp.topMargin;
                if (base == WIDTH) {
                    size = (int) (size * AutoLayoutManager.getWidthPercent());
                } else if (base == HEIGHT) {
                    size = (int) (size * AutoLayoutManager.getHeightPercent());
                }
                lp.topMargin = size;

            }
        } catch (Exception e) {

        }

    }

    private static void autoMarginBottom(View v, int base) {
        try {
            ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            if (lp != null) {
                int size = lp.bottomMargin;
                if (base == WIDTH) {
                    size = (int) (size * AutoLayoutManager.getWidthPercent());
                } else if (base == HEIGHT) {
                    size = (int) (size * AutoLayoutManager.getHeightPercent());
                }
                lp.bottomMargin = size;

            }
        } catch (Exception e) {

        }

    }

    private static void autoPadding(View v, int base) {
        int left = (int) (v.getPaddingLeft() * AutoLayoutManager.getWidthPercent());
        int top = (int) (v.getPaddingTop() * AutoLayoutManager.getHeightPercent());
        int right = (int) (v.getPaddingRight() * AutoLayoutManager.getWidthPercent());
        int bottom = (int) (v.getPaddingBottom() * AutoLayoutManager.getHeightPercent());
        v.setPadding(left, top, right, bottom);
    }

    private static void autoPaddingLeft(View v, int base) {
        int size = v.getPaddingLeft();
        if (base == WIDTH) {
            size = (int) (size * AutoLayoutManager.getWidthPercent());
        } else if (base == HEIGHT) {
            size = (int) (size * AutoLayoutManager.getHeightPercent());
        }
        v.setPadding(size, v.getPaddingTop(), v.getPaddingRight(), v.getPaddingBottom());
    }

    private static void autoPaddingRight(View v, int base) {
        int size = v.getPaddingRight();
        if (base == WIDTH) {
            size = (int) (size * AutoLayoutManager.getWidthPercent());
        } else if (base == HEIGHT) {
            size = (int) (size * AutoLayoutManager.getHeightPercent());
        }
        v.setPadding(v.getPaddingLeft(), v.getPaddingTop(), size, v.getPaddingBottom());
    }

    private static void autoPaddingTop(View v, int base) {
        int size = v.getPaddingTop();
        if (base == WIDTH) {
            size = (int) (size * AutoLayoutManager.getWidthPercent());
        } else if (base == HEIGHT) {
            size = (int) (size * AutoLayoutManager.getHeightPercent());
        }
        v.setPadding(v.getPaddingLeft(), size, v.getPaddingRight(), v.getPaddingBottom());
    }

    private static void autoPaddingBottom(View v, int base) {
        int size = v.getPaddingBottom();
        if (base == WIDTH) {
            size = (int) (size * AutoLayoutManager.getWidthPercent());
        } else if (base == HEIGHT) {
            size = (int) (size * AutoLayoutManager.getHeightPercent());
        }
        v.setPadding(v.getPaddingLeft(), v.getPaddingTop(), v.getPaddingRight(), size);
    }

    private static void autoMaxWidth(View v, int base) {

    }

    private static void autoMaxHeight(View v, int base) {

    }

    private static void autoMinWidth(View v, int base) {
        int size = v.getMinimumWidth();
        if (base == WIDTH) {
            size = (int) (size * AutoLayoutManager.getWidthPercent());
        } else if (base == HEIGHT) {
            size = (int) (size * AutoLayoutManager.getHeightPercent());
        }
        v.setMinimumWidth(size);
    }

    private static void autoMinHeight(View v, int base) {
        int size = v.getMinimumHeight();
        if (base == WIDTH) {
            size = (int) (size * AutoLayoutManager.getWidthPercent());
        } else if (base == HEIGHT) {
            size = (int) (size * AutoLayoutManager.getHeightPercent());
        }
        v.setMinimumHeight(size);
    }
}
