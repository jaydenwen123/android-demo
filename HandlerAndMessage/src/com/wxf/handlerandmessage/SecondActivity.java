package com.wxf.handlerandmessage;

import com.wxf.handlerandmessage.MainActivity.MyHandler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends Activity {

	private Button button;
	private TextView textView;
	private Handler handler;
	private Button button2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		textView=(TextView) findViewById(R.id.textView11);
		button=(Button) findViewById(R.id.button11);
//		开启新线程。
		new MyThread().start();
		button.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Message message=Message.obtain();
				message.arg1=3;
				message.obj=new String("hellll");
				handler.sendMessage(message);
			}
		});
		button2=(Button) findViewById(R.id.button22);
		button2.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(SecondActivity.this,ThridActivity.class);
				startActivity(intent);
			}
		});
		
	}
	
//	通过ui主线程发送数据给子线程。
	public class MyThread extends Thread{
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			Looper.prepare();
			handler=new Handler(){
				public void handleMessage(Message msg) {
//					不能再子线程中更新ui
				//textView.setText(msg.obj.toString());
					System.out.println(msg.obj);
				};
			};
			Looper.loop();
		}
		
	}
}
