package com.wxf.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.util.Log;

public class JavaScriptInterface {

	public static  String PIN; 
	/**
	 * @param data
	 * 该方法用来从授权的网页中获取对应的授权码：为六位
	 */
	public  void getPin(String data){
		Log.i("webView", "----------data:"+data);
//		通过正则表达式来匹配
		String regex="[0-9]{6}";
		Pattern pattern=Pattern.compile(regex);
		Matcher matcher=pattern.matcher(data);
		if(matcher.find()){
			PIN=matcher.group(0);
		}
		Log.i("webView", "----------pin:"+PIN);
	}

}
