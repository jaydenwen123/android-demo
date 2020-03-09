package com.wxf.json;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wxf.domain.Person;
import com.wxf.http.HttpUtil;
import com.wxf.json.MainActivity.ButtonListener;
import com.wxf.service.GsonTool;
import com.wxf.service.JsonTool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


/**
 * @author Administrator
 *做json数据解析时。必须采用对应的格式来解析，即json 对应json gson对应gson格式
 */
public class SecondActivity extends Activity {

	private static final String TAG = "second";
	private Button button11, button22, button33, button44,button55;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		button11 = (Button) findViewById(R.id.button11);
		button11.setOnClickListener(new ButtonListener());
		button22 = (Button) findViewById(R.id.button22);
		button22.setOnClickListener(new ButtonListener());
		button33 = (Button) findViewById(R.id.button33);
		button33.setOnClickListener(new ButtonListener());
		button44 = (Button) findViewById(R.id.button44);
		button44.setOnClickListener(new ButtonListener());
		button55= (Button) findViewById(R.id.button55);
		button55.setOnClickListener(new ButtonListener());
	}

	class ButtonListener implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub

			switch (v.getId()) {
			case R.id.button11:
				new Thread() {
					public void run() {

						String path1 = "http://122.207.36.133:8080/AndroidWebServer/login1?select=person";
						InputStream inputStream=HttpUtil.getInputStream(path1);
						String data1 = HttpUtil.changeInputStreamToString(
							inputStream	, "utf-8");
						Log.i(TAG, data1);
						Person person = GsonTool.getObject(data1, Person.class);
						Log.i(TAG, person.toString());
					};
				}.start();
				break;
			case R.id.button22:
				new Thread() {
					public void run() {
						String path2 = "http://122.207.36.133:8080/AndroidWebServer/login1?select=persons";
						String data2 = HttpUtil.changeInputStreamToString(
								HttpUtil.getInputStream(path2), "utf-8");
						List<Person> persons = GsonTool.getList(data2);
						Log.i(TAG, persons.toString());
					};
				}.start();
				break;
			case R.id.button33:
					new Thread(){
						public void run() {
							
							 String path3=
									 "http://122.207.36.133:8080/AndroidWebServer/login1?select=listString";
									 String
									 data3=HttpUtil.changeInputStreamToString(HttpUtil.getInputStream
									 (path3), "utf-8"); 
									 List<String> list=GsonTool.getList(data3);
									 Log.i(TAG, list.toString());
						};
					}.start();
				
				break;
			case R.id.button44:
				new Thread(){
					public void run() {
						
						 String path4=
								 "http://122.207.36.133:8080/AndroidWebServer/login1?select=listMap";
								 String
								 data4=HttpUtil.changeInputStreamToString(HttpUtil.getInputStream
								 (path4), "utf-8"); 
								 List<Map<String, Object>> maps=GsonTool.getListMap(data4);
								 Log.i(TAG, maps.toString());
					};
				}.start();
				break;
			case R.id.button55:
//				跳转到下一个页面。
				Intent intent=new Intent(SecondActivity.this, ThridActivity.class);
				startActivity(intent);
				break;
				

			}
		}
	}
}
