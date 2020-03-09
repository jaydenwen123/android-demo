package com.wxf.http;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

public class HttpUtil {
	
	public static final String URL="http://49.123.73.251:8080/AndroidWebServer/love.jpg";
	public static final int STATUECODE=200;
	
//	ͨ�������url���ӷ�������ȡһ��������
	public static  InputStream getInputStream(String strUrl){
		InputStream inputStream=null;
			try {
				java.net.URL  url= new java.net.URL(strUrl);
				if(url!=null){
					HttpURLConnection connection=(HttpURLConnection) url.openConnection();
					if(connection!=null){
						connection.setDoInput(true);
//						connection.setDoOutput(true);
//						��������������ʱ
						//connection.setConnectTimeout(3000);
						connection.setRequestMethod("GET");
						if(connection.getResponseCode()==STATUECODE){
							inputStream=connection.getInputStream();
						}
					}
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return inputStream;
	}
	
	
//	��һ��������ת����һ���ַ���(ʵ���Ե����ݻ����ȶ�ȡ������Ȼ����д��һ�����飬
	//���ת��Ϊ���ַ���)������ķ����õ��Ľ��һ����
	// ���еĲ���encoding�Ǳ���
	public static String changeInputStreamToString(InputStream inputStream,String encoding)
	{
		String result=null;
		ByteArrayOutputStream byteArrayOutputStream=null;
		try {
			byteArrayOutputStream=new ByteArrayOutputStream();
			int len=0;
			byte[] buffer=new byte[1024];
			while((len=inputStream.read(buffer))!=-1){
				byteArrayOutputStream.write(buffer, 0, len);
			}
			byteArrayOutputStream.flush();
			result=new String(byteArrayOutputStream.toByteArray(),encoding);
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally{
			try {
				byteArrayOutputStream.close();
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	/**
	 * @param inputStream
	 * @return
	 * �����ַ����Ľ��
	 */
	public static String getResult(InputStream inputStream){
		StringBuffer buffer=new StringBuffer();
		BufferedReader reader=null;
		try {
			reader=new BufferedReader(new InputStreamReader(inputStream));
			String line=null;
			while((line=reader.readLine())!=null){
				buffer.append(line+"\n");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally{
			try {
				reader.close();
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return buffer.toString();
	}
	
	public static void outputToFile(InputStream inputStream,String destPath){
		OutputStream outputStream=null;
		try {
			outputStream=new FileOutputStream(destPath);
			int len=0;
			byte[] buffer=new byte[1024];
			while((len=inputStream.read(buffer))!=-1){
				outputStream.write(buffer, 0, len);
				
			}
			outputStream.flush();
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			try {
				outputStream.close();
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
//	����һ��������������������������ͼƬ��
	public static byte[] getByteArrayByInputStream(InputStream inputStream) throws IOException
	{
		byte[] buffer=new byte[1024];
//		���������ֱ�ӽ��������е��ļ�д�뵽�ڴ���
		ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
		int len=0;
		while((len=inputStream.read(buffer))!=-1){
			byteArrayOutputStream.write(buffer,0, len);
		}
		byteArrayOutputStream.flush();
//		��������е�����תΪ�ֽ�����
		return byteArrayOutputStream.toByteArray();
	}
	
	
	
	public static void main(String[] args) {
		
		
		/*����һ
		 * InputStream inputStream=HttpUtil.getInputStream(HttpUtil.URL);
		String result=HttpUtil.getResult(inputStream);
		System.out.println(result);*/
		InputStream stream=HttpUtil.getInputStream(HttpUtil.URL);
		HttpUtil.outputToFile(stream,"D:\\index.jsp");
	}

}
