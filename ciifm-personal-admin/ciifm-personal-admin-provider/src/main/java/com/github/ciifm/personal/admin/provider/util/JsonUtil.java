package com.github.ciifm.personal.admin.provider.util;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * <p></p>
 *
 * @author rui.zhou
 * @date 2018/11/18 0018 18:26
 */
public class JsonUtil {

    public static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
