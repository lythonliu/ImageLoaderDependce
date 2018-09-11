package com.dongnao.imageloaderdependce.cache;


import android.graphics.Bitmap;

import com.dongnao.imageloaderdependce.request.BitmapRequest;

public class NoCache implements BitmapCache {

	@Override
	public void put(BitmapRequest request, Bitmap bitmap) {
		// TODO Auto-generated method stub

	}

	@Override
	public Bitmap get(BitmapRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(BitmapRequest request) {
		// TODO Auto-generated method stub

	}

}
