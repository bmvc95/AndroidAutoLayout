package com.xz.autoLayout;

import com.xz.autoLayout.attr.AutoAttr;

import java.util.List;

/**
 * Created by noahkong on 17-8-24.
 */

public interface IAuto {
    /**
     * atts是view属性按键值构成的字符串数组
     * 例如 {"layout_width","12px","layout_height","20px"}
     * 这里可以解析自定义的属性，然后返回AutoAttr
     *
     * @param attrs
     * @return
     */
    List<AutoAttr> getAttrs(String[] attrs);

}
