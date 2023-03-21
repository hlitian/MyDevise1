package com.lxd.mydevise.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * @Author lixd
 */
public class GsonUtils {

    private static final Gson gson = createGson();

    public static String toJson(final Object object){
        return gson.toJson(object);
    }

    public static <T> T fromJson(final String json,final Type type){
return gson.fromJson(json,type);
    }

    private static Gson createGson(){
        final GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        return builder.create();
    }
}
