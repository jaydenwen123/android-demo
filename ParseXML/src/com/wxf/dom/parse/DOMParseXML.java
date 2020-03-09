package com.wxf.dom.parse;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.wxf.dom.domain.Student;

public class DOMParseXML {

	public static List<Student> parseXML(InputStream inputStream){
		List<Student> list=new ArrayList<Student>();
		Student student=null;
//		��һ������ȡDocumentBuilderFactory ����ʵ��
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		try {
//		�ڶ�������ȡDocumentBuilder ����ʵ��
			DocumentBuilder builder=factory.newDocumentBuilder();
//			����������ȡ����dom��
			Document document=builder.parse(inputStream);
//			��ȡdom���еĸ��ڵ�Ԫ��
			Element element=document.getDocumentElement();
//			���ݱ�ǩ�����ƻ�ȡ�ڵ�
			NodeList students=element.getElementsByTagName("student");
//			�Ըýڵ�list���б�����
			for(int i=0;i<students.getLength();i++){
//				��ȡÿһ��student��ǩ�Ķ���
				Element e=(Element) students.item(i);
				student=new Student();
//				��ȡ�����Խ��ж�student����ֵ
				student.setId(Integer.parseInt(e.getAttribute("id")));
				student.setIsMarry(e.getAttribute("isMarry"));
//				��ȡstudent��ǩ�µ����к��ӽڵ㡣
				NodeList list2=e.getChildNodes();
//				Ȼ��Ըú��ӽڵ��list���б���
				for(int j=0;j<list2.getLength();j++){
					Node element2 =(Node) list2.item(j);
//					ͨ�����ӽڵ�Ĳ�ͬ��ǩ���ƽ��в�ͬ�ĸ�ֵ
					if(element2.getNodeName().equals("name")){
						student.setName(element2.getFirstChild().getNodeValue());
					}else if(element2.getNodeName().equals("age")){
						student.setAge(Integer.parseInt(element2.getFirstChild().getNodeValue()));
					}else if(element2.getNodeName().equals("sex")){
						student.setSex(element2.getFirstChild().getNodeValue());
					}
				}
				list.add(student);
				student=null;
			}
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
		
	}
}
