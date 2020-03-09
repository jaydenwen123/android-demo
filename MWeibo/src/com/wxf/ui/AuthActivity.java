package com.wxf.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.wxf.domain.Task;
import com.wxf.service.MainService;

public class AuthActivity extends Activity implements IActivity {

	// private TextView textView;
	// private Button button;

	private Button button;
	private Dialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_auth);
		// 启动service
		Intent intent = new Intent(this, MainService.class);
		startService(intent);
		View view = View.inflate(this, R.layout.dialog_auth, null);
		dialog = new Dialog(this, R.style.auth_dialog);
		dialog.setContentView(view);
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
		button = (Button) view.findViewById(R.id.begin);
		button.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Task task = new Task(Task.AUTH, null);
				MainService.addTask(task);
				//dialog.dismiss();
				Intent intent2 = new Intent(AuthActivity.this,
						WebViewActivity.class);
				startActivity(intent2);
			}
		});
		// 将该activity添加到容器中
		MainService.addActivity(this);
	}

	public void init() {
		// TODO Auto-generated method stub

	}

	public void refresh(Object... objects) {
		// TODO Auto-generated method stub
		// textView.setText(objects[0].toString());
		System.out.println(objects[0]);
		// 因为该按钮是一个图片带字的所以没有必要再去传一个文字过来。
		// button.setText(objects[0].toString());

	}

}
