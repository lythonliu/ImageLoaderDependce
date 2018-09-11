package com.dongnao.imageloaderdependce.request;

import android.widget.ImageView;

import com.dongnao.imageloaderdependce.config.DisplayConfig;
import com.dongnao.imageloaderdependce.core.MyImageLoader;
import com.dongnao.imageloaderdependce.policy.LoadPolicy;
import com.dongnao.imageloaderdependce.utils.MD5Utils;

import java.lang.ref.SoftReference;

public class BitmapRequest implements Comparable<BitmapRequest>{
	//加载策略
	private LoadPolicy loadPolicy = MyImageLoader.getObject().getConfig().getLoadPolicy();
	//序列号
	private int serialNO;
	
	//图片控件
	//当系统内存不足时，把引用的对象进行回收
	private SoftReference<ImageView> imageViewSoftReference;
	//图片路径
	private String imageUri;
	//MD5的图片路径
	private String imageUriMD5;
	
	private DisplayConfig displayConfig = MyImageLoader.getObject().getConfig().getDisplayConfig();
	public MyImageLoader.ImageListener imageListener;

	public BitmapRequest(ImageView imageView, String uri, DisplayConfig config, MyImageLoader.ImageListener imageListener) {
		this.imageViewSoftReference = new SoftReference<ImageView>(imageView);
		//设置可见的ImageView的tag为，要下载的图片路径
		imageView.setTag(uri);
		this.imageUri = uri;
		this.imageUriMD5 = MD5Utils.toMD5(imageUri);
		if(config != null){
			this.displayConfig = config;
		}
		this.imageListener = imageListener;
	}
	

	@Override
	public int compareTo(BitmapRequest another) {
		return loadPolicy.compareTo(this, another);
	}

	

	/**
	 * 设置序列号
	 * @param serialNO
	 */
	public void setSerialNO(int serialNO) {
		this.serialNO = serialNO;
	}
	
	public int getSerialNO() {
		return serialNO;
	}

	public ImageView getImageView() {
		return imageViewSoftReference.get();
	}
	
	public String getImageUri() {
		return imageUri;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((loadPolicy == null) ? 0 : loadPolicy.hashCode());
		result = prime * result + serialNO;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BitmapRequest other = (BitmapRequest) obj;
		if (loadPolicy == null) {
			if (other.loadPolicy != null)
				return false;
		} else if (!loadPolicy.equals(other.loadPolicy))
			return false;
		if (serialNO != other.serialNO)
			return false;
		return true;
	}
	
	public String getImageUriMD5() {
		return imageUriMD5;
	}

}
