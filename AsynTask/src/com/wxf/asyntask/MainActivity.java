package com.wxf.asyntask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.R.integer;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.wxf.http.HttpUtil;

public class MainActivity extends Activity {

	private Button button;
	private ImageView imageView;
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button) findViewById(R.id.button1);
		imageView = (ImageView) findViewById(R.id.imageView1);
		button.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 获取的图片的url
//				String url = "http://p1.so.qhmsg.com/bdr/326__/t01129201bb1117aad1.jpg";
				String url = "http://p4.so.qhmsg.com/t01d82de2a9063d1d74.jpg";
				new MyAsyncTask().execute(url);
			}
		});

	}

	public class MyAsyncTask extends AsyncTask<String, Integer, Bitmap> {
		/**
		 * 
		 * download the picture
		 */
		@Override
		protected Bitmap doInBackground(String... params) {
			// TODO Auto-generated method stub
			byte[] buffer = new byte[1024];
			InputStream inputStream = null;
			ByteArrayOutputStream byteArrayOutputStream = null;
			long fileSize = 0;
			Bitmap butBitmap = null;
			int flag = 0;
			try {
				// 第一种方式直接通过封装好的方法来下载。
				/*
				 * InputStream inputStream=HttpUtil.getInputStream(params[0]);
				 * buffer = HttpUtil.getByteArrayByInputStream(inputStream);
				 * butBitmap=BitmapFactory.decodeByteArray(buffer , 0,
				 * buffer.length);
				 */
				// 第二种方式，通过设置进度条的方式来下载。
				/*inputStream = HttpUtil.getInputStream(params[0]);
				byteArrayOutputStream = new ByteArrayOutputStream();
				int len = 0;
				while ((len = inputStream.read(buffer)) != -1) {
					byteArrayOutputStream.write(buffer, 0, len);
					fileSize += len;
					flag++;
					System.out.println(flag);
				}
				byteArrayOutputStream.flush();
//				还有些问题。
				for (int i = 0; i < flag; i++) {
					System.out.println(buffer.length*i / fileSize*100+"--");
					publishProgress((int) (buffer.length*i / fileSize * 100));
				}

				byte[] data = byteArrayOutputStream.toByteArray();
				butBitmap = BitmapFactory.decodeByteArray(data, 0, data.length);*/
//				第三种方法。通过httpclient类来实现现在。
//				第一步构建一个client对象
				HttpClient client=new DefaultHttpClient();
//				第二步，设置要发送的请求方式
				HttpGet httpGet=new HttpGet(params[0]);
//				发送请求。
				HttpResponse response=client.execute(httpGet);
				if(response.getStatusLine().getStatusCode()==200){
					HttpEntity entity=response.getEntity();
					if(entity!=null){
						inputStream=entity.getContent();
						byteArrayOutputStream=new ByteArrayOutputStream();
						int len=0;
						fileSize=entity.getContentLength();
//						执行下载文件的操作。
						int total=0;
						int values=0;
						while((len=inputStream.read(buffer))!=-1){
							byteArrayOutputStream.write(buffer,0,len);
//							获取下载的文件字节大小。
							total+=len;
//							转换为小于100的整数，然后对进度条进行赋值。
							values=(int)((total/(float)fileSize)*100);
//							通知进度条更新。
							publishProgress(values);
							
						}
						byteArrayOutputStream.flush();
					}
					
				}
//				将字节里面读出来的内容转换为字节数组。然后构建一个位图对象。
				byte[] data = byteArrayOutputStream.toByteArray();
				butBitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
				
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

			return butBitmap;
		}

		/**
		 * 
		 * the first step to init the progress bar
		 * 
		 */
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			// 第一种显示进度条的方式是以圈的方式显示。
			/*
			 * progressDialog=new ProgressDialog(MainActivity.this);
			 * progressDialog.setTitle("正在下载");
			 * progressDialog.setMessage("请稍等。。。。。。"); progressDialog.show();
			 */
			// 第二种以水平进度条的方式显示出来。
			progressDialog = new ProgressDialog(MainActivity.this);
			progressDialog.setTitle("正在下载");
			progressDialog.setMessage("请稍等。。。。。。");
			progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			progressDialog.show();
		}

		/**
		 * 
		 * the last step to post the result
		 */
		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			imageView.setImageBitmap(result);
			progressDialog.dismiss();
		}

		/**
		 * 
		 * to update the progess bar progress
		 */
		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			//super.onProgressUpdate(values);
//			给进度条赋值。
			progressDialog.setProgress(values[0]);
		}
	}

}
