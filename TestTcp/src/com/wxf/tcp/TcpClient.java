package com.wxf.tcp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.wxf.MyUtil;

/**
 * 
 * tcp¿Í»§¶Ë
 * 
 * @author Administrator
 * 
 */
public class TcpClient {

	MyUtil myUtil = new MyUtil();

	public void request() throws UnknownHostException, IOException {

		// "49.123.109.37"
		Socket socket = new Socket("127.0.0.1", 4321);
		InputStream is = new FileInputStream("D:\\data.txt");
		int len = 0;
		byte[] buffer = new byte[1024];
		// String message =
		// "hello world ,you are fine to how konw djfdjfdfn dfsdf oijkj dfei cefd ncondfe mdsfj ";
		String message = "";
		while ((len = is.read(buffer)) != -1) {
			message += new String(buffer, 0, len);
		}
		myUtil.writesMessage((short) 512, (short) 0x6174, message,
				(short) 0x6564, socket);
		// InputStream is = socket.getInputStream();
		// byte[] buf = new byte[1024];
		// int len =0;
		// while((len=is.read(buf))!=-1){
		// System.out.println(new String(buf,0,len));
		// }
		// System.out.println("end");
		// System.out.println("end");
		socket.close();
	}

	public static void main(String[] args) {

		TcpClient client = new TcpClient();
		try {
			client.request();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
