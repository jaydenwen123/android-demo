package com.wxf.dialog;

import com.wxf.fragment.DatePickerFragment;
import com.wxf.fragment.TimerPickerFragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author Administrator
 *
 *��ʹ��dialogFragment������Ի��������ʹ��FragmentActivity��������ʹ�ñ�׼��activity
 */
public class SecondActivity extends FragmentActivity {

	private Button button1;
	private Button button2;
	private Button button3;
	private DatePickerFragment datePickerFragment;
	private TimerPickerFragment timerPickerFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		button1 = (Button) findViewById(R.id.button11);
		button1.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				// �����������Ի���
				ProgressDialog progressDialog = new ProgressDialog(
						SecondActivity.this);
				// ��ʾ�������Ի���
				progressDialog.show();
			}
		});
		
		/**
		 * 
		 * 
		 *����button2 �� button3��Ҫ��������ʱ������ڶԻ���
		 */
		button2 = (Button) findViewById(R.id.button22);
		button2.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				datePickerFragment = new DatePickerFragment();
				datePickerFragment.show(getSupportFragmentManager(), "date");
			}
		});

		button3 = (Button) findViewById(R.id.button33);
		button3.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				timerPickerFragment = new TimerPickerFragment();
				timerPickerFragment.show(getSupportFragmentManager(), "time");
			}
		});
	}
}
