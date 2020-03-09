package com.wxf.edittext03;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

import com.wxf.edittext01.R;

public class ThridActivity extends Activity {

//	挡在输入框输入内容时。会自动提示相关的内容
	private AutoCompleteTextView autoCompleteTextView1;
	private MultiAutoCompleteTextView autoCompleteTextView2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thrid);
		autoCompleteTextView1=(AutoCompleteTextView) findViewById(R.id.auto);
		autoCompleteTextView2=(MultiAutoCompleteTextView) findViewById(R.id.multAuto);
		String[] array=new String[]{"google","goo","gooolee","gggg","你好","你好吗","你好帅"};
//		设置适配器
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array);
		autoCompleteTextView1.setAdapter(adapter);
		autoCompleteTextView2.setAdapter(adapter);
//		对于可以选择多个条目的提示框。需要加入下一行代码、
		autoCompleteTextView2.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
	}
}
