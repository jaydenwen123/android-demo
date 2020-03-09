package com.wxf.operate;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.R.bool;
import android.content.Context;
import android.os.Environment;

public class OperateFile {
	private Context context;

	public OperateFile(Context context) {
		super();
		this.context = context;
	}
	public OperateFile() {
		// TODO Auto-generated constructor stub
	}

	public  boolean writeContextToFile(String fileName, String content) {
		boolean flag = false;
		FileOutputStream fileOutputStream = null;
		File file = new File(Environment.getExternalStorageDirectory(),
				fileName);
		
//		Environment.getExternalStorageState()��ȡ�ⲿ�洢��״̬
//		Environment.MEDIA_MOUNTED
//		����ⲿ�洢���Ƿ���ã��ɶ���д
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			try {
				fileOutputStream = new FileOutputStream(file);
				fileOutputStream.write(content.getBytes());
				fileOutputStream.flush();
				flag = true;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (fileOutputStream != null) {
					try {
						fileOutputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return flag;

	}
	
	public boolean readContextFromFile(String fileName){
		boolean flag=false;
//		����ⲿ�洢���Ƿ���ã��ɶ���д��
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			File file=new File(Environment.getExternalStorageDirectory(),fileName);
			if(!file.exists()){
				System.out.println("the file is not existed");
				return flag;
			}
			InputStream inputStream=null;
			// ����������ʹ����޹أ�����Ҫ�ر�
			ByteArrayOutputStream outputStream=null;
			try {
				inputStream=new FileInputStream(file);
				outputStream=new ByteArrayOutputStream();
				byte [] buffer=new byte[1024];
				int len=0;
				int offset=0;
				while((len=inputStream.read(buffer))!=-1){
					outputStream.write(buffer, offset, len);
				}
				outputStream.flush();
				System.out.println("the result"+new String(outputStream.toByteArray()));
				flag=true;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(inputStream!=null){
					try {
						inputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return flag;
	}
}
