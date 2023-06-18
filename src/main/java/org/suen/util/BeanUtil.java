package org.suen.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SunDeZhi
 * @time 2023/4/1
 * @description : bean工具类
 */
public class BeanUtil {


    public static List<Class<?>> getClassByClassPath(List<String> classes){

        List<Class<?>> clazzes = new ArrayList<>();
        for (String aClass : classes) {
            try {
                Class<?> reflectionClass = Class.forName(aClass);
                clazzes.add(reflectionClass);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        return clazzes;
    }


}
