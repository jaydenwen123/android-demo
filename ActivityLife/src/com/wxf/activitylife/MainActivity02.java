package com.wxf.activitylife;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter.LengthFilter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity02 extends Activity {
	
	private Button button;
	private TextView textView1;
	private TextView textView2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity02);
		System.out.println("MainActivity02.onCreate()");
		button=(Button) findViewById(R.id.button2);
		textView1=(TextView) findViewById(R.id.first);
		textView2=(TextView) findViewById(R.id.second);
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				String firstStr=textView1.getText().toString();
				String secondStr=textView2.getText().toString();
				intent.putExtra("first",firstStr);
				intent.putExtra("second", secondStr);
				intent.setClass(MainActivity02.this, Activity03.class);
				startActivity(intent);
				}
		});
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		System.out.println("MainActivity02.onStart()");
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		System.out.println("MainActivity02.onResume()");
	}
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		System.out.println("MainActivity02.onRestart()");
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		System.out.println("MainActivity02.onPause()");
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		System.out.println("MainActivity02.onStop()");
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		System.out.println("MainActivity02.onDestroy()");
	}
	/**
	 * 
	 * �����ģ�����ϵ�menu�˵�ʱ�����������Ӳ˵���
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
			menu.add(0, 0, 0, R.string.back);
			menu.add(0,1,0,R.string.help);
		return super.onCreateOptionsMenu(menu);
	}
	/**
	 * ���Ӳ˵���ѡ��ʱִ�в���
	 */
	@SuppressLint("ShowToast") @Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		System.out.println(item.getTitleCondensed());
		if(item.getTitleCondensed().equals("����")){
			Toast.makeText(MainActivity02.this, R.string.msg, Toast.LENGTH_LONG);
		}else if(item.getTitleCondensed().equals("����")){
			System.out.println(item.getTitleCondensed());
			finish();
		}
		return super.onMenuItemSelected(featureId, item);
	}
	
	
	
	
}
