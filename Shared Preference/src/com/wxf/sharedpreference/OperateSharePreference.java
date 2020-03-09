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
	 * 给sharePreference中写入数据，其中传入的参数为要写入的数据
	 */
	public boolean writeDataToSharePreference(Map<String, String> map) {
		boolean flag = false;
//		通过应用上下文来获取sharePreference对象。，方法中的第一个参数为名称。第二个为mode
		SharedPreferences sharedPreferences = context
				.getSharedPreferences("share", Context.MODE_PRIVATE);
//		获取Editor对象，然后通过该对象进行存储数据。
		Editor editor = sharedPreferences.edit();
		try {
			for(Entry<String, String> entry:map.entrySet()){
				editor.putString(entry.getKey(), entry.getValue());
			}
//			最后进行提交。
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
//		获取SharedPreferences对象。
		SharedPreferences sharedPreferences = context
				.getSharedPreferences("share", Context.MODE_PRIVATE);
		for(String key:keys){
//			获取值。第二个参数为如果取不到该值，则传入的默认值。
			String value=sharedPreferences.getString(key,"");
			map.put(key, value);
		}
		return map;
	}
}
