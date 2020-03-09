package com.wxf.sax.parse;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.wxf.sax.domain.Student;
import com.wxf.sax.handler.SAXXMLContextHandler;
public class SAXParseXML {
	
	
	
	public static List<Student> ParseSAXXML(InputStream inputStream){
		List<Student> list=null;
//		��һ����������
		SAXParserFactory factory=SAXParserFactory.newInstance();
		try {
//			�ڶ���������SAXParser����
			SAXParser parser=factory.newSAXParser();
			SAXXMLContextHandler contextHandler=new SAXXMLContextHandler();
//			��parser��������Ҫ�������ĵ����˴�����ʹ�����ַ�ʽ
//			��һ�з�ʽ���Ǵ���һ����������һ���Զ����contextHandler����
			parser.parse(inputStream, contextHandler);
//			�ڶ��ַ�ʽ�Ǵ���һ��Inputsource ����Ȼ����Ҫ������xml�ļ������ݣ�����ת�����ַ���д�롣�ڶ�
//			�����������Զ����ContextHandler����
//			parser.parse(new InputSource(new StringReader("Ҫ�������ĵ�����")), contextHandler);
			list=contextHandler.getList();
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
