package com.wxf.edittext03;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

import com.wxf.edittext01.R;

public class ThridActivity extends Activity {

//	�����������������ʱ�����Զ���ʾ��ص�����
	private AutoCompleteTextView autoCompleteTextView1;
	private MultiAutoCompleteTextView autoCompleteTextView2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thrid);
		autoCompleteTextView1=(AutoCompleteTextView) findViewById(R.id.auto);
		autoCompleteTextView2=(MultiAutoCompleteTextView) findViewById(R.id.multAuto);
		String[] array=new String[]{"google","goo","gooolee","gggg","���","�����","���˧"};
//		����������
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array);
		autoCompleteTextView1.setAdapter(adapter);
		autoCompleteTextView2.setAdapter(adapter);
//		���ڿ���ѡ������Ŀ����ʾ����Ҫ������һ�д��롢
		autoCompleteTextView2.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
	}
}
