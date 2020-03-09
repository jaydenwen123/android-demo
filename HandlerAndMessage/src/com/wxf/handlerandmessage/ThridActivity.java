package com.wxf.handlerandmessage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class ThridActivity extends Activity {

	private Button button;
	private ImageView imageView;
	private ThridHandler handler;
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thrid);
		// 开始一个线程。
		final HandlerThread handlerThread = new HandlerThread("download");
		handlerThread.start();
		// 通过带looper的构造器来实例化handler
		// 通过该方法可以实现开启一个新的异步线程，但是不能在一步线程中更新ui控件。
		//handler=new ThridHandler(handlerThread.getLooper());
		handler = new ThridHandler();
		
		imageView = (ImageView) findViewById(R.id.imageView1);
		button = (Button) findViewById(R.id.button33);
		button.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 获取handler构建真的异步操作。
//				显示进度条对画框
				progressDialog=new ProgressDialog(ThridActivity.this);
				progressDialog.setTitle("提示：");
				progressDialog.setMessage("正在下载请等待：。。。");
				progressDialog.show();
				new MyThread().start();
			}
		});
	}

	public class ThridHandler extends Handler {
		//
		public ThridHandler() {
			// TODO Auto-generated constructor stub
			super();
		}

		public ThridHandler(Looper looper) {
			super(looper);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			byte[] data = (byte[]) msg.obj;
			Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
			imageView.setImageBitmap(bitmap);
//			影藏对话框。
			progressDialog.dismiss();
		}
	}

	public class MyThread extends Thread {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			Message message = Message.obtain();
			byte[] data = getDataFromInternet();
			message.obj = data;
			handler.sendMessage(message);
		}

//		通过方法来下载文件。将其文件流转为字节数组。
		private byte[] getDataFromInternet() {
			// TODO Auto-generated method stub
			HttpClient client = new DefaultHttpClient();
			String uri = "http://p4.so.qhmsg.com/bdr/326__/t019d1bddae4cabc0ae.jpg";
			HttpGet httpGet = new HttpGet(uri);
			HttpResponse response = null;
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] data = null;
			InputStream inputStream = null;
			try {
				response = client.execute(httpGet);
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					inputStream = entity.getContent();
					int len = 0;
					byte[] buffer = new byte[1024];
					while ((len = inputStream.read(buffer)) != -1) {
						outputStream.write(buffer, 0, len);
					}
				}
				outputStream.flush();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (inputStream != null) {
					try {
						inputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			data = outputStream.toByteArray();
			return data;
		}
	}
}
