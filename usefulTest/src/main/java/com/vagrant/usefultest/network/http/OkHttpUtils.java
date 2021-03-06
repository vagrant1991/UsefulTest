package com.vagrant.usefultest.network.http;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/** 使用OkHttp库访问网络,返回网络响应,具体用法参考方法注释,使用本类需要导入OkHttp库到项目中，具体用法参考方法注释
 * @author vagrant QQ:513302092
 * @date 创建时间: 2015年4月25日
 */
public class OkHttpUtils {
	private static OkHttpClient client;
	private static Request request;
	private static String path;
	private static JSONObject result;
	
	static {
		client = new OkHttpClient();
		client.setConnectTimeout(15, TimeUnit.SECONDS);
	}
	/**
	 * 使用OkHttp通过get方式得到服务器返回数据
	 * @param url 包含参数对的服务器地址
	 * @return string格式服务器响应数据
	 * @throws IOException
	 */
	public static String getWithOkHttp(String url) throws IOException {
			request = new Request.Builder().url(url).build();
		    Response response = client.newCall(request).execute();
		    if (response.isSuccessful()) {
		        return response.body().string();
		    } else {
		        throw new IOException("Unexpected code " + response);
		    }
	}
	/**
	 * 使用OkHttp通过get方式得到服务器返回数据
	 * @param url 服务器地址
	 * @param name 参数name值
	 * @param value 参数value值
	 * @return string格式服务器响应数据
	 * @throws IOException
	 */
	public static String getWithOkHttp(String url, String name, String value) throws IOException{
			path = attachHttpGetParam(url, name, value);
		 	request = new Request.Builder().url(path).build();
		    Response response = client.newCall(request).execute();
		    if (response.isSuccessful()) {
		        return response.body().string();
		    } else {
		        throw new IOException("Unexpected code " + response);
		    }
	}
	/**
	 * 使用OkHttp通过get方式得到服务器返回数据
	 * @param url 服务器地址
	 * @param params List<BasicNameValuePair>形式参数对
	 * @return string格式服务器响应数据
	 * @throws IOException
	 */
	public static String getWithOkHttp(String url, List<BasicNameValuePair> params) throws IOException{
		path = attachHttpGetParams(url, params);
	 	request = new Request.Builder().url(path).build();
	    Response response = client.newCall(request).execute();
	    if (response.isSuccessful()) {
	        return response.body().string();
	    } else {
	        throw new IOException("Unexpected code " + response);
	    }
	}
	/**
	 * 使用OkHttp通过get方式得到服务器返回数据
	 * @param url 服务器地址
	 * @param params Map<String, String> 
	 * @return string格式服务器响应数据
	 * @throws IOException
	 */
	public static String getWithOkHttp(String url, Map<String, String> params) throws IOException{
		path = attacHttpGetParams(url, params);
	 	request = new Request.Builder().url(path).build();
	    Response response = client.newCall(request).execute();
	    if (response.isSuccessful()) {
	        return response.body().string();
	    } else {
	        throw new IOException("Unexpected code " + response);
	    }
	}
	/**
	 * 使用OkHttp通过post方式向服务器传递数据
	 * @param baseUrl 服务器地址
	 * @param key 键
	 * @param value 值
	 * @return string格式服务器响应数据
	 * @throws IOException
	 */
	public static String postWithOkHttp(String baseUrl, String key, String value) throws IOException{
		RequestBody body = new FormEncodingBuilder().add(key, value).build();
		request = new Request.Builder().url(baseUrl).post(body).build();
		Response response = client.newCall(request).execute();
		if (response.isSuccessful()) {
			return response.body().toString();
		} else {
			throw new IOException("Unexpected code " + response);
		}
	}
	/**
	 * 使用OkHttp通过post方式向服务器传递数据
	 * @param baseUrl 服务器地址
	 * @param params List<BasicNameValuePair>形式参数对
	 * @return string格式服务器响应数据
	 * @throws IOException
	 */
	public static String postWithOkHttp(String baseUrl, List<BasicNameValuePair> params) throws IOException {
		String param = URLEncodedUtils.format(params, "utf-8");
		FormEncodingBuilder buider = new FormEncodingBuilder();
		String[] pairs = param.split("&");
		for (int i = 0 ; i < pairs.length; i++) {
			String[] keyAndValue = pairs[i].split("=");
			buider.add(keyAndValue[0], keyAndValue[1]);
		}
		RequestBody body = buider.build();
		request = new Request.Builder().url(baseUrl).post(body).build();
		Response response = client.newCall(request).execute();
		if (response.isSuccessful()) {
			return response.body().toString();
		} else {
			throw new IOException("Unexpected code " + response);
		}
	}

