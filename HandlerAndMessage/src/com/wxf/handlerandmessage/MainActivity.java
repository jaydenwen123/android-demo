package com.wxf.handlerandmessage;

import junit.framework.Test;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private Button button;
	private TextView textView;
	private MyHandler handler;
	private Button button2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView=(TextView) findViewById(R.id.textView2);
		handler=new MyHandler();
		button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				new MyThread().start();
			}
		});
		button2=(Button) findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(MainActivity.this, SecondActivity.class);
				startActivity(intent);
			}
		});

	}
	
	/**
	 * @author Administrator
	 *
	 *实现自己的handler
	 */
	public class MyHandler extends Handler{
		/* (non-Javadoc)
		 * @see android.os.Handler#handleMessage(android.os.Message)
		 */
		/**
		 * 
		 * 处理消息
		 */
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			int arg1=msg.arg1;
			int arg2=msg.arg2;
			int what=msg.what;
			Object object=msg.obj;
			System.out.println(arg1+","+arg2+","+what+","+object);
			textView.setText(object.toString());
 		}
	}
//	通过子线程像ui主线程发送消息。
	public class MyThread extends Thread{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message message=Message.obtain();
			message.arg1=2;
			message.arg2=3;
			message.what=4;
			message.obj=new String("hello");
			handler.sendMessage(message);
		}
	}
}
