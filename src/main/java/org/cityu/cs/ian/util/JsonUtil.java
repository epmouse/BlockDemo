package org.cityu.cs.ian.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonUtil {

    public static <T> String toJson(T c) {
        Gson gson = new Gson();
        String json = gson.toJson(c);
        return json;
    }

    public static <T> T fromJson(String json, Class<T> tClass) {
        Gson gson = new Gson();
        try {
            T t = gson.fromJson(json, tClass);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            return null;//解析发生异常则返回null。
        }
    }

    public static  Map<String,String> parseJsonToMap(String json){
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>() {}.getType();
        return gson.fromJson(json,type);
    }

    /**
     * 解析jsonobject相同的jsonArray
     * 说明：该方法会报类型转化错误    linkedList-----》》bean的转化错误
     *
     *
     * @param json
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> List<T> parseSameJsonArray(String json, Class<T> tClass) {
        Gson gson = new Gson();
        List<T> list = gson.fromJson(json, new TypeToken<List<T>>() {
        }.getType());
        return list;
    }

    public static JsonArray getJsonArray(String json) {
        JsonParser parser = new JsonParser();
        JsonArray Jarray = parser.parse(json).getAsJsonArray();
        return Jarray;
    }
    /**
     * 解析成bean的list集合 跟以上的parseSameJsonArray（）方法效果相同，但上面的方法会出现类型转换异常。
     * @param json
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> List<T> getBeanList(String json,Class<T> tClass) {
        ArrayList<T> ts = new ArrayList<>();
        JsonParser parser = new JsonParser();
        JsonArray Jarray = parser.parse(json).getAsJsonArray();
        Gson gson = new Gson();
        for (int i = 0; i < Jarray.size(); i++) {
            T beans = gson.fromJson(Jarray.get(i), tClass);
            ts.add(beans);
        }
        return ts;
    }
}
