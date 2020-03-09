package com.wxf.sax.test;

import java.io.InputStream;
import java.util.List;

import com.wxf.dom.http.HttpUtil;
import com.wxf.sax.domain.Student;
import com.wxf.sax.handler.SAXXMLContextHandler;
import com.wxf.sax.parse.SAXParseXML;

public class TestSAX {

	
	public TestSAX() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		String url="http://59.71.75.97:8080/AndroidWebServer/student.xml";
		InputStream inputStream=HttpUtil.getInputStream(url);
		List<Student> list=SAXParseXML.ParseSAXXML(inputStream);
		System.out.println(list.size());
		for(Student s:list){
			System.out.println(s);
		}
		
	}
}
