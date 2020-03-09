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
//				����Ҫ������activity���໥��ֵ��ʱ����Ҫ��������������
//				1.ʹ��setResult������Ҫ���صĽ��
//				2.����ʹ��finish�������������رո�activity��
				setResult(2, intent);
				finish();
			}
		});
	}
}
