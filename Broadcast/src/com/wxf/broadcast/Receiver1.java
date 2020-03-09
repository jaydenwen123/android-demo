package com.wxf.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Receiver1 extends BroadcastReceiver{

	public Receiver1() {
		// TODO Auto-generated constructor stub
		System.out.println("receiver constructor");
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("onReceiver method ... ");
	}

}
