package com.wxf.file.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.example.download.R.id;


public class DownloadUtil {
	private FileUtil fileUtil;

	public DownloadUtil() {
		// TODO Auto-generated constructor stub
		fileUtil=new FileUtil();
	}

	public String  downloadText(String strUrl){
		InputStream is=null;
		StringBuffer stringBuffer=new StringBuffer();
		String line=null;
		BufferedReader reader=null;
		try {
			is=getInputStream(strUrl);
			reader=new BufferedReader(new InputStreamReader(is));
			while((line=reader.readLine())!=null){
				stringBuffer.append(line);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stringBuffer.toString();
	}
	
	public int downloadFile(final String strUrl, final String path, final String fileName) {
		InputStream	is =null;
			try {
//				如果文件存在，返回1
				if(fileUtil.fileIsExist(path+fileName)){
					return 1;
				}
				else {
					is=getInputStream(strUrl);
					File file=fileUtil.outputToSDFromInput(is, path, fileName);
					if(file==null){
//				下载失败，返回-1
						return -1;
					}
				}
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return 0;
			
	}
	private InputStream getInputStream(final String strUrl)
			throws MalformedURLException, IOException {
		URL url = new URL(strUrl);
		HttpURLConnection connection = (HttpURLConnection) url
				.openConnection();
		InputStream is = connection.getInputStream();
		return is;
	}
}
