package org.suen.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @author: suen
 * @time: 2023/7/3
 * @description:
 **/
public class JsonUtil {

    public static boolean isJson(String str){
        boolean isJson = true;
        try {
            JSON.parse(str);
        }catch (Exception e){
            isJson = false;
        }
        return isJson;
    }

    public static String jsonFormat(String str){
        if (!isJson(str)){
            return str;
        }
       return JSON.toJSONString(JSON.parse(str) , SerializerFeature.PrettyFormat ,SerializerFeature.WriteMapNullValue , SerializerFeature.WriteDateUseDateFormat);
    }

}
