package com.xz.autoLayout;

import android.view.View;
import android.view.ViewGroup;


import com.xz.autoLayout.attr.AutoAttr;
import com.xz.autoLayout.attr.HeightAttr;
import com.xz.autoLayout.attr.MarginAttr;
import com.xz.autoLayout.attr.MarginBottomAttr;
import com.xz.autoLayout.attr.MarginLeftAttr;
import com.xz.autoLayout.attr.MarginRightAttr;
import com.xz.autoLayout.attr.MarginTopAttr;
import com.xz.autoLayout.attr.MaxHeightAttr;
import com.xz.autoLayout.attr.MaxWidthAttr;
import com.xz.autoLayout.attr.MinHeightAttr;
import com.xz.autoLayout.attr.MinWidthAttr;
import com.xz.autoLayout.attr.PaddingAttr;
import com.xz.autoLayout.attr.PaddingBottomAttr;
import com.xz.autoLayout.attr.PaddingLeftAttr;
import com.xz.autoLayout.attr.PaddingRightAttr;
import com.xz.autoLayout.attr.PaddingTopAttr;
import com.xz.autoLayout.attr.TextSizeAutoAttr;
import com.xz.autoLayout.attr.WidthAttr;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by noahkong on 17-8-23.
 */

public class AutoLayoutInfo {


    private static final String[] LS = new String[]
            {
                    "layout_width",
                    "layout_height",
                    "textSize",
                    "padding",
                    "paddingLeft",
                    "paddingTop",
                    "paddingRight",
                    "paddingBottom",
                    "layout_margin",
                    "layout_marginLeft",
                    "layout_marginTop",
                    "layout_marginRight",
                    "layout_marginBottom",
                    "maxWidth",
                    "maxHeight",
                    "minWidth",
                    "minHeight",
                    "auto_baseWidth",
                    "auto_baseHeight",
                    "auto_isDefault"

            };

    private static final int LAYOUT_WIDTH = 0;
    private static final int LAYOUT_HEIGHT = 1;
    private static final int TEXT_SIZE = 2;
    private static final int PADDING = 3;
    private static final int PADDING_LEFT = 4;
    private static final int PADDING_TOP = 5;
    private static final int PADDING_RIGHT = 6;
    private static final int PADDING_BOTTOM = 7;
    private static final int LAYOUT_MARGIN = 8;
    private static final int LAYOUT_MARGIN_LEFT = 9;
    private static final int LAYOUT_MARGIN_TOP = 10;
    private static final int LAYOUT_MARGIN_RIGHT = 11;
    private static final int LAYOUT_MARGIN_BOTTOM = 12;
    private static final int MAX_WIDTH = 13;
    private static final int MAX_HEIGHT = 14;
    private static final int MIN_WIDTH = 15;
    private static final int MIN_HEIGHT = 16;

    private static final int BASE_WIDTH = 17;
    private static final int BASE_HEIGHT = 18;
    private static final int IS_DEFAULT = 19;


    private List<AutoAttr> autoAttrs = new ArrayList<>();
    private IAuto iAuto;

    public AutoLayoutInfo(IAuto iAuto) {
        this.iAuto = iAuto;

    }

    private void addAttr(AutoAttr autoAttr) {
        autoAttrs.add(autoAttr);
    }

