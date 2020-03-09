package com.wxf.serviceall;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends Activity {

	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private Intent intent1;
	private Intent intent2;
	private Intent intent3;
	private Button button5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button1.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 开始一个service
				intent1 = new Intent(FirstActivity.this, FirstServie.class);
				startService(intent1);
			}
		});

		button2.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 结束service
				stopService(intent1);
			}
		});

		button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 开始secondService，下载图片。不过。secondService
				intent2 = new Intent(FirstActivity.this, SecondService.class);
				startService(intent2);
			}
		});

		button4 = (Button) findViewById(R.id.button4);
		button4.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent3 = new Intent(FirstActivity.this, ThridService.class);
				startService(intent3);
			}
		});
		
		button5=(Button) findViewById(R.id.button5);
		button5.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(FirstActivity.this,SecondActivity.class);
				startActivity(intent);
			}
		});
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onStop() 结束secondService
	 */
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		if(intent2!=null){
			stopService(intent2);
		}
		if(intent3!=null){
			stopService(intent3);
		}
	}
}
