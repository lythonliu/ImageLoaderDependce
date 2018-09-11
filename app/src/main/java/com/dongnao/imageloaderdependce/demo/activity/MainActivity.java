package com.dongnao.imageloaderdependce.demo.activity;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.dongnao.imageloaderdependce.BuildConfig;
import com.dongnao.imageloaderdependce.R;
import com.dongnao.imageloaderdependce.cache.DoubleCache;
import com.dongnao.imageloaderdependce.config.ImageLoaderConfig;
import com.dongnao.imageloaderdependce.core.MyImageLoader;
import com.dongnao.imageloaderdependce.policy.ReversePolicy;

public class MainActivity extends com.lythonliu.LinkActivity {
	@Override
	public String getAppName(){
		return BuildConfig.APP_NAME;
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
		ListView listview = (ListView) findViewById(R.id.listview);
		listview.setAdapter(new MyAdapter(this));
		
		//配置
		ImageLoaderConfig.Builder build = new ImageLoaderConfig.Builder();
		build.setThreadCount(3) //线程数量
		.setLoadPolicy(new ReversePolicy()) //加载策略
		.setCachePolicy(new DoubleCache(this)) //缓存策略
		.setLoadingPlaceHolder(R.drawable.loading)
		.setFailedPlaceHolder(R.drawable.not_found);
		
		ImageLoaderConfig config = build.build();
		//初始化
		myImageLoader = MyImageLoader.getInstance(config);
	
	}

	class MyAdapter extends BaseAdapter{
		
		private LayoutInflater inflater;

		public MyAdapter(Context context) {
			inflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return imageThumbUrls.length;
		}

		@Override
		public Object getItem(int position) {
			return imageThumbUrls[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View item = inflater.inflate(R.layout.item, null);
			ImageView imageView = (ImageView) item.findViewById(R.id.iv);
			//请求图片
			myImageLoader.displayImage(imageView, imageThumbUrls[position]);
			return item;
		}
		
	}
	
	
	public final static String[] imageThumbUrls = new String[] {
			"http://alipiba.com/image/1.jpg",
			"http://alipiba.com/image/2.jpg",
			"http://alipiba.com/image/3.jpg",
			"http://alipiba.com/image/4.jpg",
			"http://alipiba.com/image/5.jpg",
			"http://alipiba.com/image/6.jpg",
			"http://alipiba.com/image/7.jpg",
			"http://alipiba.com/image/8.jpg",
			"http://alipiba.com/image/9.jpg",
			"http://alipiba.com/image/10.jpg",
			"http://alipiba.com/image/11.jpg",
			"http://alipiba.com/image/12.jpg",
			"http://alipiba.com/image/13.jpg",
			"http://alipiba.com/image/14.jpg",
			"http://alipiba.com/image/15.jpg",
			"http://alipiba.com/image/16.jpg",
			"http://alipiba.com/image/17.jpg",
			"http://alipiba.com/image/18.jpg",
			"http://alipiba.com/image/19.jpg",
			"http://alipiba.com/image/20.jpg",
			"http://alipiba.com/image/21.jpg",
			"http://alipiba.com/image/22.jpg",
			"http://alipiba.com/image/23.jpg",
			"http://alipiba.com/image/24.jpg",
			"http://alipiba.com/image/25.jpg",
			"http://alipiba.com/image/26.jpg",
			"http://alipiba.com/image/27.jpg",
			"http://alipiba.com/image/28.jpg",
			"http://alipiba.com/image/29.jpg",
			"http://alipiba.com/image/30.jpg",
			"http://alipiba.com/image/31.jpg",
			"http://alipiba.com/image/32.jpg",
			"http://alipiba.com/image/33.jpg",
			"http://alipiba.com/image/34.jpg",
			"http://alipiba.com/image/35.jpg",
			"http://alipiba.com/image/36.jpg",
			"http://alipiba.com/image/37.jpg",
			"http://alipiba.com/image/38.jpg",
			"http://alipiba.com/image/39.jpg",
			"http://alipiba.com/image/40.jpg",
			"http://alipiba.com/image/41.jpg",
			"http://alipiba.com/image/42.jpg",
			"http://alipiba.com/image/43.jpg",
			"http://alipiba.com/image/44.jpg",
			"http://alipiba.com/image/45.jpg",
			"http://alipiba.com/image/46.jpg",
			"http://alipiba.com/image/47.jpg",
			"http://alipiba.com/image/48.jpg",
			"http://alipiba.com/image/49.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383299_1976.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383291_6518.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383291_8239.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383290_9329.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383290_1042.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383275_3977.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383265_8550.jpg",
            // 以下三张为本地图片,本地图片支持uri格式，形如file:// + 图片绝对路径
            "file:///storage/emulated/0/Camera/P41115-140216.jpg",
            "file:///storage/emulated/0/Camera/P41115-142950.jpg",
            "file:///storage/emulated/0/Camera/P50102-133614.jpg",
            "http://alipiba.com/image/not_found_haha.jpg", // 不存在的图片

			"http://alipiba.com/image/50.jpg",
			"http://alipiba.com/image/51.jpg",
			"http://alipiba.com/image/52.jpg",
			"http://alipiba.com/image/53.jpg",
			"http://alipiba.com/image/54.jpg",
			"http://alipiba.com/image/55.jpg",
			"http://alipiba.com/image/56.jpg",
			"http://alipiba.com/image/57.jpg",
			"http://alipiba.com/image/58.jpg",
			"http://alipiba.com/image/59.jpg",
			"http://alipiba.com/image/60.jpg",
			"http://alipiba.com/image/61.jpg",
			"http://alipiba.com/image/62.jpg",
			"http://alipiba.com/image/63.jpg",
			"http://alipiba.com/image/64.jpg",
			"http://alipiba.com/image/65.jpg",
			"http://alipiba.com/image/66.jpg",
			"http://alipiba.com/image/67.jpg",
			"http://alipiba.com/image/68.jpg",
			"http://alipiba.com/image/69.jpg",
			"http://alipiba.com/image/70.jpg"

    };
	private MyImageLoader myImageLoader;
}
