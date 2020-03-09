package com.wxf.pull.parse;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.wxf.pull.domain.Student;

public class PullParseXML {

	public static List<Student> PullParseXML(InputStream inputStream) {
		List<Student> list = null;
		Student student=null;
		try {
			XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
			XmlPullParser parser=factory.newPullParser();
			parser.setInput(inputStream, "utf-8");
			// 获得事件的类型
			int eventType = parser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
							case XmlPullParser.START_DOCUMENT:
								list = new ArrayList<Student>();
								break;
							case XmlPullParser.START_TAG:
								if ("student".equals(parser.getName())) {
									student = new Student();
									// 取出属性值
									int id = Integer.parseInt(parser.getAttributeValue(0));
									student.setId(id);
									student.setIsMarry(parser.getAttributeValue(1));
								} else if ("name".equals(parser.getName())) {
									String name = parser.nextText();// 获取该节点的内容
									student.setName(name);
								} else if ("age".equals(parser.getName())) {
									int age = Integer.parseInt(parser.nextText());
									student.setAge(age);
								}
								break;
							case XmlPullParser.END_TAG:
								if ("student".equals(parser.getName())) {
									list.add(student);
									student = null;
								}
								break;
				}
				eventType = parser.next();
			}
	}catch (Exception e){
		
	}
		return list;
	}
}
