package com.wxf.json;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.wxf.domain.Person;
import com.wxf.http.HttpUtil;
import com.wxf.service.JsonTool;

public class MainActivity extends Activity {

	private static final String TAG = "one";
	private Button button1, button2, button3, button4,button5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new ButtonListener());
		button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new ButtonListener());
		button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(new ButtonListener());
		button4 = (Button) findViewById(R.id.button4);
		button4.setOnClickListener(new ButtonListener());
		button5= (Button) findViewById(R.id.button5);
		button5.setOnClickListener(new ButtonListener());
	}

	class ButtonListener implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub

			switch (v.getId()) {
			case R.id.button1:
				new Thread() {
					public void run() {

						String path1 = "http://122.207.36.133:8080/AndroidWebServer/login?select=person";
						InputStream inputStream=HttpUtil.getInputStream(path1);
						String data1 = HttpUtil.changeInputStreamToString(
							inputStream	, "utf-8");
						Log.i(TAG, data1);
						Person person = JsonTool.getPerson("person", data1);
						Log.i(TAG, person.toString());
					};
				}.start();
				break;
			case R.id.button2:
				new Thread() {
					public void run() {
						String path2 = "http://122.207.36.133:8080/AndroidWebServer/login?select=persons";
						String data2 = HttpUtil.changeInputStreamToString(
								HttpUtil.getInputStream(path2), "utf-8");
						List<Person> persons = JsonTool.getPersons(
								"persons", data2);
						Log.i(TAG, persons.toString());
					};
				}.start();
				break;
			case R.id.button3:
					new Thread(){
						public void run() {
							
							 String path3=
									 "http://122.207.36.133:8080/AndroidWebServer/login?select=listString";
									 String
									 data3=HttpUtil.changeInputStreamToString(HttpUtil.getInputStream
									 (path3), "utf-8"); 
									 List<String> list=JsonTool.getListString("listString", data3);
									 Log.i(TAG, list.toString());
						};
					}.start();
				
				break;
			case R.id.button4:
				new Thread(){
					public void run() {
						
						 String path4=
								 "http://122.207.36.133:8080/AndroidWebServer/login?select=listMap";
								 String
								 data4=HttpUtil.changeInputStreamToString(HttpUtil.getInputStream
								 (path4), "utf-8"); 
								 List<Map<String, String>> maps=JsonTool.getListMap("listMap", data4);
								 Log.i(TAG, maps.toString());
					};
				}.start();
				break;
			case R.id.button5:
//				跳转到下一个页面。
				Intent intent=new Intent(MainActivity.this, SecondActivity.class);
				startActivity(intent);
				break;
				

			}
		}
	}
}
