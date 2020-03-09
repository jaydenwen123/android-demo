package com.wxf.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class RadioActivity extends Activity {

	private RadioGroup radioGroup;
	private RadioButton female;
	private RadioButton male;
	private CheckBox basketBall;
	private CheckBox volleytBall;
	private CheckBox footBall;
	private Button button ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.radio);
		radioGroup=(RadioGroup) findViewById(R.id.radioGoup);
		female=(RadioButton) findViewById(R.id.female);
		male=(RadioButton) findViewById(R.id.male);
		basketBall=(CheckBox) findViewById(R.id.basketBall);
		volleytBall=(CheckBox) findViewById(R.id.volleyBall);
		footBall=(CheckBox) findViewById(R.id.football);
		button=(Button) findViewById(R.id.toShowListView);
//		radioGroup触发的事件为：setOnCheckedChangeListener
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if(female.getId()==checkedId){
					Toast.makeText(RadioActivity.this, "你选择的是"+female.getText(), Toast.LENGTH_SHORT).show();
				}
				else if(male.getId()==checkedId){
					Toast.makeText(RadioActivity.this, "您好，你选择的是："+male.getText(), Toast.LENGTH_LONG).show();
				}
			}
		});
//		复选框触发的事件为：setOnCheckedChangeListener但二者的参数不同
		basketBall.setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					System.out.println(isChecked);
					Toast.makeText(RadioActivity.this, "你选择了"+buttonView.getText(), Toast.LENGTH_LONG).show();
				}{
					System.out.println("sno");
				}
			}
		});
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(RadioActivity.this, ProgressBarActivity.class);
				startActivity(intent);
			}
		});
	}
}
