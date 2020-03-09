package com.wxf.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.print.DocFlavor.INPUT_STREAM;

import com.wxf.MyUtil;

/**
 * tcp服务器
 * 
 * @author Administrator
 * 
 */
public class TcpServer {

	public TcpServer() {
		// TODO Auto-generated constructor stub
	}

	MyUtil myUtil = new MyUtil();

	public void buildConnection() throws IOException {
		// 创建服务器端的套接字
		ServerSocket serverSocket = new ServerSocket(4321);
		// 服务器端进行监听
		Socket socket = serverSocket.accept();
		BufferedInputStream is = new BufferedInputStream(
				socket.getInputStream());
		BufferedOutputStream os = new BufferedOutputStream(
				socket.getOutputStream());

		System.out.println(socket.getLocalAddress().getHostName() + ","
				+ socket.getInetAddress() + "," + socket.getPort());
		System.out.println(socket.getReceiveBufferSize());
		System.out.println("*********************");
		// 获取输入流和输出流
		// int len = 0;
		// int i = 0;
		// while (is.available() > 0) {
		// System.out.println(is.available());
		// byte[] buffer = new byte[is.available()];
		// System.out.println("***");
		// len = is.read(buffer);
		// System.out.println(new String(buffer, 0, len));
		// i++;
		// }
		// System.out.println(i);

		myUtil.readMessage(socket);
		// while ((len = is.read(buffer)) != -1) {
		// System.out.println(len);
		// System.out.println(new String(buffer, 0, len));
		// }
		// String data = "hello you too,i am a server!!!";
		// os.write(data.getBytes());
		socket.close();

	}

	public static void main(String[] args) {

		TcpServer server = new TcpServer();
		System.out.println("start to listen!!!");
		try {
			server.buildConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