    private void parse(String[] attrs) {

        int baseWidth = 0;
        int baseHeight = 0;
        boolean isDefault = false;


        for (int i = 0; i < LS.length; i++) {
            String s1 = LS[i];
            for (int j = 0; j < attrs.length; j += 2) {
                String s2 = attrs[j];
                if (s1.equals(s2)) {
                    switch (i) {
                        case BASE_WIDTH:
                            try {
                                baseWidth = Integer.parseInt(attrs[j + 1]);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            break;
                        case BASE_HEIGHT:
                            try {
                                baseHeight = Integer.parseInt(attrs[j + 1]);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            break;
                        case IS_DEFAULT:
                            try {
                                isDefault = Boolean.getBoolean(attrs[j + 1]);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            break;
                    }
                }
            }
        }

        for (int i = 0; i < LS.length; i++) {
            String s1 = LS[i];
            for (int j = 0; j < attrs.length; j += 2) {
                String s2 = attrs[j];
                if (s1.equals(s2)) {
                    String s;
                    switch (i) {
                        case LAYOUT_WIDTH:
                            s = attrs[j + 1];
                            if (isPxValue(s)) {
                                s = s.substring(0, s.length() - 2);
                                autoAttrs.add(new WidthAttr(isDefault, baseWidth, baseHeight, Float.parseFloat(s)));
                            }
                            break;
                        case LAYOUT_HEIGHT:
                            s = attrs[j + 1];
                            if (isPxValue(s)) {
                                s = s.substring(0, s.length() - 2);
                                autoAttrs.add(new HeightAttr(isDefault, baseWidth, baseHeight, Float.parseFloat(s)));
                            }
                            break;
                        case TEXT_SIZE:
                            s = attrs[j + 1];
                            if (isPxValue(s)) {
                                s = s.substring(0, s.length() - 2);
                                autoAttrs.add(new TextSizeAutoAttr(isDefault, baseWidth, baseHeight, Float.parseFloat(s)));
                            }
                            break;
                        case PADDING:
                            s = attrs[j + 1];
                            if (isPxValue(s)) {
                                s = s.substring(0, s.length() - 2);
                                autoAttrs.add(new PaddingAttr(isDefault, baseWidth, baseHeight, Float.parseFloat(s)));
                            }
                            break;
                        case PADDING_LEFT:
                            s = attrs[j + 1];
                            if (isPxValue(s)) {
                                s = s.substring(0, s.length() - 2);
                                autoAttrs.add(new PaddingLeftAttr(isDefault, baseWidth, baseHeight, Float.parseFloat(s)));
                            }
                            break;
                        case PADDING_TOP:
                            s = attrs[j + 1];
                            if (isPxValue(s)) {
                                s = s.substring(0, s.length() - 2);
                                autoAttrs.add(new PaddingTopAttr(isDefault, baseWidth, baseHeight, Float.parseFloat(s)));
                            }
                            break;
                        case PADDING_RIGHT:
                            s = attrs[j + 1];
                            if (isPxValue(s)) {
                                s = s.substring(0, s.length() - 2);
                                autoAttrs.add(new PaddingRightAttr(isDefault, baseWidth, baseHeight, Float.parseFloat(s)));
                            }
                            break;
                        case PADDING_BOTTOM:
                            s = attrs[j + 1];
                            if (isPxValue(s)) {
                                s = s.substring(0, s.length() - 2);
                                autoAttrs.add(new PaddingBottomAttr(isDefault, baseWidth, baseHeight, Float.parseFloat(s)));
                            }
                            break;
                        case LAYOUT_MARGIN:
                            s = attrs[j + 1];
                            if (isPxValue(s)) {
                                s = s.substring(0, s.length() - 2);
                                autoAttrs.add(new MarginAttr(isDefault, baseWidth, baseHeight, Float.parseFloat(s)));
                            }
                            break;
                        case LAYOUT_MARGIN_LEFT:
                            s = attrs[j + 1];
                            if (isPxValue(s)) {
                                s = s.substring(0, s.length() - 2);
                                autoAttrs.add(new MarginLeftAttr(isDefault, baseWidth, baseHeight, Float.parseFloat(s)));
                            }
                            break;
                        case LAYOUT_MARGIN_TOP:
                            s = attrs[j + 1];
                            if (isPxValue(s)) {
                                s = s.substring(0, s.length() - 2);
                                autoAttrs.add(new MarginTopAttr(isDefault, baseWidth, baseHeight, Float.parseFloat(s)));
                            }
                            break;
                        case LAYOUT_MARGIN_RIGHT:
                            s = attrs[j + 1];
                            if (isPxValue(s)) {
                                s = s.substring(0, s.length() - 2);
                                autoAttrs.add(new MarginRightAttr(isDefault, baseWidth, baseHeight, Float.parseFloat(s)));
                            }
                            break;
                        case LAYOUT_MARGIN_BOTTOM:
                            s = attrs[j + 1];
                            if (isPxValue(s)) {
                                s = s.substring(0, s.length() - 2);
                                autoAttrs.add(new MarginBottomAttr(isDefault, baseWidth, baseHeight, Float.parseFloat(s)));
                            }
                            break;
                        case MAX_WIDTH:
                            s = attrs[j + 1];
                            if (isPxValue(s)) {
                                s = s.substring(0, s.length() - 2);
                                autoAttrs.add(new MaxWidthAttr(isDefault, baseWidth, baseHeight, Float.parseFloat(s)));
                            }
                            break;
                        case MAX_HEIGHT:
                            s = attrs[j + 1];
                            if (isPxValue(s)) {
                                s = s.substring(0, s.length() - 2);
                                autoAttrs.add(new MaxHeightAttr(isDefault, baseWidth, baseHeight, Float.parseFloat(s)));
                            }
                            break;
                        case MIN_WIDTH:
                            s = attrs[j + 1];
                            if (isPxValue(s)) {
                                s = s.substring(0, s.length() - 2);
                                autoAttrs.add(new MinWidthAttr(isDefault, baseWidth, baseHeight, Float.parseFloat(s)));
                            }
                            break;
                        case MIN_HEIGHT:
                            s = attrs[j + 1];
                            if (isPxValue(s)) {
                                s = s.substring(0, s.length() - 2);
                                autoAttrs.add(new MinHeightAttr(isDefault, baseWidth, baseHeight, Float.parseFloat(s)));
                            }
                            break;
                    }
                }
            }
        }
        if(iAuto!=null){
            List<AutoAttr> list = iAuto.getAttrs(attrs);
            if(list!=null&&list.size()>0){
                autoAttrs.addAll(list);
            }
        }

    }

    public void execute(View v) {
        try {
            Field filed = v.getClass().getField("mAttributes");
            filed.setAccessible(true);
            String[] str = (String[]) filed.get(v);
            parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (AutoAttr autoAttr : autoAttrs) {
            autoAttr.auto(v);
        }

        adjustChildren(v);
    }


    private void adjustChildren(View v) {
        if (v instanceof ViewGroup) {

            int count = ((ViewGroup) v).getChildCount();
            for (int i = 0; i < count; i++) {
                View v1 = ((ViewGroup) v).getChildAt(i);
                Class<?> c = v1.getClass();//得到Dog对应的Class对象
                Class<?> interfaces[] = c.getInterfaces();//获得Dog所实现的所有接口
                boolean isContinue=false;
                for (Class<?> inte : interfaces) {//打印
                    if (inte == IAuto.class) {
                        isContinue=true;
                        break;
                    }
                }
                if(isContinue){
                    continue;
                }
                AutoLayoutInfo layoutInfo = new AutoLayoutInfo(null);
                try {
                    Field filed = v1.getClass().getField("mAttributes");
                    filed.setAccessible(true);
                    String[] str = (String[]) filed.get(v1);
                    layoutInfo.parse(str);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                layoutInfo.execute(v1);
            }
        }
    }

    private static boolean isPxValue(String str) {

        return str.toLowerCase().endsWith("px");


    }
}
