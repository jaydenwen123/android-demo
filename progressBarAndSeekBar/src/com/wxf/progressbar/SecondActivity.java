package com.wxf.progressbar;

import com.wxf.ratingbar.ThridActivity;
import com.wxf.seekbar.R;
import com.wxf.seekbar.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class SecondActivity extends Activity implements OnClickListener {

	private Button button1, button2;
	private ProgressBar progressBar;
	private Button toNext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		progressBar = (ProgressBar) findViewById(R.id.pnormal);
		button1 = (Button) findViewById(R.id.add);
		button2 = (Button) findViewById(R.id.sub);
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		toNext=(Button) findViewById(R.id.toThrid);
		toNext.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(SecondActivity.this,ThridActivity.class);
				startActivity(intent);
			}
		});
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.add:
			progressBar
					.setProgress((int) (progressBar.getProgress() + progressBar
							.getProgress() * 0.3));
			break;

		case R.id.sub:
			progressBar
					.setProgress((int) (progressBar.getProgress() -progressBar
							.getProgress() * 0.3));
			break;
		}
	}
}
