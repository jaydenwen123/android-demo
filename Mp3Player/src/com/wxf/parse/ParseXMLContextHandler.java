package com.wxf.parse;

import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.wxf.entity.MP3Info;

import android.R.integer;
import android.app.ListActivity;
import android.util.Log;

/**
 * @author Administrator
 *
 */
public class ParseXMLContextHandler extends DefaultHandler

{
	private List<MP3Info> infos=null;
	private MP3Info info=null;
	private String tagName=null;
	
	
	public List<MP3Info> getInfos() {
		return infos;
	}


	public void setInfos(List<MP3Info> infos) {
		this.infos = infos;
	}

	

	public ParseXMLContextHandler(List<MP3Info> infos) {
		super();
		this.infos = infos;
	}


	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startDocument()
	 * �ĵ���ʼ
	 */
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		Log.i("tag", "start document");
	}

	
	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#endDocument()
	 * ɨ�赽�ļ��Ľ���λ��
	 * 
	 */
	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		Log.i("tag", "end document");
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 * 
	 * ɨ��Ԫ�ؿ�ʼ
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		/*Log.i("tag", "localName="+localName);
		Log.i("tag", "qName="+qName);
		for(int i=0;i<attributes.getLength();i++){
			Log.i("tag", "attribute qname:"+attributes.getQName(i));
		}*/
		tagName=qName;
		if(tagName.equals("resource"))
		{
			info=new MP3Info();
		}
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 * ɨ��Ԫ�ؽ���
	 * 
	 */
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		/*Log.i("tag", "localName="+localName);
		Log.i("tag", "qName="+qName);*/
		tagName="";
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 * ɨ�赽Ԫ�ؽڵ������
	 */
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
//		Log.i("tag", new String(ch, start, length));
		String temp=new String(ch,start,length);
		if(tagName.equals("id")){
			info.setId(temp);
		}else if(tagName.equals("mp3.name")){
			info.setMp3Name(temp);
		}else if (tagName.equals("mp3.size")) {
			info.setMp3Size(temp);
		}else if(tagName.equals("lrc.name")){
			info.setLrcName(temp);
		}else if(tagName.equals("lrc.size")){
			info.setLrcSize(temp);
		}else if(tagName.equals("resource")){
				infos.add(info);
		}
	}

	
}
