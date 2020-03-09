package com.wxf.button;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener,
		OnTouchListener, OnKeyListener, OnFocusChangeListener {

	private Button button1, button2;
	private int value = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button1.setOnClickListener(this);
		button1.setOnKeyListener(this);
		button1.setOnTouchListener(this);
		button1.setOnFocusChangeListener(this);
		button2.setOnClickListener(this);
		button2.setOnFocusChangeListener(this);
		button2.setOnTouchListener(this);
		button2.setOnKeyListener(this);
	}

	public boolean onKey(View v, int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		Button button=(Button) v;
		if(KeyEvent.ACTION_UP==event.getAction()){
			button.setBackgroundResource(R.drawable.button3);
		}else if(KeyEvent.ACTION_DOWN==event.getAction()){
			button.setBackgroundResource(R.drawable.button2);
		}
		return true;
	}

	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		if (event.getAction() == MotionEvent.ACTION_UP) {
			v.setBackgroundResource(R.drawable.button1);
		} else if (event.getAction() == MotionEvent.ACTION_DOWN) {
			v.setBackgroundResource(R.drawable.button3);
		}
		return false;
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		Button button = (Button) v;
		if (value == 1 && button.getWidth() < getWindowManager()
				.getDefaultDisplay().getWidth()) {

			button.setWidth((int) (getWindowManager().getDefaultDisplay()
					.getWidth() * 0.1 + value * button.getWidth()));
			value=-1;
		} else {
			button.setWidth((int) (getWindowManager().getDefaultDisplay()
					.getWidth() * 0.1 + value * button.getWidth()));
			value=1;
		}
	}

	public void onFocusChange(View v, boolean hasFocus) {
		// TODO Auto-generated method stub
		if(hasFocus){
			v.setBackgroundResource(R.drawable.button3);
		}
	}

}
