package com.dongnao.imageloaderdependce.policy;


import com.dongnao.imageloaderdependce.request.BitmapRequest;

public class ReversePolicy implements LoadPolicy {

	@Override
	public int compareTo(BitmapRequest request1, BitmapRequest request2) {
		return request2.getSerialNO() - request1.getSerialNO();
	}

}
