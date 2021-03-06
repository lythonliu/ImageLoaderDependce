package com.dongnao.imageloaderdependce.cache;


import android.graphics.Bitmap;

import com.dongnao.imageloaderdependce.request.BitmapRequest;

public interface BitmapCache {

	/**
	 * 缓存
	 * @param request
	 * @param bitmap
	 */
	void put(BitmapRequest request, Bitmap bitmap);
	
	/**
	 * 获取缓存
	 * @param request
	 * @return
	 */
	Bitmap get(BitmapRequest request);
	
	/**
	 * 移除缓存
	 * @param request
	 */
	void remove(BitmapRequest request);
	
}
