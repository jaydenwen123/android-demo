package com.wxf.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.wxf.domain.Person;

public class JsonTool {

	/**
	 * @param key，为要取得键
	 * @param jsonStr，为json数据字符串
	 * @return
	 * 获取单个person对象
	 * 
	 */
	public static Person getPerson(String key,String jsonStr){
		JSONObject jsonObject;
		Person person=null;
		try {
//			//			首先构建JSONObject 对象，
			jsonObject = new JSONObject(jsonStr);
			JSONObject jsonObject2=(JSONObject) jsonObject.get(key);
//			通过键来获取json对象。
			person=new Person();
			person.setId(jsonObject2.getInt("id"));
			person.setPassword(jsonObject2.getString("password"));
			person.setUsername(jsonObject2.getString("username"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return person;
	}
	
	/**
	 * @param key
	 * @param jsonStr
	 * @return
	 * 获取list集合，返回的是List《Person》集合参数介绍同上
	 */
	public static List<Person> getPersons(String key,String jsonStr){
		List<Person> persons=new ArrayList<Person>();
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(jsonStr);
			JSONArray jsonObject2=(JSONArray) jsonObject.get(key);
			for(int i=0;i<jsonObject2.length();i++){
				JSONObject jsonObject3=(JSONObject) jsonObject2.get(i);
				Person person=new Person();
				person.setId(jsonObject3.getInt("id"));
				person.setPassword(jsonObject3.getString("password"));
				person.setUsername((String) jsonObject3.get("username"));
				persons.add(person);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return persons;
	}
	
	
	/**
	 * @param key
	 * @param jsonStr
	 * @return
	 * 返回List<String>对象，参数介绍同上。
	 */
	public static List<String> getListString(String key,String jsonStr){
		List<String> list=new ArrayList<String>();
		try {
//			首先构建JSONObject 对象，然后通过键来获取JSONObject 对象JSONArray，json数组。
			JSONObject jsonObject=new JSONObject(jsonStr);
			JSONArray jsonArray=jsonObject.getJSONArray(key);
//			遍历json数组，
			for(int i=0;i<jsonArray.length();i++){
				list.add(jsonArray.getString(i));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Map<String,String>> getListMap(String key,String jsonStr){
		List<Map<String,String>> list =new ArrayList<Map<String,String>>();
		 try {
//				首先构建JSONObject 对象
			JSONObject jsonObject=new JSONObject(jsonStr);
//			，然后通过键来获取JSONObject 对象JSONArray，json数组。
			 JSONArray jsonArray=jsonObject.getJSONArray(key);
//				遍历json数组，
			 for(int i=0;i<jsonArray.length();i++){
//				 在取出单个JSONObject对象。
				 JSONObject jsonObject2=jsonArray.getJSONObject(i);
				 Iterator<String> iterator=jsonObject2.keys();
				 Map<String, String> map=new HashMap<String, String>();
//				 通过迭代器来便利
				while(iterator.hasNext()){
					String objKey=iterator.next();
					String objValue=(String) jsonObject2.get(objKey);
					map.put(objKey, objValue);
				}
				list.add(map);
			 }
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
