package com.wxf.service;

import android.R.string;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class FirstSerive extends Service{

	/**
	 * 
	 * ��service��activity����һ��
	 * ���Դ�service�л�����ݡ�
	 */
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		MyBinder binder=new MyBinder();
		System.out.println("��activity�н�����������");
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
	 * ��������������������service�������������
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
