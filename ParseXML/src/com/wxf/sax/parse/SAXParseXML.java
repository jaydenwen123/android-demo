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
//		第一步构建工厂
		SAXParserFactory factory=SAXParserFactory.newInstance();
		try {
//			第二步，构建SAXParser对象
			SAXParser parser=factory.newSAXParser();
			SAXXMLContextHandler contextHandler=new SAXXMLContextHandler();
//			对parser对象设置要解析的文档，此处可以使用两种方式
//			第一中方式，是传入一个输入流和一个自定义的contextHandler对象
			parser.parse(inputStream, contextHandler);
//			第二种方式是传入一个Inputsource 对象，然后传入要解析的xml文件的内容，将其转化文字符串写入。第二
//			参数依旧是自定义的ContextHandler对象。
//			parser.parse(new InputSource(new StringReader("要解析的文档内容")), contextHandler);
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
