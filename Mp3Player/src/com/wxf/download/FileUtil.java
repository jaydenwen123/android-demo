package com.wxf.download;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.R.string;
import android.os.Environment;

public class FileUtil {

//	����sd����Ŀ¼
	private   String SDPATH=null;
	
	public  String getSDPATH() {
		return SDPATH;
	}

	public  void setSDPATH(String sDPATH) {
		SDPATH = sDPATH;
	}

	public FileUtil() {
		// TODO Auto-generated constructor stub
		SDPATH=Environment.getExternalStorageDirectory()+"/";
	}
//	�����µ��ļ�
	public  File createFile(String filePath){
		File file=new File(SDPATH+filePath);
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file;
	}
//	����Ŀ¼
	public File createDirectory(String destPath){
		File file=new File(SDPATH+destPath);
		file.mkdirs();
		return file;
	}
//	�ж��ļ��Ƿ����
	public boolean fileIsExist(String filePath){
		File file=new File(SDPATH+filePath);
		return file.exists();
	}
//	��inputstream�е����ݣ�д�뵽�ļ���
	public  File  outputToSDFromInput(InputStream is,String destDirectory,String fileName){
		
		OutputStream os=null;
		File file=null;
		try {
			createDirectory(destDirectory);
			file=createFile(destDirectory+fileName);
			os=new FileOutputStream(file);
			int len=0;
			byte[] buffer=new byte[1024*4];
			while((len=is.read(buffer))>0){
				os.write(buffer, 0, len);
			}
			os.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return file;
	}
	
}
