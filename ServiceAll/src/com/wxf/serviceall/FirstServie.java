package com.wxf.serviceall;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * @author Administrator
 *����service����������
 *ʹ��service�Ĳ��裺
 *1.����Ҫʵ��һ��service�����ࡣ����ͨ���̳�service����IntentService�ࡣ
 *2.��Ҫ��AdnroidManifest.xml�ļ��н������á�
 *ͨ��<servicer>��ǩ��ע��
 *3.��Ҫ��д�����onstartcommand()��������onBind()������
 *4����activity�У���Ҫͨ��intent��ת��service���ҵ���startService()������
 *5.service/�����������ǣ����ȵ���һ��service��Ȼ�����onstart()������
 *���ŵ���onstartCommand����������
 */
public class FirstServie extends Service {

	public FirstServie() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * 
	 * ��service
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
