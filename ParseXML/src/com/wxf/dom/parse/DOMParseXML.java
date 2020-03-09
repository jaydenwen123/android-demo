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
//		第一步。获取DocumentBuilderFactory 工厂实例
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		try {
//		第二步，获取DocumentBuilder 对象实例
			DocumentBuilder builder=factory.newDocumentBuilder();
//			第三步，获取整颗dom树
			Document document=builder.parse(inputStream);
//			获取dom树中的根节点元素
			Element element=document.getDocumentElement();
//			根据标签的名称获取节点
			NodeList students=element.getElementsByTagName("student");
//			对该节点list进行遍历，
			for(int i=0;i<students.getLength();i++){
//				获取每一个student标签的对象
				Element e=(Element) students.item(i);
				student=new Student();
//				获取其属性进行对student对象赋值
				student.setId(Integer.parseInt(e.getAttribute("id")));
				student.setIsMarry(e.getAttribute("isMarry"));
//				获取student标签下的所有孩子节点。
				NodeList list2=e.getChildNodes();
//				然后对该孩子节点的list进行遍历
				for(int j=0;j<list2.getLength();j++){
					Node element2 =(Node) list2.item(j);
//					通过孩子节点的不同标签名称进行不同的赋值
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
