package com.wxf.ui;

import weibo4j.http.AccessToken;

import com.wxf.domain.Task;
import com.wxf.service.MainService;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class AccessTokenActivity extends Activity implements IActivity {

	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_access_token);
		Task task = new Task(Task.GET_ACCESSKON, null);
		MainService.addTask(task);
		MainService.addActivity(this);
		init();
	}

	public void init() {
		// TODO Auto-generated method stub
		progressDialog = new ProgressDialog(this);
		progressDialog.setMessage(getString(R.string.get_access_token));
		progressDialog.show();
	}

	public void refresh(Object... objects) {
		// TODO Auto-generated method stub
		progressDialog.dismiss();
		AccessToken accessToken = (AccessToken) objects[0];
		System.out.println("Got access token.");
		System.out.println("Access token: " + accessToken.getToken());
		System.out.println("uid:" + accessToken.getUserId());
		Log.i("accesstoken", accessToken + "");
	}
}
