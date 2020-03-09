package com.wxf.sax.handler;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.wxf.sax.domain.Student;


public class SAXXMLContextHandler extends DefaultHandler {

	private List<Student> list=new ArrayList<Student>();
	private Student student=null; 
	private String tagName=null;
	


	@Override
	public void startDocument() throws SAXException {
//		System.out.println("the document is started  ");
	}

	public List<Student> getList() {
		return list;
	}

	public void setList(List<Student> list) {
		this.list = list;
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
//		System.out.println("the document is ended");
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
//		System.out.println("the element is started to parse  ----localName:"+localName+"qName:"+qName);
		tagName=qName;
		if(qName.equals("student")){
			student=new Student();
			for(int i=0;i<attributes.getLength();i++){
				student.setIsMarry(attributes.getValue(1));
				student.setId(Integer.parseInt(attributes.getValue(0)));
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
//		System.out.println("the element is ended parse");
		tagName="";
		if(qName.equals("student")){
			list.add(student);
			student=null;
		}
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 * 
	 * 该函数在不仅仅在<and>123</and>and中间执行一次，
	 * 而且在and结束扫描之后还要执行一次，它吧其后的空格也当做值。切记
	 */
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		String temp=new String(ch, start, length);
//		System.out.println("this is the characters --------->value: "+temp);
		if(tagName.equals("name")){
			student.setName(temp);
		}else if(tagName.equals("age")) {
			student.setAge(Integer.parseInt(temp));
		}else if(tagName.equals("sex")){
			student.setSex(temp);
		}
		
	}

}
