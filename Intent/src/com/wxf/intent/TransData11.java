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
 *测试通过TransData1中给TransData11的静态变量赋值，然后再该activity中接受该数据
 */
public class TransData11 extends Activity {

	// 定义两个静态变量name
	static String name;
	// 定义静态变量age
	static int age;
	private Button button2;
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trans_data11);
		// 给空间进行赋值，赋的值为静态变量的值。
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
