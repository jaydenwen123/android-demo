package com.wxf.test;

import com.wxf.operate.OperateFile;

import android.content.Context;
import android.test.AndroidTestCase;
import android.util.Log;


public class OperateFileTest extends AndroidTestCase{

	private Context context=getContext();
	private OperateFile operateFile;
	public OperateFileTest() {
		// TODO Auto-generated constructor stub
		operateFile=new OperateFile(context);
	}
	
	public void testwriteContextToFile(){
		boolean falg=operateFile.writeContextToFile("hello.txt", "ÄãºÃÂð£¬ÅóÓÑ");
		Log.i("main", "--->"+falg);

	}
	
	public void testreadContextFromFile(){
		String fileName="hello.txt";
		boolean falg=operateFile.readContextFromFile(fileName);
		Log.i("main","---->"+falg);
	}
}


