package com.wang.chat.data;


import com.google.gson.Gson;

/**
 * Created by wang on 16-6-28.
 */
public class JsonHelper {
    private static volatile Gson gson;

    private static Gson getInstance() {
        if (gson == null) {
            synchronized (JsonHelper.class) {
                if (gson == null) {
                    gson = new Gson();
                }
            }
        }
        return gson;
    }

    public static <T> T fromJson(String str, Class<T> clazz) {
        return getInstance().fromJson(str, clazz);
    }

    public static String toJson(Object obj) {
        return getInstance().toJson(obj);
    }


}
