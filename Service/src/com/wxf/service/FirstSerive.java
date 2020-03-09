package com.wxf.service;

import android.R.string;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class FirstSerive extends Service{

	/**
	 * 
	 * 把service和activity绑定在一起。
	 * 可以从service中获得数据。
	 */
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		MyBinder binder=new MyBinder();
		System.out.println("从activity中接受来的数据");
		System.out.println(intent.getStringExtra("hello"));
		System.out.println(intent.getStringExtra("str"));
		return binder;
	}
	
 class MyBinder extends Binder{
	 public MyBinder() {
		// TODO Auto-generated constructor stub
	}
		public String testBinder(){
			return "data";
		}
	};
	
	/**
	 * 
	 * 以下三个函数用来测试service组件的生命周期
	 */

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		System.out.println("FirstSerive.onCreate()");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		System.out.println("FirstSerive.onStartCommand()");
		return super.onStartCommand(intent, flags, startId);
		
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		System.out.println("FirstSerive.onDestroy()");
	}
	
	

}
