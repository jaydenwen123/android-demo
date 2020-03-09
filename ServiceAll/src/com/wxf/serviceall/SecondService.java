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
 *         若使用继承IntentService类，则只需重写onhnadleIntent方法即可，该方法
 *         已经内部存在线程和handler因此不需要重新开启一个线程。可以直接操作网络。下载，文件操作等。
 *         该例子用来从网上下载图片。放到sdCard卡中。
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
		// 获取sdCard卡的路径。
		File sdCardPath = Environment.getExternalStorageDirectory();
		try {
			response = client.execute(post);
			if (response.getStatusLine().getStatusCode() == 200) {
				// 或去消息实体。
				entity = response.getEntity();
				// 将消息实体转换为字节数组，方便用来写出
				byte[] data = EntityUtils.toByteArray(entity);
				// 下载文件
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
	 *             下载文件的方法
	 */
	private void downLoadFile(File sdCardPath, String fileName, byte[] data)
			throws FileNotFoundException, IOException {
		OutputStream outputStream = null;
		try {
			File file;
			file = new File(sdCardPath, fileName);
			outputStream = new FileOutputStream(file);
			// 将文件写入到sdCard卡中。
			outputStream.write(data, 0, data.length);
			outputStream.flush();
//			如果要在intentService中，使用Toast提示消息。则需要使用hadnler来发送消息
		new Handler(Looper.getMainLooper()).post(new Runnable() {
			public void run() {
				// TODO Auto-generated method stub
				Toast.makeText(getBaseContext(), "下载完成", Toast.LENGTH_SHORT)
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
