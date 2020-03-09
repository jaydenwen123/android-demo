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
	 * @param key��ΪҪȡ�ü�
	 * @param jsonStr��Ϊjson�����ַ���
	 * @return
	 * ��ȡ����person����
	 * 
	 */
	public static Person getPerson(String key,String jsonStr){
		JSONObject jsonObject;
		Person person=null;
		try {
//			//			���ȹ���JSONObject ����
			jsonObject = new JSONObject(jsonStr);
			JSONObject jsonObject2=(JSONObject) jsonObject.get(key);
//			ͨ��������ȡjson����
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
	 * ��ȡlist���ϣ����ص���List��Person�����ϲ�������ͬ��
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
	 * ����List<String>���󣬲�������ͬ�ϡ�
	 */
	public static List<String> getListString(String key,String jsonStr){
		List<String> list=new ArrayList<String>();
		try {
//			���ȹ���JSONObject ����Ȼ��ͨ��������ȡJSONObject ����JSONArray��json���顣
			JSONObject jsonObject=new JSONObject(jsonStr);
			JSONArray jsonArray=jsonObject.getJSONArray(key);
//			����json���飬
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
//				���ȹ���JSONObject ����
			JSONObject jsonObject=new JSONObject(jsonStr);
//			��Ȼ��ͨ��������ȡJSONObject ����JSONArray��json���顣
			 JSONArray jsonArray=jsonObject.getJSONArray(key);
//				����json���飬
			 for(int i=0;i<jsonArray.length();i++){
//				 ��ȡ������JSONObject����
				 JSONObject jsonObject2=jsonArray.getJSONObject(i);
				 Iterator<String> iterator=jsonObject2.keys();
				 Map<String, String> map=new HashMap<String, String>();
//				 ͨ��������������
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
