package com.dongnao.imageloaderdependce.request;

import android.util.Log;

import com.dongnao.imageloaderdependce.loader.Loader;
import com.dongnao.imageloaderdependce.loader.LoaderManager;

import java.util.concurrent.BlockingQueue;

/**
 * 请求转发线程，不断从请求队列中获取请求处理
 * @author Jason
 * QQ: 1476949583
 * @date 2016年3月9日
 * @version 1.0
 */
public class RequestDispatcherThread extends Thread{
	
	
	//请求队列
	private BlockingQueue<BitmapRequest> bitmapRequestBlockingQueue;

	public RequestDispatcherThread(BlockingQueue<BitmapRequest> bitmapRequestBlockingQueue) {
		this.bitmapRequestBlockingQueue = bitmapRequestBlockingQueue;
	}

	@Override
	public void run() {
		//非阻塞状态，获取请求处理
		while(!isInterrupted()){
			//从队列中获取优先级最高的请求进行处理
			try {
				BitmapRequest request = bitmapRequestBlockingQueue.take();
				Log.d("jason", "---处理请求"+request.getSerialNO());
				//解析图片地址，获取对象的加载器
				String schema = parseSchema(request.getImageUri());
				Loader loader = LoaderManager.getInstance().getLoader(schema);
				loader.loadImage(request);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}

	/**
	 * 解析图片地址，获取schema
	 * @param imageUri
	 * @return
	 */
	private String parseSchema(String imageUri) {
		if(imageUri.contains("://")){
			return imageUri.split("://")[0];
		}else{
			Log.e("jason", "图片地址schema异常！");
		}
		return null;
	}
	
}
