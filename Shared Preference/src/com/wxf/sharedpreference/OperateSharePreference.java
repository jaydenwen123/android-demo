package com.wxf.sharedpreference;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class OperateSharePreference {

	private Context context;

	public OperateSharePreference() {
		// TODO Auto-generated constructor stub
	}
	public OperateSharePreference(Context context) {
		this.context = context;
	}
	


	/**
	 * @param map
	 * @return
	 * ��sharePreference��д�����ݣ����д���Ĳ���ΪҪд�������
	 */
	public boolean writeDataToSharePreference(Map<String, String> map) {
		boolean flag = false;
//		ͨ��Ӧ������������ȡsharePreference���󡣣������еĵ�һ������Ϊ���ơ��ڶ���Ϊmode
		SharedPreferences sharedPreferences = context
				.getSharedPreferences("share", Context.MODE_PRIVATE);
//		��ȡEditor����Ȼ��ͨ���ö�����д洢���ݡ�
		Editor editor = sharedPreferences.edit();
		try {
			for(Entry<String, String> entry:map.entrySet()){
				editor.putString(entry.getKey(), entry.getValue());
			}
//			�������ύ��
			editor.commit();
			flag=true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	public Map<String, String> readDataFromSharePreference(String[] keys){
		Map<String, String> map=new HashMap<String, String>();
//		��ȡSharedPreferences����
		SharedPreferences sharedPreferences = context
				.getSharedPreferences("share", Context.MODE_PRIVATE);
		for(String key:keys){
//			��ȡֵ���ڶ�������Ϊ���ȡ������ֵ�������Ĭ��ֵ��
			String value=sharedPreferences.getString(key,"");
			map.put(key, value);
		}
		return map;
	}
}
