package com.wxf.intent;

import com.wxf.app.MyApp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TransData33 extends Activity {
	
	 private Button button6;
	 private TextView textView;
	 private MyApp app;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trans_data33);
//		关键代码:以下两行
		textView=(TextView) findViewById(R.id.textView3);
		ClipboardManager clipboardManager=(ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
        textView.setText(clipboardManager.getText());
		button6=(Button) findViewById(R.id.button6);
		button6.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(TransData33.this
						,TransData4.class);
				startActivity(intent);
			}
		});
	}
}
