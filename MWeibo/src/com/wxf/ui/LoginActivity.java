package com.wxf.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class LoginActivity extends Activity {

	private Spinner loginOption;
	private Button loginBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置无标题，
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 设置全屏.其实只是把底部的操作的三个键去掉而已。
		// getWindow().getDecorView().setSystemUiVisibility(
		// View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
		setContentView(R.layout.activity_login);
		loginBtn = (Button) findViewById(R.id.login);
		loginBtn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(LoginActivity.this,
						AuthActivity.class);
				startActivity(intent);
			}
		});
		// 获取该下拉列表
		loginOption = (Spinner) findViewById(R.id.loginOption);
		// 创建一个适配器。
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, getResources()
						.getStringArray(R.array.spinner_array));
		// 给下拉列表设置适配器
		loginOption.setAdapter(adapter);
	}
}
