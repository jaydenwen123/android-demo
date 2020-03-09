package com.wxf.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wxf.fragment.AlertDialogFragment1;
import com.wxf.fragment.AlertDialogFragment2;
import com.wxf.fragment.AlertDialogFragment3;
import com.wxf.fragment.AlertDialogFragment4;
import com.wxf.fragment.AlertDialogFragment5;

public class MainActivity extends FragmentActivity implements
		AlertDialogFragment5.NtoiceDialogListener {

	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private Button button5;
	private Button button6;
	private AlertDialogFragment1 alertDialogFragment1;
	private AlertDialogFragment2 alertDialogFragment2;
	private AlertDialogFragment3 alertDialogFragment3;
	private AlertDialogFragment4 alertDialogFragment4;
	private AlertDialogFragment5 alertDialogFragment5;
	// private AlertDialog alertDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		alertDialogFragment1 = new AlertDialogFragment1();
		alertDialogFragment2 = new AlertDialogFragment2();
		alertDialogFragment3 = new AlertDialogFragment3();
		alertDialogFragment4 = new AlertDialogFragment4();
		alertDialogFragment5 = new AlertDialogFragment5();
		// alertDialog=(AlertDialog)
		// alertDialogFragment.onCreateDialog(savedInstanceState);
		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				alertDialogFragment1.show(getSupportFragmentManager(),
						"dialog1");
			}
		});
		button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				alertDialogFragment2.show(getSupportFragmentManager(),
						"dialog2");
			}
		});
		button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				alertDialogFragment3.show(getSupportFragmentManager(),
						"dialog3");
			}
		});
		button4 = (Button) findViewById(R.id.button4);
		button4.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				alertDialogFragment4.show(getSupportFragmentManager(),
						"dialog4");
			}
		});
		button5 = (Button) findViewById(R.id.button5);
		button5.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				alertDialogFragment5.show(getSupportFragmentManager(),
						"dialog5");
			}
		});
		button6=(Button) findViewById(R.id.button6);
		button6.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(MainActivity.this, SecondActivity.class);
				startActivity(intent);
			}
		});
	}

	// ����������ȷ����ť��Ĳ�����
	
	/* (non-Javadoc)
	 * 
	 * dialog:�Ի���
	 */
	public void onDialogPositiveClick(DialogFragment dialog) {
		// TODO Auto-generated method stub
		//System.out.println(dialog);
//		ȡ���Ի����е����ݣ�Ȼ����в�����
		View view =((AlertDialogFragment5)dialog).getLayout();
		System.out.println(view+"--");
		EditText editText1 = (EditText) view.findViewById(R.id.username);
		EditText editText2 = (EditText) view.findViewById(R.id.password);
		Toast.makeText(MainActivity.this,
				"username:"+editText1.getText() + ",password:" + editText2.getText(), 1)
				.show();
	}

	// ����������ȡ����ť��Ĳ�����
	public void onDialogNegativeClick(DialogFragment dialog) {
		// TODO Auto-generated method stub
		dialog.dismiss();
		System.out.println("2");
	}

	// �������������԰�ť��Ĳ�����
	public void onDialogNeutralClick(DialogFragment dialog) {
		// TODO Auto-generated method stub

	}

}
