package com.wxf.pull.test;

import java.io.InputStream;
import java.util.List;

import com.wxf.pull.domain.Student;
import com.wxf.pull.http.HttpUtil;
import com.wxf.pull.parse.PullParseXML;
import com.wxf.sax.parse.SAXParseXML;

public class TestPull {

	public TestPull() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		String url="http://59.71.75.97:8080/AndroidWebServer/student.xml";
		InputStream inputStream=HttpUtil.getInputStream(url);
		List<Student> list=PullParseXML.PullParseXML(inputStream);
		System.out.println(list.size());
		for(Student s:list){
			System.out.println(s);
		}
	}
}
