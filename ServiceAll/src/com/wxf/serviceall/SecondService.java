package com.wxf.serviceall;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Path.FillType;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

/**
 * @author Administrator
 * 
 *         ��ʹ�ü̳�IntentService�࣬��ֻ����дonhnadleIntent�������ɣ��÷���
 *         �Ѿ��ڲ������̺߳�handler��˲���Ҫ���¿���һ���̡߳�����ֱ�Ӳ������硣���أ��ļ������ȡ�
 *         ��������������������ͼƬ���ŵ�sdCard���С�
 */
public class SecondService extends IntentService {

	public SecondService() {
		super("intentService");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		HttpClient client = new DefaultHttpClient();
		HttpResponse response = null;
		HttpEntity entity = null;
		String url = "https://www.baidu.com/img/bd_logo1.png";
		HttpPost post = new HttpPost(url);
		// ��ȡsdCard����·����
		File sdCardPath = Environment.getExternalStorageDirectory();
		try {
			response = client.execute(post);
			if (response.getStatusLine().getStatusCode() == 200) {
				// ��ȥ��Ϣʵ�塣
				entity = response.getEntity();
				// ����Ϣʵ��ת��Ϊ�ֽ����飬��������д��
				byte[] data = EntityUtils.toByteArray(entity);
				// �����ļ�
				downLoadFile(sdCardPath, "baidu.png", data);
				System.out.println("--->"+getApplicationContext());
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @param sdCardPath
	 * @param fileName
	 * @param data
	 * @throws FileNotFoundException
	 * @throws IOException
	 *             �����ļ��ķ���
	 */
	private void downLoadFile(File sdCardPath, String fileName, byte[] data)
			throws FileNotFoundException, IOException {
		OutputStream outputStream = null;
		try {
			File file;
			file = new File(sdCardPath, fileName);
			outputStream = new FileOutputStream(file);
			// ���ļ�д�뵽sdCard���С�
			outputStream.write(data, 0, data.length);
			outputStream.flush();
//			���Ҫ��intentService�У�ʹ��Toast��ʾ��Ϣ������Ҫʹ��hadnler��������Ϣ
		new Handler(Looper.getMainLooper()).post(new Runnable() {
			public void run() {
				// TODO Auto-generated method stub
				Toast.makeText(getBaseContext(), "�������", Toast.LENGTH_SHORT)
				.show();
			}
		});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}

	}

}
