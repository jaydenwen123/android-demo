package com.example.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class ThridActivity extends Activity {

	private Button button3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thrid);
		button3=(Button) findViewById(R.id.button3);
		
//		一般当handler通过post调用runnable接口时。实际上它并没有开启 一个新的线程，而是和activity处于同一个
//		线程，当时可以通过HandlerThread 来获取looper。进而将其和handler来绑定在一起，开启一个新的
//		线程
		HandlerThread handlerThread=new HandlerThread("text");
//		该行代码必不可少
		handlerThread.start();
		MyHandler handler=new MyHandler(handlerThread.getLooper());
		Message msg=handler.obtainMessage();
//		创建一个Bundle对象，他的作用和map'作用类似，可以当做一个特殊的map
//		msg可以通过setData()来发送大量的数据
		Bundle data=new Bundle();
		data.putString("hello", "hello world");
		data.putInt("ok", 333);
		msg.setData(data);
//		该msg通过那个handler创建，就发给那个目标
		msg.sendToTarget();
	}
	
	class MyHandler extends Handler{

		public MyHandler() {
			super();
			// TODO Auto-generated constructor stub
		}

		public MyHandler(Looper looper) {
			super(looper);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Bundle bundle=msg.getData();
			System.out.println(bundle.getString("hello"));
			System.out.println(bundle.getInt("ok"));
		}
		
	}
	Runnable thread=new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("this is the threasd");
		}
	};
}
