package com.wxf.service;

import java.util.Calendar;

import com.wxf.service.FirstSerive.MyBinder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private Button button1;
	private Button button2;
	private Button button3;
	private Intent intent;
	private String string;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button1=(Button) findViewById(R.id.button1);
		button2=(Button) findViewById(R.id.Button2);
		button1.setOnClickListener(new startServiceButtonListener());
		button2.setOnClickListener(new endServiceButtonListener());
		button3=(Button) findViewById(R.id.button3);
		button3.setOnClickListener(new binderServiceButtonListener());
	}
	
	class startServiceButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			intent=new Intent();
			intent.setClass(MainActivity.this,FirstSerive.class);
			startService(intent);
		}
		
	}
	
	class endServiceButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			stopService(intent);
		}
	}
	
	class  binderServiceButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			ServiceConnection conn=new ServiceConnection() {
				
				@Override
				public void onServiceDisconnected(ComponentName name) {
					// TODO Auto-generated method stub
					System.out.println("ServiceConnection.onServiceDisconnected-->"+name);
				}
				
				@Override
				public void onServiceConnected(ComponentName name, IBinder service) {
					// TODO Auto-generated method stub
					System.out.println("ServiceConnection.onServiceConnected-->"+name+",service="+service);
					MyBinder binder=(MyBinder) service;
					String data=binder.testBinder();
					System.out.println("data="+data);
				}
			};
			intent.putExtra("hello", "23");
			intent.putExtra("str", "1222s");
			bindService(intent, conn,  BIND_AUTO_CREATE);
			Toast.makeText(MainActivity.this, "binder service", Toast.LENGTH_LONG).show();
		}
		
		
	}
	
	public void test(){
		
	}
	
	
}
