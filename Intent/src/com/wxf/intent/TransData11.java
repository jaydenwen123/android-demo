package com.wxf.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author Administrator
 *����ͨ��TransData1�и�TransData11�ľ�̬������ֵ��Ȼ���ٸ�activity�н��ܸ�����
 */
public class TransData11 extends Activity {

	// ����������̬����name
	static String name;
	// ���徲̬����age
	static int age;
	private Button button2;
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trans_data11);
		// ���ռ���и�ֵ������ֵΪ��̬������ֵ��
		textView = (TextView) findViewById(R.id.textView1);
		textView.setText("name=" + name + ",age=" + age);
		button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(TransData11.this, TransData2.class);
				startActivity(intent);
			}
		});
	}
}
