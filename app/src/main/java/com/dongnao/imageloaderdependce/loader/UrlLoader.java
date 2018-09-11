package com.dongnao.imageloaderdependce.loader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

import com.dongnao.imageloaderdependce.request.BitmapRequest;
import com.dongnao.imageloaderdependce.utils.BitmapDecoder;
import com.dongnao.imageloaderdependce.utils.ImageViewHelper;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 网络图片加载器
 * @author Jason
 * QQ: 1476949583
 * @date 2016年3月11日
 * @version 1.0
 */
public class UrlLoader extends AbstractLoader {

	@Override
	protected Bitmap onLoad(BitmapRequest request) {
		HttpURLConnection httpURLConnection = null;
		InputStream inputStream1 = null;
		try {
			httpURLConnection = (HttpURLConnection)new URL(request.getImageUri()).openConnection();
			//mark与reset支持重复使用流，但是InputStream不支持
			inputStream1 = new BufferedInputStream(httpURLConnection.getInputStream());
			//标记
			inputStream1.mark(inputStream1.available());
			final InputStream inputStream = inputStream1;
			//匿名内部类
			BitmapDecoder decoder = new BitmapDecoder(){
				@Override
				public Bitmap decodeBitmapWithOption(Options options) {
					Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, options);
					if(options.inJustDecodeBounds){
						try {
							//重置
							//则输入流总会在调用 mark 之后记住所有读取的字节，并且无论何时调用方法 reset ，都会准备再次提供那些相同的字节
							//但是，如果在调用 reset 之前可以从流中读取多于 readlimit 的字节，则根本不需要该流记住任何数据。
							inputStream.reset();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else{
						try {
							inputStream.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					return bitmap;
				}
			};
			//如何获取ImageView的宽高？
			return decoder.decodeBitmap(ImageViewHelper.getImageViewWidth(request.getImageView()), ImageViewHelper.getImageViewHeight(request.getImageView()));
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(httpURLConnection != null){
				httpURLConnection.disconnect();
			}
			try {
				if(inputStream1 != null){
					inputStream1.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

}
