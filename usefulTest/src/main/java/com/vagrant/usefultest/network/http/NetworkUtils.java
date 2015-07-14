package com.vagrant.usefultest.network.http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * 检查网络连接状态工具类
 * @author vagrant QQ:513302092
 * @date 创建时间: 2015年4月27日
 */
public class NetworkUtils {

	public static boolean isNetworkAvalible(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		// NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		// if (networkInfo != null && networkInfo.isConnected()) {
		// // checkNetwork(context);
		// // CheckNetworkTast task = new CheckNetworkTast();
		// // task.execute(context);
		// return true;
		// }
		NetworkInfo[] net_info = connectivityManager.getAllNetworkInfo();

		if (net_info != null) {
			for (int i = 0; i < net_info.length; i++) {
				if (net_info[i].getState() == NetworkInfo.State.CONNECTED) {
					return true;
				}
			}
		}
		Toast.makeText(context, "网络不可用，请检查网络连接!", Toast.LENGTH_SHORT).show();
		return false;
	}

	public static void checkNetwork(Context context) {
		URL url;
		try {
			url = new URL("www.baidu.com");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(10000);
			conn.connect();
			if (!(conn.getResponseCode() == 200))
				Toast.makeText(context, "网络不可用",
						Toast.LENGTH_SHORT).show();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static class CheckNetworkTast extends
			AsyncTask<Context, Void, Boolean> {

		@Override
		protected Boolean doInBackground(Context... params) {
			URL url;
			try {
				url = new URL("http://www.baidu.com");
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.setConnectTimeout(5000);
				conn.connect();
				if (!(conn.getResponseCode() == 200)) {
					Toast.makeText(params[0], "网络不可用",
							Toast.LENGTH_SHORT).show();
					return false;
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}

	}
}
