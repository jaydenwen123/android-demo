package com.wxf.intent;

import com.wxf.app.MyApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TransData22 extends Activity {
	
	 private Button button4;
	 private TextView textView;
	 private MyApp app;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trans_data22);
		textView=(TextView) findViewById(R.id.textView2);
		app=(MyApp) getApplication();
		textView.setText(app.getName());
		button4=(Button) findViewById(R.id.button4);
		button4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(TransData22.this
						,TransData3.class);
				startActivity(intent);
			}
		});
	}
}
