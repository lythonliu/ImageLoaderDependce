package com.dongnao.imageloaderdependce.policy;


import com.dongnao.imageloaderdependce.request.BitmapRequest;

public class SerialPolicy implements LoadPolicy {

	@Override
	public int compareTo(BitmapRequest request1, BitmapRequest request2) {
		return request1.getSerialNO() - request2.getSerialNO();
	}

}
