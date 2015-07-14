package com.vagrant.usefultest.network.volley;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 自定义用于上传文件的volley请求,需要引入Httpmime包， Multiparty请参照：http://www.baeldung.com/httpclient-multipart
 * -upload
 * 
 * @author vagrant QQ:513302092
 * @date 创建时间: 2015年4月29日
 */
public class UploadRequest extends Request<String> {
	private final Listener<String> mListener;
	private File file;
	private Bitmap bitmap;
	private Map<String, String> params;
	private MultipartEntityBuilder builder;
	private HttpEntity entity;

	public UploadRequest(String url, File file, Map<String, String> params,
						 Listener<String> listener, ErrorListener errorListener) {
		super(Method.POST, url, errorListener);
		mListener = listener;
		this.file = file;
		this.params = params;
		buildMultiPartyEntity();
	}

	public UploadRequest(String url, Bitmap bitmap, Map<String, String> params,
						 Listener<String> listener, ErrorListener errorListener) {
		super(Method.POST, url, errorListener);
		mListener = listener;
		this.bitmap = bitmap;
		this.params = params;
		buildMultiPartyEntity();
	}

	public void buildMultiPartyEntity() {
		builder = MultipartEntityBuilder.create();
		if (file != null) {
			FileBody fileBody = new FileBody(file);
		}
		if (params != null) {
			Iterator<Entry<String, String>> iterator = params.entrySet()
					.iterator();
			while (iterator.hasNext()) {
				Entry<String, String> entry = iterator.next();
				String key = entry.getKey();
				String value = entry.getValue();
				builder.addTextBody(key, value);
			}
		}
		if (bitmap != null) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			bitmap.compress(CompressFormat.JPEG, 100, baos);
			byte[] b = baos.toByteArray();
			builder.addBinaryBody("bitmap", b);
		}
		entity = builder.build();
	}

	@Override
	public byte[] getPostBody() throws AuthFailureError {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			entity.writeTo(baos);
			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getPostBodyContentType() {
		return entity.getContentType().getValue();
	}

	@Override
	protected void deliverResponse(String response) {
		mListener.onResponse(response);
	}

	@Override
	protected Response<String> parseNetworkResponse(NetworkResponse response) {
		String parsed;
		try {
			parsed = new String(response.data,
					HttpHeaderParser.parseCharset(response.headers));
		} catch (UnsupportedEncodingException e) {
			parsed = new String(response.data);
		}
		return Response.success(parsed,
				HttpHeaderParser.parseCacheHeaders(response));
	}
}
