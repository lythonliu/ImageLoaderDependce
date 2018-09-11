package com.dongnao.imageloaderdependce.loader;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.dongnao.imageloaderdependce.cache.BitmapCache;
import com.dongnao.imageloaderdependce.config.DisplayConfig;
import com.dongnao.imageloaderdependce.core.MyImageLoader;
import com.dongnao.imageloaderdependce.request.BitmapRequest;

/**
 * 抽象加载器
 * @author Jason
 * QQ: 1476949583
 * @date 2016年3月11日
 * @version 1.0
 */
public abstract class AbstractLoader implements Loader{
	
	//缓存策略
	private BitmapCache bitmapCache = MyImageLoader.getObject().getConfig().getBitmapCache();
	//显示配置
	private DisplayConfig displayConfig = MyImageLoader.getObject().getConfig().getDisplayConfig();
	
	/**
	 * 模板方法
	 */
	@Override
	public void loadImage(BitmapRequest request) {
		//从缓存获取，缓存中没有再加载
		Bitmap bitmap = bitmapCache.get(request);
		if(bitmap == null){
			//加载前显示的图片
			showLoadingImage(request);
			
			//加载完成，再缓存
			//具体的加载方式，由子类决定
			bitmap = onLoad(request);
			cacheBitmap(request,bitmap);
		}
		
		//显示
		deliveryToUIThread(request, bitmap);
	}

	/**
	 * 加载前显示的图片
	 * @param request
	 */
	protected void showLoadingImage(BitmapRequest request) {
		//指定了，显示配置
		if(hasLoadingPlaceHolder()){
			final ImageView imageView = request.getImageView();
			imageView.post(new Runnable() {
				@Override
				public void run() {
					imageView.setImageResource(displayConfig.loadingImgResId);
				}
			});
		}
	}
	
	protected boolean hasLoadingPlaceHolder(){
		return (displayConfig != null && displayConfig.loadingImgResId > 0);
	}
	
	protected boolean hasFailedPlaceHolder(){
		return (displayConfig != null && displayConfig.failedImg > 0);
	}

	/**
	 * 交给主线程显示
	 * @param request
	 * @param bitmap
	 */
	protected void deliveryToUIThread(final BitmapRequest request, final Bitmap bitmap) {
		ImageView imageView = request.getImageView();
		imageView.post(new Runnable() {
			@Override
			public void run() {
				updateImageView(request, bitmap);
			}
			
		});
	}
	
	
	private void updateImageView(final BitmapRequest request, final Bitmap bitmap) {
		ImageView imageView = request.getImageView();
		//加载正常
		if(bitmap != null && imageView.getTag().equals(request.getImageUri())){
			imageView.setImageBitmap(bitmap);
		}
		//有可能加载失败
		if(bitmap == null && hasFailedPlaceHolder()){
			imageView.setImageResource(displayConfig.failedImg);
		}
		//监听
		//回调
		if(request.imageListener != null){
			request.imageListener.onComplete(imageView, bitmap, request.getImageUri());
		}
	}

	/**
	 * 缓存
	 * @param request
	 * @param bitmap
	 */
	private void cacheBitmap(BitmapRequest request, Bitmap bitmap) {
		if(request != null && bitmap != null){
			synchronized (bitmapCache) {
				bitmapCache.put(request,bitmap);
			}
		}
	}

	/**
	 * 具体的加载实现
	 * @param request
	 * @return
	 */
	protected abstract Bitmap onLoad(BitmapRequest request);
	
	
	
}
