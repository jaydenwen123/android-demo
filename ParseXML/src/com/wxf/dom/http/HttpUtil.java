package com.wxf.dom.http;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.Map;
import java.util.Map.Entry;

public class HttpUtil {
	
	public static final String URL="http://59.71.75.97:8080/AndroidWebServer/index.jsp";
	public static final int STATUECODE=200;
	
//	通过传入的url来从服务器获取一个输入流
	public static  InputStream getInputStream(String strUrl){
		InputStream inputStream=null;
			try {
				java.net.URL  url= new java.net.URL(strUrl);
				if(url!=null){
					HttpURLConnection connection=(HttpURLConnection) url.openConnection();
					if(connection!=null){
						connection.setDoInput(true);
//						connection.setDoOutput(true);
//						设置网络连接延时
						connection.setConnectTimeout(3000);
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
	
	public static void main(String[] args) {
		
		
		/*测试一
		 * InputStream inputStream=HttpUtil.getInputStream(HttpUtil.URL);
		String result=HttpUtil.getResult(inputStream);
		System.out.println(result);*/
		InputStream stream=HttpUtil.getInputStream(HttpUtil.URL);
		HttpUtil.outputToFile(stream,"D:\\index.jsp");
	}

}
