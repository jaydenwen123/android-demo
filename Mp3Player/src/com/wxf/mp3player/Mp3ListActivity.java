package com.wxf.mp3player;

import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts.Data;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.wxf.download.DownloadUtil;
import com.wxf.entity.MP3Info;
public class Mp3ListActivity extends ListActivity {

	private static final int UPDATE=1;
	private static final int ABOUT=2;
	private static final String MP3LISTURL="http://59.71.77.231:8080/mp3/mp3.xml";
	private String data=null;
	
	private List<MP3Info> infos=new ArrayList<MP3Info>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mp3list);
		//System.out.println(infos.size());
		showList(infos);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(0, UPDATE, UPDATE,R.string.update);
		menu.add(1, ABOUT, ABOUT, R.string.about);
		return super.onCreateOptionsMenu(menu);
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if(item.getItemId()==UPDATE){
			/*
			 * ���û�ѡ��ø����б�˵�ʱ����Ҫ������µ�����
			 * 1.��tomcat����������ȡ�������ϵĸ����б�MP3.xml�ļ�
			 * 2.�Ը�xml�ļ����н�������÷�����������Щ������
			 * 3.Ȼ�󽫸�������Ϣչ����activity��
			 * 
			 * */
			// 1.��tomcat����������ȡ�������ϵĸ����б�MP3.xml�ļ�
			downLoadXMLThread thread=new downLoadXMLThread(data,MP3LISTURL);
			thread.start();
			infos=thread.getInfos();
			//2.�Ը�xml�ļ����н�������÷�����������Щ������
			//parseXMLMP3List(data);
			//3.Ȼ�󽫸�������Ϣչ����activity��
			showList(infos);
			
			
		}else if(item.getItemId()==ABOUT) {
			Toast.makeText(Mp3ListActivity.this, "��ѡ����ǹ��ڲ˵�", 
					Toast.LENGTH_SHORT).show();
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void  showList(List<MP3Info> infos){
		List<Map<String, String>> list=new ArrayList<Map<String,String>>();
		infos.add(new MP3Info("0001", "111", "23423", "111", "12312"));
		infos.add(new MP3Info("0002", "222", "23423", "222", "12312"));
		Map<String, String> map=null;
		for (Iterator iterator = infos.iterator(); iterator.hasNext();) {
			MP3Info info = (MP3Info) iterator.next();
			map=new HashMap<String, String>();
			map.put("musicName", info.getMp3Name());
			map.put("musicSize", info.getMp3Size());
			list.add(map);
		}
		SimpleAdapter simpleAdapter=new SimpleAdapter(Mp3ListActivity.this, list,
				R.layout.musiclist, new String[]{"musicName","musicSize"}, new int[]{R.id.musicName,
				R.id.musicSize});
		setListAdapter(simpleAdapter);
	}
	
	
}

//�������ļ����뵽�߳������
class downLoadXMLThread extends Thread{
	private String data;
	private List<MP3Info> infos;
	private String url;
	
	public downLoadXMLThread() {
		// TODO Auto-generated constructor stub
		infos=new ArrayList<MP3Info>();
	}
	public downLoadXMLThread(String data, List<MP3Info> infos, String url) {
		super();
		this.data = data;
		this.infos = infos;
		this.url = url;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public downLoadXMLThread(String data, List<MP3Info> infos) {
		this();
		this.data = data;
		this.infos = infos;
	}

	
	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}


	public List<MP3Info> getInfos() {
		return infos;
	}


	public void setInfos(List<MP3Info> infos) {
		this.infos = infos;
	}


	public downLoadXMLThread(String data, String url) {
		super();
		this.data = data;
		this.url = url;
	}
	@Override
	public void run() {
		data = getMP3XMLList(url);
		infos=parseXMLMP3List(data);
		//showList();
		System.out.println("123333==========="+infos.size());
	}
	
	/**
	 * @return
	 * �ӷ��������صõ������б�
	 * 
	 */
	private String getMP3XMLList(String url) {
		//			�������صĹ����ࡣ��ʵ�������ļ�
					DownloadUtil downloadUtil=new DownloadUtil();
		//			�������ʹ�ñ�����ip��������Դ
					String result=downloadUtil.downloadText(url);
		return result;
	}
	/**
	 * @param data
	 * ͨ��sax��ʽ������xml�ļ�
	 * 
	 */
	public List<MP3Info> parseXMLMP3List(String data) {
		try {
			//����һ��SAX����
			SAXParserFactory  factory=SAXParserFactory.newInstance();
			SAXParser parser=factory.newSAXParser();
			XMLReader reader=parser.getXMLReader();
			reader.setContentHandler(new com.wxf.parse.ParseXMLContextHandler(infos));
			reader.parse(new InputSource(new StringReader(data)));
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
		 return infos;
		
	}
	
}


