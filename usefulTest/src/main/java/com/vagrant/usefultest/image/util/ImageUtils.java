package com.vagrant.usefultest.image.util;

import java.io.InputStream;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 *  加载不同来源图片为指定大小图片
 * @author vagrant QQ:513302092
 * @date 创建时间: 2015年4月27日
 */
public class ImageUtils {
	//计算 BitmapFactory.Options的inSampleSize值
	public static int calculateInSampleSize(BitmapFactory.Options options,  
	        int reqWidth, int reqHeight) {  
		// Raw height and width of image
	    final int height = options.outHeight;
	    final int width = options.outWidth;
	    int inSampleSize = 1;

	    if (height > reqHeight || width > reqWidth) {

	        final int halfHeight = height / 2;
	        final int halfWidth = width / 2;

	        // Calculate the largest inSampleSize value that is a power of 2 and keeps both
	        // height and width larger than the requested height and width.
	        while ((halfHeight / inSampleSize) > reqHeight
	                && (halfWidth / inSampleSize) > reqWidth) {
	            inSampleSize *= 2;
	        }
	    }
	    return inSampleSize;
	}  
	/**
	 * 从本地加载图片，指定大小
	 * @param res getResources()获得
	 * @param resId 图片id,如: R.drawable.icon
	 * @param reqWidth 指定的宽度
	 * @param reqHeight 指定的高度
	 * @return Bitmap对象
	 */
	public static Bitmap decodeBitmapFromResource(Resources res, int resId,  
	        int reqWidth, int reqHeight) {  
	    // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小  
	    final BitmapFactory.Options options = new BitmapFactory.Options();  
	    //设置inJustDecodeBounds为true,禁止为bitmap分配内存
	    options.inJustDecodeBounds = true;  
	    BitmapFactory.decodeResource(res, resId, options);  
	    // 计算inSampleSize值  
	    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);  
	    // 使用获取到的inSampleSize值再次解析图片  
	    options.inJustDecodeBounds = false;  
	    return BitmapFactory.decodeResource(res, resId, options);  
	}  
	/**
	 * 从输入流加载图片，指定大小
	 * @param inputstream 输入流
	 * @param reqWidth 指定的宽度
	 * @param reqHeight 指定的高度
	 * @return Bitmap对象
	 */
	public static Bitmap decodeBitmapFromStream(InputStream inputstream, int reqWidth, int reqHeight) {
		// 第一次解析将inJustDecodeBounds设置为true，来获取图片大小  
	    final BitmapFactory.Options options = new BitmapFactory.Options();  
	    //设置inJustDecodeBounds为true,禁止为bitmap分配内存
	    options.inJustDecodeBounds = true;  
		BitmapFactory.decodeStream(inputstream, null, options);
		// 计算inSampleSize值  
	    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);  
	    // 使用获取到的inSampleSize值再次解析图片  
	    options.inJustDecodeBounds = false;  
	    return BitmapFactory.decodeStream(inputstream, null, options);  
	}
	/**
	 * 从字节中加载图片  
	 * @param data 字节数组
	 * @param reqWidth  指定的宽度
	 * @param reqHeight 指定的高度
	 * @return
	 */
	public static Bitmap decodeBitmapFromByte(byte[] data, int reqWidth, int reqHeight) {
		// 第一次解析将inJustDecodeBounds设置为true，来获取图片大小  
	    final BitmapFactory.Options options = new BitmapFactory.Options();  
	    //设置inJustDecodeBounds为true,禁止为bitmap分配内存
	    options.inJustDecodeBounds = true;  
		BitmapFactory.decodeByteArray(data, 0, data.length, options);
		// 计算inSampleSize值  
	    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);  
	    // 使用获取到的inSampleSize值再次解析图片  
	    options.inJustDecodeBounds = false;  
	    return BitmapFactory.decodeByteArray(data, 0, data.length, options);
	}
	/**
	 * 从文件中加载图片
	 * @param pathName 文件路径
	 * @param reqWidth 指定的宽度
	 * @param reqHeiht 指定的高度
	 * @return
	 */
	public static Bitmap decodeBitmapFromFile(String pathName, int reqWidth, int reqHeight) {
		// 第一次解析将inJustDecodeBounds设置为true，来获取图片大小  
	    final BitmapFactory.Options options = new BitmapFactory.Options();  
	    //设置inJustDecodeBounds为true,禁止为bitmap分配内存
	    options.inJustDecodeBounds = true;  
	    
	    BitmapFactory.decodeFile(pathName);
		// 计算inSampleSize值  
	    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);  
	    // 使用获取到的inSampleSize值再次解析图片  
	    options.inJustDecodeBounds = false;  
	    return BitmapFactory.decodeFile(pathName, options);
	}
}
