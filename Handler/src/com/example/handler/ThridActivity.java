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
		
//		һ�㵱handlerͨ��post����runnable�ӿ�ʱ��ʵ��������û�п��� һ���µ��̣߳����Ǻ�activity����ͬһ��
//		�̣߳���ʱ����ͨ��HandlerThread ����ȡlooper�����������handler������һ�𣬿���һ���µ�
//		�߳�
		HandlerThread handlerThread=new HandlerThread("text");
//		���д���ز�����
		handlerThread.start();
		MyHandler handler=new MyHandler(handlerThread.getLooper());
		Message msg=handler.obtainMessage();
//		����һ��Bundle�����������ú�map'�������ƣ����Ե���һ�������map
//		msg����ͨ��setData()�����ʹ���������
		Bundle data=new Bundle();
		data.putString("hello", "hello world");
		data.putInt("ok", 333);
		msg.setData(data);
//		��msgͨ���Ǹ�handler�������ͷ����Ǹ�Ŀ��
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
