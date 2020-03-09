package com.wxf.json;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.wxf.domain.Person;
import com.wxf.http.HttpUtil;
import com.wxf.json.SecondActivity.ButtonListener;
import com.wxf.service.FastJsonTool;
import com.wxf.service.GsonTool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ThridActivity extends Activity {

	private static final String TAG = "thrid";
	private Button button111, button222, button333, button444;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thrid);
		button111 = (Button) findViewById(R.id.button111);
		button111.setOnClickListener(new ButtonListener());
		button222 = (Button) findViewById(R.id.button222);
		button222.setOnClickListener(new ButtonListener());
		button333 = (Button) findViewById(R.id.button333);
		button333.setOnClickListener(new ButtonListener());
		button444 = (Button) findViewById(R.id.button444);
		button444.setOnClickListener(new ButtonListener());
	}

	class ButtonListener implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub

			switch (v.getId()) {
			case R.id.button111:
				new Thread() {
					public void run() {

						String path1 = "http://122.207.36.133:8080/AndroidWebServer/login1?select=person";
						InputStream inputStream=HttpUtil.getInputStream(path1);
						String data1 = HttpUtil.changeInputStreamToString(
							inputStream	, "utf-8");
						Log.i(TAG, data1);
						Person person = FastJsonTool.getObject(data1, Person.class);
						Log.i(TAG, person.toString());
					};
				}.start();
				break;
			case R.id.button222:
				new Thread() {
					public void run() {
						String path2 = "http://122.207.36.133:8080/AndroidWebServer/login1?select=persons";
						String data2 = HttpUtil.changeInputStreamToString(
								HttpUtil.getInputStream(path2), "utf-8");
						List<Person> persons = FastJsonTool.getList(data2, Person.class);
						Log.i(TAG, persons.toString());
					};
				}.start();
				break;
			case R.id.button333:
					new Thread(){
						public void run() {
							
							 String path3=
									 "http://122.207.36.133:8080/AndroidWebServer/login1?select=listString";
									 String
									 data3=HttpUtil.changeInputStreamToString(HttpUtil.getInputStream
									 (path3), "utf-8"); 
									 List<String> list=FastJsonTool.getList(data3, String.class);
									 Log.i(TAG, list.toString());
						};
					}.start();
				
				break;
			case R.id.button444:
				new Thread(){
					public void run() {
						
						 String path4=
								 "http://122.207.36.133:8080/AndroidWebServer/login1?select=listMap";
								 String
								 data4=HttpUtil.changeInputStreamToString(HttpUtil.getInputStream
								 (path4), "utf-8"); 
								 List<Map<String, Object>> maps=FastJsonTool.getListMap(data4);
								 Log.i(TAG, maps.toString());
					};
				}.start();
				break;

			}
		}
	}
}
