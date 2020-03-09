package com.wxf.json;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

public class FastJsonTool {

	public FastJsonTool() {
		// TODO Auto-generated constructor stub
	}
	
	public static <T> T getObject(String jsonStr,Class<T> clazz){
		T t=null;
		try {
			t=JSON.parseObject(jsonStr,clazz);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}
	
	public static <T> List<T> getList(String jsonStr,Class<T> clazz){
		List<T> list=null;
		try {
			list=JSON.parseArray(jsonStr, clazz);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	public static <T> List<Map<String, Object>> getListMap(String jsonStr){
		List<Map<String, Object>> maps=null;
		try {
			maps=JSON.parseObject(jsonStr, new TypeReference<List<Map<String, Object>>>(){});
		} catch (Exception e) {
			// TODO: handle exception
		}
		return maps;
	}
}
