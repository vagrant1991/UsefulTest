package com.vagrant.usefultest.network.asynchttp;

import org.apache.http.Header;


import com.google.gson.Gson;
import com.loopj.android.http.BaseJsonHttpResponseHandler;

/**
 * 自定义的一个用于android-async-http的AsyncHttpResponseHandler子类,通过构造函数传入class对象，异步获得class对应的响应对象
 * 使用Gson解析 
 * @author vagrant QQ:513302092
 * @date 创建时间: 2015年5月4日
 */
public class GsonHttpResponseHandler<T> extends BaseJsonHttpResponseHandler<T> {
	private static Gson gson;
	private Class<T> clazz;
	static {
		gson = new Gson();
	}

	public GsonHttpResponseHandler(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public void onFailure(int arg0, Header[] arg1, Throwable arg2, String arg3,
			T arg4) {
		
	}

	@Override
	public void onSuccess(int arg0, Header[] arg1, String arg2, T arg3) {

	}

	@Override
	protected T parseResponse(String arg0, boolean arg1) throws Throwable {
		if (!arg1)
			return gson.fromJson(arg0, clazz);
		return null;
	}
}
