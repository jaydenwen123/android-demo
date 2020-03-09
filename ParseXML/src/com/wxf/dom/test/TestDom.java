package com.wxf.dom.test;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import com.wxf.dom.domain.Student;
import com.wxf.dom.parse.DOMParseXML;
import com.wxf.sax.http.HttpUtil;

public class TestDom {

	public TestDom() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		String url="http://59.71.75.97:8080/AndroidWebServer/student.xml";
		InputStream inputStream=HttpUtil.getInputStream(url);
		List<Student> list=DOMParseXML.parseXML(inputStream);
		
		/*for(Student s:list){
			System.out.println(s);
		}*/
	}
}
