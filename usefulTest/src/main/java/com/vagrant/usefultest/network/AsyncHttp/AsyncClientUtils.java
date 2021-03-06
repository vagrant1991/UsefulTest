package com.vagrant.usefultest.network.asynchttp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * android-async-http工具类
 * @author vagrant QQ:513302092
 * @date 创建时间: 2015年4月25日
 */
public class AsyncClientUtils {
	//可修改此变量默认值
	public static final String BASE_URL = "http://www.birdboy.cn/";
	private static RequestParams requestParams;
	private static AsyncHttpClient client = new AsyncHttpClient();
	
	/**
	 * 使用android-async-http通过get方式向服务器发起请求，异步返回结果
	 * @param url 完整的URL路径
	 * @param params RequestParams封装的参数对
	 * @param responseHandler 回调接口
	 */
	public static void getWithFullUrl(Context context, String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
		client.get(context, url, params, responseHandler);
	}
	/**
	 * 使用android-async-http通过get方式向服务器发起请求，异步返回结果
	 * @param url 完整的URL路径
	 * @param name 参数名
	 * @param value 参数值
	 * @param responseHandler 回调接口
	 */
	public static void getWithFullUrl(Context context, String url, String name, String value, AsyncHttpResponseHandler responseHandler) {
		requestParams = new RequestParams(name, value);
		getWithFullUrl(context, url, requestParams, responseHandler);
	}
	/**
	 * 使用android-async-http通过get方式向服务器发起请求，异步返回结果
	 * @param url 完整的URL路径
	 * @param listParams List<BasicNameValuePair>格式参数
	 * @param responseHandler 回调接口
	 */
	public static void getWithFullUrl(Context context, String url, List<BasicNameValuePair> listParams, AsyncHttpResponseHandler responseHandler) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map = convertListUrlToMap(listParams);
		getWithFullUrl(context, url, map, responseHandler);
	}
	/**
	 * 使用android-async-http通过get方式向服务器发起请求，异步返回结果
	 * @param url 完整的URL路径
	 * @param params Map<String, String>格式参数
	 * @param responseHandler 回调接口
	 */
	public static void getWithFullUrl(Context context, String url, Map<String, String> params, AsyncHttpResponseHandler responseHandler) {
		requestParams = new RequestParams(params);
		getWithFullUrl(context, url, requestParams, responseHandler);
	}
	/**
	 * 使用android-async-http通过get方式向服务器发起请求，异步返回结果
	 * @param api api名称
	 * @param params RequestParams封装的参数对
	 * @param responseHandler 回调接口
	 */
	public static void getWithApi(Context context, String api, RequestParams params, AsyncHttpResponseHandler responseHandler) {
		client.get(context, getAbsoluteUrl(api), params, responseHandler);
	}
	/**
	 * 使用android-async-http通过get方式向服务器发起请求，异步返回结果
	 * @param api api名称
	 * @param name 参数名
	 * @param value 参数值
	 * @param responseHandler 回调接口
	 */
	public static void getWithApi(Context context, String api, String name, String value, AsyncHttpResponseHandler responseHandler) {
		requestParams = new RequestParams(name, value);
		getWithApi(context, api, requestParams, responseHandler);
	}
	/**
	 * 使用android-async-http通过get方式向服务器发起请求，异步返回结果
	 * @param api api名称
	 * @param listParams List<BasicNameValuePair>格式参数
	 * @param responseHandler 回调接口
	 */ 
	public static void getWithApi(Context context, String api, List<BasicNameValuePair> listParams, AsyncHttpResponseHandler responseHandler) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map = convertListUrlToMap(listParams);
		getWithApi(context, api, map, responseHandler);
	}
	/**
	 * 使用android-async-http通过get方式向服务器发起请求，异步返回结果
	 * @param api api名称
	 * @param params Map<String, String>格式参数
	 * @param responseHandler 回调接口
	 */
	public static void getWithApi(Context context, String api, Map<String, String> params, AsyncHttpResponseHandler responseHandler) {
		requestParams = new RequestParams(params);
		getWithApi(context, api, requestParams, responseHandler);
	}
	/**
	 * 使用android-async-http通过post方式向服务器发起请求，异步返回结果
	 * @param api 完整URL路径
	 * @param params RequestParams封装的参数
	 * @param responseHandler 回调接口
	 */
	public static void postWithFullUrl(Context context, String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
		client.post(context, url, params, responseHandler);
	}
	/**
	 * 使用android-async-http通过post方式向服务器发起请求，异步返回结果
	 * @param url 完整URL路径
	 * @param name name值
	 * @param value value值
	 * @param responseHandler 回调接口
	 */
	public static void postWithFullUrl(Context context, String url, String name, String value, AsyncHttpResponseHandler responseHandler) {
		requestParams = new RequestParams(name, value);
		postWithFullUrl(context, url, requestParams, responseHandler);
	}
	/**
	 * 使用android-async-http通过post方式向服务器发起请求，异步返回结果
	 * @param url 完整URL路径
	 * @param listParams List<BasicNameValuePair>表示参数值
	 * @param responseHandler 回调接口
	 */
	public static void postWithFullUrl(Context context, String url, List<BasicNameValuePair> listParams, AsyncHttpResponseHandler responseHandler) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map = convertListUrlToMap(listParams);
		postWithFullUrl(context, url, map, responseHandler);
	}
	/**
	 * 使用android-async-http通过post方式向服务器发起请求，异步返回结果
	 * @param url 完整URL路径
	 * @param params Map<String, String>表示参数值
	 * @param responseHandler 回调接口
	 */
	public static void postWithFullUrl(Context context, String url, Map<String, String> params, AsyncHttpResponseHandler responseHandler) {
		requestParams = new RequestParams(params);
		postWithFullUrl(context, url, requestParams, responseHandler);
	}
	/**
	 * 使用android-async-http通过post方式向服务器发起请求，异步返回结果 
	 * @param api api名称
	 * @param params RequestParams封装的参数
	 * @param responseHandler 回调接口
	 */
	public static void postWithApi(Context context, String api, RequestParams params, AsyncHttpResponseHandler responseHandler) {
		client.post(context, getAbsoluteUrl(api), params, responseHandler);
	}
	/**
	 * 使用android-async-http通过post方式向服务器发起请求，异步返回结果 
	 * @param api api名称
	 * @param name name值
	 * @param value value值
	 * @param responseHandler 回调接口
	 */
	public static void postWithApi(Context context, String api, String name, String value, AsyncHttpResponseHandler responseHandler) {
		requestParams = new RequestParams(name, value);
		postWithApi(context, api, requestParams, responseHandler);
	}
	/**
	 * 使用android-async-http通过post方式向服务器发起请求，异步返回结果 
	 * @param api api名称
	 * @param listParams List<BasicNameValuePair>形式表示参数
	 * @param responseHandler 回调接口
	 */
	public static void postWithApi(Context context, String api, List<BasicNameValuePair> listParams, AsyncHttpResponseHandler responseHandler) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map = convertListUrlToMap(listParams);
		postWithApi(context, api, map, responseHandler);
	}
	/**
	 * 使用android-async-http通过post方式向服务器发起请求，异步返回结果 
	 * @param api api名称 
	 * @param params Map<String,String> 形式表示参数
	 * @param responseHandler 回调接口
	 */
	public static void postWithApi(Context context, String api, Map<String,String> params, AsyncHttpResponseHandler responseHandler) {
		requestParams = new RequestParams(params);
		postWithApi(context, api, requestParams, responseHandler);
	}
	/**
	 * 使用android-async-http通过post方式向服务器写入InputStream，异步返回结果 
	 * @param url url地址
	 * @param key 标识参数的key值  see: RequestParams.put(String key, InputStream stream)
	 * @param inputstream
	 * @param responseHandler  回调接口
	 */
	public static void postInputStream(Context context, String url, String key, InputStream inputstream, AsyncHttpResponseHandler responseHandler) {
		requestParams = new RequestParams();
		requestParams.put(key, inputstream);
		postWithFullUrl(context, url, requestParams, responseHandler);
	}
	/**
	 * 使用android-async-http通过post方式向服务器写入InputStream，异步返回结果 
	 * @param url url地址
	 * @param key 标识参数的key值  see: RequestParams.put(String key, InputStream stream, String name)
	 * @param inputStream
	 * @param name inputStream的名称
	 * @param responseHandler 回调接口
	 */
	public static void postInputStream(Context context, String url, String key, InputStream inputStream, String name, AsyncHttpResponseHandler responseHandler) {
		requestParams = new RequestParams();
		requestParams.put(key, inputStream, name);
		postWithFullUrl(context, url, requestParams, responseHandler);
	}
	/**
	 * 使用android-async-http通过post方式向服务器写入InputStream，异步返回结果 
	 * @param url url地址
	 * @param key 标识参数的key值  see: RequestParams.put(String key, InputStream stream, String name, String contentType)
	 * @param inputStream
	 * @param name inputStream的名称
	 * @param contentType inputStream的类型 如：application/json
	 * @param responseHandler 回调接口
	 */
	public static void postInputStream(Context context, String url, String key, InputStream inputStream, String name, String contentType, AsyncHttpResponseHandler responseHandler) {
		requestParams = new RequestParams();
		requestParams.put(key, inputStream, name, contentType);
		postWithFullUrl(context, url, requestParams, responseHandler);
	}
	/**
	 * 使用android-async-http通过post方式向服务器上传文件，异步返回结果 
	 * @param url url地址
	 * @param key 标识参数的key值 see:RequestParams.put(String key, File file)
	 * @param file 文件
	 * @param responseHandler 回调接口
	 */
	public static void postFile(Context context, String url, String key, File file, AsyncHttpResponseHandler responseHandler) {
		requestParams = new RequestParams();
		try {
			requestParams.put(key, file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		postWithFullUrl(context, url, requestParams, responseHandler);
	}
	/**
	 * 使用android-async-http通过post方式向服务器上传文件，异步返回结果 
	 * @param url url地址 
	 * @param key 标识参数的key值 see:RequestParams.put(String key, File file, String contentType, String customFileName)
	 * @param file 文件
	 * @param contentType 文件类型 如：application/json
	 * @param responseHandler 回调接口
	 */
	public static void postFile(Context context, String url, String key, File file, String contentType, AsyncHttpResponseHandler responseHandler) {
		requestParams = new RequestParams();
		try {
			requestParams.put(key, file, contentType);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		postWithFullUrl(context, url, requestParams, responseHandler);
	}
	/**
	 * 使用android-async-http通过post方式向服务器上传文件，异步返回结果 
	 * @param url url地址 
	 * @param key 标识参数的key值  see RequestParams.put(String key, File file, String contentType, String customFileName)
	 * @param file 文件
	 * @param contentType 文件类型 如：application/json
	 * @param customName 文件名
	 * @param responseHandler 回调接口
	 */
	public static void postFile(Context context, String url, String key, File file, String contentType, String customName, AsyncHttpResponseHandler responseHandler) {
		requestParams = new RequestParams();
		try {
			requestParams.put(key, file, contentType, customName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		postWithFullUrl(context, url, requestParams, responseHandler);
	}
	public static void downloadFile(Context context, String url, FileAsyncHttpResponseHandler fileAsyncHttpResponseHandler) {
		client.get(context, url, fileAsyncHttpResponseHandler);
	}
	public static String getAbsoluteUrl(String relativeUrl) {
		return BASE_URL + relativeUrl;
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
}
