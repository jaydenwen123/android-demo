package com.example.download;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.wxf.file.util.DownloadUtil;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.text.AndroidCharacter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DownloadActivity extends Activity {

	private Button downloadTxt;
	private Button downloadFile;
	private DownloadUtil downloadUtil=new DownloadUtil();

	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_download);
		downloadTxt = (Button) findViewById(R.id.download1);
		downloadFile = (Button) findViewById(R.id.download2);
		downloadTxt.setOnClickListener(new DownloadTxtButtonListener());
		downloadFile.setOnClickListener(new DownloadFileButtonListener());
	}

	private class DownloadTxtButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			System.out.println("download button is clicked");
//	这儿使用线程是应为在android4.0以后不允许直接在activity中，
//			进行一些耗时的操作，如文件的下载等，必须放入到新的独立的线程中	
//			android.os.NetworkOnMainThreadException
			new Thread(){
				public void run() {
					System.out.println("begin download");
					int flag=downloadUtil.downloadFile("http://baidu.com", "wen/picture/", "seconds.txt");
					System.out.println("return state="+flag);
					System.out.println("download success");
					
				};
			}.start();
		}
		
	/*	new Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String strUrl = "http://www.baidu.com";
				InputStream is = null;
				OutputStream os = null;
				BufferedReader bufferedReader=null;
				try {
					URL url = new URL(strUrl);
					 HttpURLConnection connection=(HttpURLConnection)
					 url.openConnection();
					 System.out.println("connection="+connection);
					  is=connection.getInputStream();
					  bufferedReader=new BufferedReader(new InputStreamReader(is));
					 System.out.println("input stram ="+is);
					 StringBuffer str=new StringBuffer();
					 String line=""; 
					 while((line=bufferedReader.readLine())!=null){
						 str.append(line);
						 }
					 System.out.println("the results:"+str.toString());
					System.out.println("download success");
					System.out.println("sd卡的目录："+Environment.getExternalStorageDirectory());
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}.start();
	}*/

	}

	private class DownloadFileButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

		}

	}
}
