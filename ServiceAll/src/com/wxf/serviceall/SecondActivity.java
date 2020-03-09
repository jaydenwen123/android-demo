package com.wxf.serviceall;

import com.wxf.serviceall.ForthService.LocalBinder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SecondActivity extends Activity {

	private Button button;
	private Button button2;
	private ForthService forthService;
	// 用来判断是否绑定service
	private boolean flag = false;
	private LocalBinder binder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		button = (Button) findViewById(R.id.button11);
		button.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				//if (flag) {
					/*Integer value = forthService.transferData();
					System.out.println("--->" + value);*/
					Parcel data=Parcel.obtain();
					Parcel reply=Parcel.obtain();
					data.writeInt(3);
					data.writeString("welcome");
					try {
						binder.transact(Binder.LAST_CALL_TRANSACTION, data, reply, 0);
						System.out.println("-SecondActivity receive-->"+reply.readInt());
						System.out.println("-SecondActivity--receive->"+reply.readString());
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			//}
		});
		button2 = (Button) findViewById(R.id.button22);
		button2.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SecondActivity.this,
						ForthService.class);
				// bindService(intent, conn, Context.BIND_AUTO_CREATE);
				bindService(intent, conn, Context.BIND_AUTO_CREATE);
				System.out.println("SecondActivity.onStart()");
			}
		});
		System.out.println("SecondActivity.onCreate()");
	}

	private ServiceConnection conn = new ServiceConnection() {

		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			flag = false;
			System.out.println("onServiceDisconnected()");
		}

		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			binder = (LocalBinder) service;
			forthService = binder.getService();
			flag = true;
			System.out.println("onServiceConnected()");
		}
	};

	/**
	 * 
	 * 取消绑定service
	 */
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		if (flag) {
			unbindService(conn);
			flag = false;
			System.out.println("SecondActivity.onStop()");
		}
	}

}
