package com.wxf.broadcast;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Broadcast2Activity extends Activity {

	private Button button1;
	private Button button2;
	private Receiver2 receiver;
	private Button button3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.broadcast2);
		button1 = (Button) findViewById(R.id.registerBroadcast);
		button2 = (Button) findViewById(R.id.unregisterBroadcast);
		button3=(Button) findViewById(R.id.sendBroadcast);
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				receiver=new Receiver2();
				IntentFilter intentFilter=new IntentFilter();
				intentFilter.addAction(Intent.ACTION_EDIT);
				Broadcast2Activity.this.registerReceiver(receiver, intentFilter);
			}
		});
		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 Broadcast2Activity.this.unregisterReceiver(receiver);
			}
		});
		button3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setAction(Intent.ACTION_EDIT);
				Broadcast2Activity.this.sendBroadcast(intent);
			}
		});

	}
}
