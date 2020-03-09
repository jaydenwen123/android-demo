package com.wxf;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.print.DocFlavor.INPUT_STREAM;

public class MyUtil {

	public ByteArrayOutputStream byteOutput;
	public DataOutputStream output;

	/**
	 * 发送数据
	 * 
	 * @param socket
	 * @param bytes
	 * @throws IOException
	 */
	public static void sendMessage(Socket socket, byte[] bytes)
			throws IOException {
		DataOutputStream dataOutputStream = new DataOutputStream(
				socket.getOutputStream());
		dataOutputStream.writeShort(bytes.length);
		dataOutputStream.write(bytes);
		dataOutputStream.flush();

	}

	/**
	 * 写数据到网络流
	 * 
	 * @param cmd
	 * @param messages
	 * @param move
	 * @param socket
	 */
	public void writesMessage(short len, short cmd, String messages,
			short move, Socket socket) {
		initOutput();
		try {
			output.writeShort(len);
			output.writeShort(cmd);
			output.writeChars(messages);
			output.writeShort(move);
			MyUtil.sendMessage(socket, byteOutput.toByteArray());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 初始化用到的流
	 */
	private void initOutput() {
		byteOutput = new ByteArrayOutputStream();
		output = new DataOutputStream(byteOutput);
	}

	/**
	 * 读数据
	 * 
	 * @param socket
	 * @throws IOException
	 */
	public static void readMessage(Socket socket) throws IOException {
		DataInputStream input = new DataInputStream(socket.getInputStream());
		short cmd = input.readShort();
		short len = input.readShort();
		byte[] bytes = new byte[len];
		input.read(bytes,0,len-4);
		String message = new String(bytes);
		System.out.println(message);
	}

}
