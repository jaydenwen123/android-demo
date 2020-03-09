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

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

/**
 * @author Administrator
 * ͨ���̳�service��ʵ��һ���Զ����service
 * ��service��������һ��ͼƬ��
 */
public class ThridService extends Service {

	private Handler handler;

	public ThridService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		// ʵ����һ��handler
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if (msg.what == 2) {
					System.out.println("---->������ϡ�������");
					Toast.makeText(getApplicationContext(), "�������",
							Toast.LENGTH_SHORT).show();
				}
				super.handleMessage(msg);
			}
		};
		// ͨ���߳������ļ���
		new Thread(new Runnable() {

			public void run() {
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
						downLoadFile(sdCardPath, "baidu2.png", data);
						// System.out.println("--->"+getApplicationContext());
					}
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Message message = Message.obtain();
				message.what = 2;
				handler.sendMessage(message);
			}
		}).start();
		return super.onStartCommand(intent, flags, startId);
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
