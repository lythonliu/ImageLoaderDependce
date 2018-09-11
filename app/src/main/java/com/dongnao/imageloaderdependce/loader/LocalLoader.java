package com.dongnao.imageloaderdependce.loader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;

import com.dongnao.imageloaderdependce.request.BitmapRequest;
import com.dongnao.imageloaderdependce.utils.BitmapDecoder;
import com.dongnao.imageloaderdependce.utils.ImageViewHelper;

import java.io.File;

/**
 * 本地图片加载器
 * @author Jason
 * QQ: 1476949583
 * @date 2016年3月11日
 * @version 1.0
 */
public class LocalLoader extends AbstractLoader {

	@Override
	protected Bitmap onLoad(BitmapRequest request) {
		final String path = Uri.parse(request.getImageUri()).getPath();
		File file = new File(path);
		if(!file.exists()){
			return null;
		}
		BitmapDecoder decoder = new BitmapDecoder() {
			@Override
			public Bitmap decodeBitmapWithOption(Options options) {
				return BitmapFactory.decodeFile(path, options);
			}
		};
		
		return decoder.decodeBitmap(ImageViewHelper.getImageViewWidth(request.getImageView()), ImageViewHelper.getImageViewHeight(request.getImageView()));
	}

}
