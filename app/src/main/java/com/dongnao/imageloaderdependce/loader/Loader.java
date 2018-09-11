package com.dongnao.imageloaderdependce.loader;


import com.dongnao.imageloaderdependce.request.BitmapRequest;

public interface Loader {

	/**
	 * 加载图片
	 * @param request
	 */
	void loadImage(BitmapRequest request);
	
}
