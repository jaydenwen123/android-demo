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
	
	
//	将一个输入流转换成一个字符串(实质性的内容还是先读取进来。然后再写入一个数组，
	//最后转换为以字符串)与下面的方法得到的结果一样。
	// 其中的参数encoding是编码
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
	 * 返回字符串的结果
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
	
//	构建一个方法，可以用来从网上下载图片。
	public static byte[] getByteArrayByInputStream(InputStream inputStream) throws IOException
	{
		byte[] buffer=new byte[1024];
//		该输出流是直接将输入流中的文件写入到内存中
		ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
		int len=0;
		while((len=inputStream.read(buffer))!=-1){
			byteArrayOutputStream.write(buffer,0, len);
		}
		byteArrayOutputStream.flush();
//		将输出流中的内容转为字节数组
		return byteArrayOutputStream.toByteArray();
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
