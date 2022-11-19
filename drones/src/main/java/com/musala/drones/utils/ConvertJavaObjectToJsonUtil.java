package com.musala.drones.utils;
import com.google.gson.Gson;
public class ConvertJavaObjectToJsonUtil {
	private static Gson Gson = new Gson();

    public static String convertObjectToJson(Object obj) {

        if (obj != null) {
            return obj.getClass().getName().substring(obj.getClass().getName().lastIndexOf(".") + 1) + ":"
                    + Gson.toJson(obj);
        }
        return null;

    }
}
