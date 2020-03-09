package com.wxf.serviceall;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * @author Administrator
 *测试service的生命周期
 *使用service的步骤：
 *1.首先要实现一个service的子类。可以通过继承service或者IntentService类。
 *2.需要在AdnroidManifest.xml文件中进行配置。
 *通过<servicer>标签来注册
 *3.需要重写父类的onstartcommand()方法或者onBind()方法。
 *4。在activity中，需要通过intent来转发service并且调用startService()方法。
 *5.service/的声明周期是：首先调用一个service后，然后调用onstart()方法，
 *接着调用onstartCommand（）方法。
 */
public class FirstServie extends Service {

	public FirstServie() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * 
	 * 绑定service
	 */
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		System.out.println("FirstServie.onCreate()");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		System.out.println("FirstServie.onStartCommand()");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		System.out.println("FirstServie.onDestroy()");
	}
	

}
