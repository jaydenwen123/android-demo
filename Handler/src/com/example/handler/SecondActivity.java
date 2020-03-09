package com.example.handler;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class SecondActivity extends Activity {

	private Button button;
	private ProgressBar progressBar;
	private Button toNext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		button=(Button) findViewById(R.id.button);
		progressBar=(ProgressBar) findViewById(R.id.secondBar);
		button.setOnClickListener(new buttonListener());
		toNext=(Button) findViewById(R.id.toThrid);
		toNext.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(SecondActivity.this, ThridActivity.class);
				startActivity(intent);
			}
		});
	}
	
	private class  buttonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			progressBar.setVisibility(View.VISIBLE);
			handler.post(thread);
		}
		
	}
	
//	通过消息队列来实现对进度条的控制
	Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			progressBar.setProgress(msg.arg1);
			handler.post(thread);
		};
	};
	
	Runnable thread=new Runnable() {
		
		int i=0;
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg=handler.obtainMessage();
			i=i+10;
			msg.arg1=i;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(i>100){
				handler.removeCallbacks(thread);
				System.out.println("end ============");
			}else {
				
				handler.sendMessage(msg);
			}
		}
	};
	
}
