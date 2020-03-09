package com.wxf.menu;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ListView listView;
	private List<String> list = null;
	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ListView) findViewById(R.id.listView);
		list = new ArrayList<String>();
		for (int i = 0; i < 6; i++) {
			list.add("wen" + i);
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				MainActivity.this, android.R.layout.simple_list_item_1, list);
		listView.setAdapter(adapter);
//		��listView����ע�ḡ���˵���
		registerForContextMenu(listView);
		button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(MainActivity.this, SecondActivity.class);
				startActivity(intent);
			}
		});
	}

	/**
	 * 
	 * ���������˵���
	 * �������ͨ�����ַ������������˵���
	 * 1.����ͨ��menu.xml
	 * �ļ������ز˵���
	 * 2.����ͨ��java�����������˵���
	 */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.first, menu);
	}

	/**
	 * 
	 *���両���˵��Ĳ˵�����д���ͨ��id���Ƚϣ�Ȼ��ʹ��switch�����з�֮����
	 */
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.open:
			show("open");
			break;
		case R.id.save:
			show("save");
			break;
		case R.id.close:
			show("close");
			break;

		}
		return super.onContextItemSelected(item);
	}

	/**
	 * 
	 * �ڴ����˵�ʱ��̬�ļ��ز����ļ���
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.first, menu);
		return true;
	}

	/**
	 * 
	 * ͨ��switch�����ʵ�ֶԲҵ���ͬѡ��Ĳ�����
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.open:
			show("open");
			break;
		case R.id.save:
			show("save");
			break;
		case R.id.close:
			show("close");
			break;

		}
		return true;
	}

	public void show(CharSequence msg) {
		Toast.makeText(MainActivity.this, msg, 1).show();
	}
}
