package com.wxf.service;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class GsonTool {

	
	/**
	 * @param jsonStr
	 * @param clazz
	 * @return
	 * ͨ��gson��ȡ�����������÷���ķ�ʽ
	 * 
	 */
	public static <T> T getObject(String jsonStr,Class<T> clazz){
		T t=null;
		try {
//			����Gson����
			Gson gson=new Gson();
//			����java������ƣ����ַ���ת��Ϊ��Ӧ����
			t=gson.fromJson(jsonStr, clazz);
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}
//	��ȡlist���϶���
	public static <T> List<T> getList(String jsonStr){
		List<T> list=null;
		try {
			Gson gson=new Gson();
//		��ȡ���ϵķ�����
			list=gson.fromJson(jsonStr, new TypeToken<List<T>>(){}.getType());
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
//	��ȡlist��Map�ļ���Ƕ�׶���
	public static <T> List<Map<String, Object>> getListMap(String jsonStr){
		List<Map<String, Object>> list=null;
		try {
			Gson gson=new Gson();
			list=gson.fromJson(jsonStr, new TypeToken<List<Map<String, Object>>>(){}.getType());
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
