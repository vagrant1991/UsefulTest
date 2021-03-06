package com.vagrant.usefultest.network.json;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
/**
 * GsonBuilder实现Gson常用解析工 参考： https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/GsonBuilder.html
 * @author vagrant QQ:513302092
 * @date 创建时间: 2015年4月27日
 */
public class GsonBuilderTool {
	private static Gson gson;
	static { 
        Gson gson = new GsonBuilder()
        .excludeFieldsWithoutExposeAnnotation() //不导出实体中没有用@Expose注解的属性
        .enableComplexMapKeySerialization() //支持Map的key为复杂对象的形式
        .serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss:SSS")//时间转化为特定格式 
        .setPrettyPrinting() //对json结果格式化.
        .setVersion(1.0).create();
        //有的字段不是一开始就有的,会随着版本的升级添加进来,那么在进行序列化和返序列化的时候就会根据版本号来选择是否要序列化.
        //@Since(版本号)能完美地实现这个功能.还的字段可能,随着版本的升级而删除,那么
        //@Until(版本号)也能实现这个功能,GsonBuilder.setVersion(double)方法需要调用.
        
	}
	public static <T> String pojoToJson(T t) {
		return gson.toJson(t);
	}

	public static <T> T jsonStringToPojo(String json, Class<T> clazz) {
		T t = null;
		try {
			t = gson.fromJson(json, clazz);
			return t;
		} catch (JsonParseException e) {
			e.printStackTrace();
		}
		return t;
	}

	public static <T> T jsonObjectToPojo(JSONObject json, Class<T> clazz) {
		String jsonString = json.toString();
		T t = null;
		try {
			t = gson.fromJson(jsonString, clazz);
			return t;
		} catch (JsonParseException e) {
			e.printStackTrace();
		}
		return t;
	}

	public static <T> String listPojoToJson(List<T> t) {
		return gson.toJson(t);
	}
	
	public static <T> List<T> jsonStringToListPojo(String json, Class<T> clazz) {
		List<T> list = null;
		try {
			list = gson.fromJson(json, new TypeToken<List<T>>() {
			}.getType());
		} catch (JsonParseException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static <T> List<T> jsonObjectToListPojo(JSONObject json, Class<T> clazz) {
		String jsonString = json.toString();
		List<T> list = null;
		try {
			list = gson.fromJson(jsonString, new TypeToken<List<T>>() {
			}.getType());
			return list;
		} catch (JsonParseException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static <K, V> String mapPojoToJson(Map<K, V> map) {
		return gson.toJson(map);
	}
	
	public static <K, V> Map<K, V> jsonStringToMapPojo(String json, Class<K> clazzK,
			Class<V> clazzV) {
		Type mapType = new TypeToken<Map<K, V>>() {
		}.getType();
		Map<K, V> map = null;
		try {
			gson.fromJson(json, mapType);
		} catch (JsonParseException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public static <K, V> Map<K, V> jsonObjectToMapPojo(JSONObject json, Class<K> clazzK, Class<V> clazzV) {
		String jsonString = json.toString();
		Type mapType = new TypeToken<Map<K, V>>() {
		}.getType();
		Map<K, V> map = null;
		try {
			gson.fromJson(jsonString, mapType);
		} catch (JsonParseException e) {
			e.printStackTrace();
		}
		return map;
	}
}
