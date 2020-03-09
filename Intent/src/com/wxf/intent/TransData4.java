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
 *  测试在两个activity中相互传值。
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
	 * 如果要从activity中接受从另外一个activity中返回的值，
	 * 则需要重写activity中的该函数
	 * 该方法的三个参数含义分别如下：
	 * 1.requestcode：是指自定义的请求码。只有startActivityForResult和onActivityResult的
	 * 两个的请求码相同时，才认为是对。
	 * 2.resultCode：结果码，该码是对应与要返回的数据的activity中的那个自定义的结果码
	 * 3.接收数据的intent意图
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