	/**
	 * 使用OkHttp通过post方式向服务器传递数据
	 * @param baseUrl 服务器地址
	 * @param params Map<String, String>形式参数对
	 * @return  string格式服务器响应数据
	 * @throws IOException
	 */
	public static String postWithOkHttp(String baseUrl, Map<String, String> params) throws IOException {
		Set<Entry<String, String>> set = params.entrySet();
		FormEncodingBuilder buider = new FormEncodingBuilder();
		for (Iterator<Entry<String, String>> iterator = set.iterator();iterator.hasNext();) {
			Entry<String, String> entry = iterator.next();
			buider.add(entry.getKey(), entry.getValue());
		}
		RequestBody body = buider.build();
		request = new Request.Builder().url(baseUrl).post(body).build();
		Response response = client.newCall(request).execute();
		if (response.isSuccessful()) {
			return response.body().toString();
		} else {
			throw new IOException("Unexpected code " + response);
		}
	}
	/**
	 * 使用OkHttp通过post方式向服务器传递数据
	 * @param url 包含参数对的服务器地址
	 * @return json格式服务器响应数据
	 */
	public static JSONObject jsonGetWithOkHttp(String url) {
		try {
			result = new JSONObject(getWithOkHttp(url));
			return result;
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 使用OkHttp通过post方式向服务器传递数据
	 * @param url 服务器地址
	 * @param key 键
	 * @param value 值
	 * @return json格式服务器响应数据
	 */
	public static JSONObject jsonGetWithOkHttp(String url, String key, String value) {
		try {
			result = new JSONObject(getWithOkHttp(url, key, value));
			return result;
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 使用OkHttp通过post方式向服务器传递数据
	 * @param url 服务器地址
	 * @param params List<BasicNameValuePair>形式参数对
	 * @return json格式服务器响应数据
	 */
	public static JSONObject jsonGetWithOkHttp(String url, List<BasicNameValuePair> params) {
		try {
			result = new JSONObject(getWithOkHttp(url, params));
			return result;
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 使用OkHttp通过post方式向服务器传递数据
	 * @param url 服务器地址
	 * @param params Map<String, String> 形式参数对
	 * @return
	 */
	public static JSONObject jsonGetWithOkHttp(String url, Map<String, String> params) {
		try {
			result = new JSONObject(getWithOkHttp(url, params));
			return result;
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 转换List<BasicNameValuePair>参数为Map<String, String>参数
	 * @param params List<BasicNameValuePair>形式参数
	 * @return Map<String, String>参数
	 */
	public static Map<String, String> convertListUrlToMap(List<BasicNameValuePair> params) {
		String param = URLEncodedUtils.format(params, "utf-8");
		Map<String, String> map = new LinkedHashMap<String, String>();
		String[] pairs = param.split("&");
		for (int i = 0 ; i < pairs.length; i++) {
			String[] keyAndValue = pairs[i].split("=");
			map.put(keyAndValue[0], keyAndValue[1]);
		}
		return map;
	}
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	public static String postJsonWithHttp(String url, String json) throws IOException {
	 	RequestBody body = RequestBody.create(JSON, json);
	  	Request request = new Request.Builder()
	      .url(url)
	      .post(body)
	      .build();
	  	Response response = client.newCall(request).execute();
		if (response.isSuccessful()) {
		    return response.body().string();
		} else {
		    throw new IOException("Unexpected code " + response);
		}
	}
	/**
	 * 转换Map<String, String>为string
	 * @param Map<String, String> 参数
	 * @return 转换后的String数据
	 */
	public static String transMapToString(Map<String, String> map) {
		Entry<String, String> entry;
		StringBuilder sb = new StringBuilder();
		for (Iterator<Entry<String, String>> iterator = map.entrySet().iterator(); iterator.hasNext();) {
			entry = iterator.next();
			 sb.append(entry.getKey().toString()).append( "=" ).append(null==entry.getValue()?"":  
			      entry.getValue().toString()).append (iterator.hasNext() ? "&" : ""); 
		}
		return sb.toString();
	}
	
	private static final String CHARSET_NAME = "UTF-8";

	public static String formatParams(List<BasicNameValuePair> params) {
		return URLEncodedUtils.format(params, CHARSET_NAME);
	}
	public static String attacHttpGetParams(String url, Map<String, String> params) {
		return attachHttpGetParam(url, transMapToString(params));
	}
	public static String attachHttpGetParams(String url,
			List<BasicNameValuePair> params) {
		return url + "?" + formatParams(params);
	}
	public static String attachHttpGetParam(String url, String params) {
		return url + "?" + params;
	}
	public static String attachHttpGetParam(String url, String name,
			String value) {
		return url + "?" + name + "=" + value;
	}
	public static String attachKeyAndVlaue(String key, String value) {
		return key + "=" + value;
	}
	
}
