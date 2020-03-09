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
				// ��ȡ��ͼƬ��url
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
				// ��һ�ַ�ʽֱ��ͨ����װ�õķ��������ء�
				/*
				 * InputStream inputStream=HttpUtil.getInputStream(params[0]);
				 * buffer = HttpUtil.getByteArrayByInputStream(inputStream);
				 * butBitmap=BitmapFactory.decodeByteArray(buffer , 0,
				 * buffer.length);
				 */
				// �ڶ��ַ�ʽ��ͨ�����ý������ķ�ʽ�����ء�
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
//				����Щ���⡣
				for (int i = 0; i < flag; i++) {
					System.out.println(buffer.length*i / fileSize*100+"--");
					publishProgress((int) (buffer.length*i / fileSize * 100));
				}

				byte[] data = byteArrayOutputStream.toByteArray();
				butBitmap = BitmapFactory.decodeByteArray(data, 0, data.length);*/
//				�����ַ�����ͨ��httpclient����ʵ�����ڡ�
//				��һ������һ��client����
				HttpClient client=new DefaultHttpClient();
//				�ڶ���������Ҫ���͵�����ʽ
				HttpGet httpGet=new HttpGet(params[0]);
//				��������
				HttpResponse response=client.execute(httpGet);
				if(response.getStatusLine().getStatusCode()==200){
					HttpEntity entity=response.getEntity();
					if(entity!=null){
						inputStream=entity.getContent();
						byteArrayOutputStream=new ByteArrayOutputStream();
						int len=0;
						fileSize=entity.getContentLength();
//						ִ�������ļ��Ĳ�����
						int total=0;
						int values=0;
						while((len=inputStream.read(buffer))!=-1){
							byteArrayOutputStream.write(buffer,0,len);
//							��ȡ���ص��ļ��ֽڴ�С��
							total+=len;
//							ת��ΪС��100��������Ȼ��Խ��������и�ֵ��
							values=(int)((total/(float)fileSize)*100);
//							֪ͨ���������¡�
							publishProgress(values);
							
						}
						byteArrayOutputStream.flush();
					}
					
				}
//				���ֽ����������������ת��Ϊ�ֽ����顣Ȼ�󹹽�һ��λͼ����
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
			// ��һ����ʾ�������ķ�ʽ����Ȧ�ķ�ʽ��ʾ��
			/*
			 * progressDialog=new ProgressDialog(MainActivity.this);
			 * progressDialog.setTitle("��������");
			 * progressDialog.setMessage("���Եȡ�����������"); progressDialog.show();
			 */
			// �ڶ�����ˮƽ�������ķ�ʽ��ʾ������
			progressDialog = new ProgressDialog(MainActivity.this);
			progressDialog.setTitle("��������");
			progressDialog.setMessage("���Եȡ�����������");
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
//			����������ֵ��
			progressDialog.setProgress(values[0]);
		}
	}

}
