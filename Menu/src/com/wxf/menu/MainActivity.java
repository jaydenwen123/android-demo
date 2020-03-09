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
//		给listView进行注册浮动菜单。
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
	 * 创建浮动菜单。
	 * 这里可以通过两种方法，来创建菜单。
	 * 1.可以通过menu.xml
	 * 文件来加载菜单。
	 * 2.可以通过java代码来创建菜单。
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
	 *对其浮动菜单的菜单项进行处理。通过id来比较，然后使用switch语句进行分之处理。
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
	 * 在创建菜单时动态的加载布局文件。
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
	 * 通过switch语句来实现对惨淡不同选项的操作。
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
