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
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author Administrator
 * 
 *  ����������activity���໥��ֵ��
 */
public class TransData4 extends Activity {

	private Button addButton;
	private EditText one;
	private EditText two;
	private EditText three;
	private static final int REQUESTCODE = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trans_data4);
		one = (EditText) findViewById(R.id.one);
		two = (EditText) findViewById(R.id.two);
		three = (EditText) findViewById(R.id.three);
		addButton = (Button) findViewById(R.id.add);
		addButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(TransData4.this, TransData44.class);
				intent.putExtra("one", one.getText().toString());
				intent.putExtra("two", two.getText().toString());
				startActivityForResult(intent, REQUESTCODE);
			}
		});
	}

	/**
	 * 
	 * ���Ҫ��activity�н��ܴ�����һ��activity�з��ص�ֵ��
	 * ����Ҫ��дactivity�еĸú���
	 * �÷�����������������ֱ����£�
	 * 1.requestcode����ָ�Զ���������롣ֻ��startActivityForResult��onActivityResult��
	 * ��������������ͬʱ������Ϊ�Ƕԡ�
	 * 2.resultCode������룬�����Ƕ�Ӧ��Ҫ���ص����ݵ�activity�е��Ǹ��Զ���Ľ����
	 * 3.�������ݵ�intent��ͼ
	 * 
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUESTCODE) {
			if (resultCode == 2) {
				int result = data.getIntExtra("three", 0);
				System.out.println(result);
				three.setText(result + "");
			}
		}
	}
}
