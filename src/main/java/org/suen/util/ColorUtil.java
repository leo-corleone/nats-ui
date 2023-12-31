package org.suen.util;

import javafx.scene.paint.Color;

/**
 * @author: suen
 * @time: 2023/6/24
 * @description:
 **/
public class ColorUtil {

    public static String toHexString(Color color) {
        int r = ((int) Math.round(color.getRed()     * 255)) << 24;
        int g = ((int) Math.round(color.getGreen()   * 255)) << 16;
        int b = ((int) Math.round(color.getBlue()    * 255)) << 8;
        int a = ((int) Math.round(color.getOpacity() * 255));
        return String.format("#%08X", (r + g + b + a));
    }

}
