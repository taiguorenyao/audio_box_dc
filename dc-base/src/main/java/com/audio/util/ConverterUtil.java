
package com.audio.util;

import com.google.gson.Gson;

import java.util.Map;

public class ConverterUtil {

	public static Gson gson = new Gson();

	@SuppressWarnings("unchecked")
	public static <T> Object json2Object(String json, Object obj) {
		if (null != json) {
			try {
				return gson.fromJson(json, (Class<T>) obj);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static <T> String mapToJson(Map<T, T> map) {
		return object2Json(map);
	}

	public static String object2Json(Object obj) {
		try {
			return gson.toJson(obj);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
