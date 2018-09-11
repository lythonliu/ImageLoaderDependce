package com.dongnao.imageloaderdependce.test;


import android.os.SystemClock;
import android.test.AndroidTestCase;

import com.dongnao.imageloaderdependce.config.ImageLoaderConfig;
import com.dongnao.imageloaderdependce.core.MyImageLoader;
import com.dongnao.imageloaderdependce.policy.SerialPolicy;

public class Test extends AndroidTestCase{

	
	public void testDispatch(){
		//配置
		ImageLoaderConfig.Builder build = new ImageLoaderConfig.Builder();
		build.setThreadCount(3) //线程数量
		.setLoadPolicy(new SerialPolicy()); //加载策略
		
		ImageLoaderConfig config = build.build();
		//初始化
		final MyImageLoader myImageLoader = MyImageLoader.getInstance(config);
		
		//请求
		new Thread(){
			public void run() {
				for (int i = 0; i < 30; i++) {
					String uri = "http://img.my.csdn.net/uploads/201407/26/1406383299_1976.jpg";
					myImageLoader.displayImage(null, uri, null, null);
					myImageLoader.displayImage(null, uri, null, null);
					SystemClock.sleep(1000);
				}
			}
		}.start();
		
		
		new Thread(){
			public void run() {
				for (int i = 0; i < 30; i++) {
					
					//loader.displayImage(null, null, null, null);
					//loader.displayImage(null, null, null, null);
					//SystemClock.sleep(1500);
				}
			}
		}.start();
		
		SystemClock.sleep(1000 * 60);
	}
	
}
