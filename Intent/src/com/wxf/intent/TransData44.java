package com.wxf.intent;

import com.wxf.app.MyApp;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TransData44 extends Activity {
	
	 private Button button6;
	 private TextView textView;
	 private int one;
	 private int two;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trans_data44);
		textView=(TextView) findViewById(R.id.result);
		Intent intent=getIntent();
		one=Integer.parseInt(intent.getStringExtra("one").toString());
		two=Integer.parseInt(intent.getStringExtra("two"));
		textView.setText(one +"+"+two+"="+"?");
		button6=(Button) findViewById(R.id.button6);
		button6.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				int three=one+two;
				intent.putExtra("three",three);
//				当需要在两个activity中相互传值的时候，需要满足两个条件。
//				1.使用setResult来返回要返回的结果
//				2.必须使用finish（）方法，来关闭该activity。
				setResult(2, intent);
				finish();
			}
		});
	}
}
