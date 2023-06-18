package org.suen.util;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SunDeZhi
 * @time 2023/4/1
 * @description : 扫描包工具类
 */
public class PackageUtil {


    public static List<String> getClassByPackageName(String packageName){

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource(packageName.replace("." , "/"));
        File rootFile = new File(url.getFile());
        File[] files = rootFile.listFiles();

        List<String> classes = new ArrayList<>();

        for (File file : files) {
            if (file.isFile()){
                if (file.getName().endsWith(".class")){
                    classes.add(packageName.concat("." + file.getName().replace(".class" , "")));
                }
            }else{
                classes.addAll(getClassByPackageName(packageName.concat("."+file.getName())));
            }
        }

        return classes;
    }


}
