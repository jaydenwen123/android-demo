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
	 * 通过gson获取单个对象。利用反射的方式
	 * 
	 */
	public static <T> T getObject(String jsonStr,Class<T> clazz){
		T t=null;
		try {
//			构建Gson对象。
			Gson gson=new Gson();
//			利用java反射机制，将字符串转换为对应的类
			t=gson.fromJson(jsonStr, clazz);
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}
//	获取list集合对象
	public static <T> List<T> getList(String jsonStr){
		List<T> list=null;
		try {
			Gson gson=new Gson();
//		获取集合的方法。
			list=gson.fromJson(jsonStr, new TypeToken<List<T>>(){}.getType());
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
//	获取list和Map的集合嵌套对象。
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
