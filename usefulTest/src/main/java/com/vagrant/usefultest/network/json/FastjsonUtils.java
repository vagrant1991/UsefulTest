package com.vagrant.usefultest.network.json;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 使用fastjson解析json数据工具类，需导入fastjson包
 * @author vagrant QQ:513302092
 * @date 创建时间: 2015年4月27日
 */
public class FastjsonUtils {
	
	public static <T> String pojoToString(T t) {
		String json = JSON.toJSONString(t);
		return json;
	}
	
	public static <T> T jsonStringToPojo(String json, Class<T> clazz) {
		T t = JSON.parseObject(json, clazz);
		return t;
	}
	
	public static <T> T jsonObjectToPojo(JSONObject json, Class<T> clazz) {
		String jsonString = json.toString();
		T t = JSON.parseObject(jsonString, clazz);
		return t;
	}
	public static <T> String listPojoToString(List<T> t) {
		return JSON.toJSONString(t);
	}
	public static <T> List<T> jsonStringToListPojo(String json, Class<T> clazz) {
		return JSON.parseArray(json, clazz);
	}
	public static <T> List<T> jsonObjectToListPojo(JSONObject json, Class<T> clazz) {
		String jsonString = json.toString();
		return JSON.parseArray(jsonString, clazz);
	}
}
