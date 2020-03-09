package com.wxf.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Receiver2 extends BroadcastReceiver{

	public Receiver2() {
		// TODO Auto-generated constructor stub
		System.out.println("receiver constructor2");
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("onReceiver method 2... ");
	}

}
