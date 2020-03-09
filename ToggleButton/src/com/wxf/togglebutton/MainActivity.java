package com.wxf.togglebutton;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

	private ToggleButton toggleButton;
	private LinearLayout linearLayout;
	private Switch switch2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		linearLayout=(LinearLayout) findViewById(R.id.linearLayout);
		switch2=(Switch) findViewById(R.id.helo);
//		触发的事件
		switch2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					System.out.println("checked");
				}else {
					System.out.println("unckecdked");
				}
			}
		});
		toggleButton=(ToggleButton) findViewById(R.id.toggleButton);
//		toggleButton触发的事件
		toggleButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
				{
					linearLayout.setOrientation(LinearLayout.HORIZONTAL);
					Toast.makeText(MainActivity.this, "你的操作是："+buttonView.getText(), 1).show();
				}else {
					linearLayout.setOrientation(LinearLayout.VERTICAL);
					Toast.makeText(MainActivity.this, "你的操作是："+buttonView.getText(), 1).show();
				}
			}
		});
	}
}
