package com.wxf.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.util.Log;

public class JavaScriptInterface {

	public static  String PIN; 
	/**
	 * @param data
	 * �÷�����������Ȩ����ҳ�л�ȡ��Ӧ����Ȩ�룺Ϊ��λ
	 */
	public  void getPin(String data){
		Log.i("webView", "----------data:"+data);
//		ͨ��������ʽ��ƥ��
		String regex="[0-9]{6}";
		Pattern pattern=Pattern.compile(regex);
		Matcher matcher=pattern.matcher(data);
		if(matcher.find()){
			PIN=matcher.group(0);
		}
		Log.i("webView", "----------pin:"+PIN);
	}

}
