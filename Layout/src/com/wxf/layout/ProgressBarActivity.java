package com.wxf.layout;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class ProgressBarActivity extends Activity {

	private ProgressBar progressBar1;
	private ProgressBar progressBar2;
	private Button button;
	private Button  button2;
	int i=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.progressbar);
		progressBar1=(ProgressBar) findViewById(R.id.progress1);
		progressBar2=(ProgressBar) findViewById(R.id.progress2);
		button=(Button) findViewById(R.id.button1);
		button2=(Button) findViewById(R.id.button2);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(i==0){
					progressBar1.setVisibility(View.VISIBLE);
					progressBar2.setVisibility(View.VISIBLE);
				}
				else if(i<200){
					progressBar2.setProgress(i+10);
					progressBar2.setMax(200);
					progressBar2.setSecondaryProgress(i+20);
					progressBar1.setProgress(i+10);
				}else {
					progressBar1.setVisibility(View.INVISIBLE);
					progressBar2.setVisibility(View.INVISIBLE);
					i=0;
				}
				i=i+10;
			}
		});
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(ProgressBarActivity.this, ListViewActivity.class);
				startActivity(intent);
			}
		});
	}
}
