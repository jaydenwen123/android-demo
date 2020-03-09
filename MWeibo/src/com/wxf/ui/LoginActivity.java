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
		// �����ޱ��⣬
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		// ����ȫ��.��ʵֻ�ǰѵײ��Ĳ�����������ȥ�����ѡ�
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
		// ��ȡ�������б�
		loginOption = (Spinner) findViewById(R.id.loginOption);
		// ����һ����������
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, getResources()
						.getStringArray(R.array.spinner_array));
		// �������б�����������
		loginOption.setAdapter(adapter);
	}
}
