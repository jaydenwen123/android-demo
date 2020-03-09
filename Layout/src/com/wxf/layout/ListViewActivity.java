package com.wxf.layout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ListViewActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview);
		ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("u_name", "wenxiaofei");
		map1.put("u_ip", "192.78.23.01");
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("u_name", "wangshuming");
		map2.put("u_ip", "192.78.23.02");
		Map<String, String> map3 = new HashMap<String, String>();
		map3.put("u_name", "caohongying");
		map3.put("u_ip", "192.78.23.03");
		list.add(map1);
		list.add(map2);
		list.add(map3);
		/*
		 * android.widget.SimpleAdapter.SimpleAdapter(Context context, List<?
		 * extends Map<String, ?>> data, int resource, String[] from, int[] to)
		 * �ú����Ĳ���˵�����£�
		 * 1.context��ָ��ǰ��activity
		 * 2.list�����е�data��ָ��Ҫ��listView����ʾ������
		 * 3.int resource ��ָ�������Ժ��ֲ�������ʾ������������ǲ��ֵ���Դ��
		 * 4.string[] from ��ָlist�ж�Ӧ��Map�ļ�ֵ��
		 * 5.��ָ�ԵĲ����ļ���Ҫ��ʾ�ĵĸ����ռ��id��
		 */
		ListAdapter simpleAdapter = new SimpleAdapter(this, list,
				R.layout.u_user, new String[] { "u_name", "u_ip" }, new int[] {
						R.id.u_name, R.id.u_ip });
		setListAdapter(simpleAdapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		System.out.println("listView  position:---" + position);
		System.out.println("listView  view:---" + v);
		System.out.println("listView  listView:" + l);
	}

}
