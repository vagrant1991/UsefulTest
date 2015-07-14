package com.vagrant.usefultest.image.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.DisplayMetrics;

public class CommonImageCache {
	
	private LruCache<String, Bitmap> mCache;

	public CommonImageCache() {
		// 缓存大小
		int maxMemorySize = 4 * 1024 * 1024;
		mCache = new LruCache<String, Bitmap>(maxMemorySize) {
			@Override
			protected int sizeOf(String key, Bitmap value) {
				// return bitmap.getByteCount() / 1024;
				return value.getRowBytes() * value.getHeight();
			}
		};
	}

	public CommonImageCache(int maxSize) {
		mCache = new LruCache<String, Bitmap>(maxSize) {
			@Override
			protected int sizeOf(String key, Bitmap bitmap) {
				return bitmap.getRowBytes() * bitmap.getHeight();
			}
		};
	}

	public CommonImageCache(Context context) {
		this(getCacheSize(context));
	}

	public Bitmap getBitmap(String url) {
		return mCache.get(url);
	}

	public void putBitmap(String url, Bitmap bitmap) {
		if (mCache.get(url) == null)
			mCache.put(url, bitmap);
	}
	// Returns a cache size equal to approximately three screens worth of
	// images.
	public static int getCacheSize(Context ctx) {
		final DisplayMetrics displayMetrics = ctx.getResources()
				.getDisplayMetrics();
		final int screenWidth = displayMetrics.widthPixels;
		final int screenHeight = displayMetrics.heightPixels;
		// 4 bytes per pixel
		final int screenBytes = screenWidth * screenHeight * 4;

		return screenBytes * 3;
	}
}
