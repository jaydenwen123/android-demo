package com.wxf.exercise;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wxf.http.HttpUtil;
import com.wxf.image.DownloadImage;
import com.wxf.image.DownloadImage.ImageCallback;
import com.wxf.json.FastJsonTool;
import com.wxf.url.CommonUrl;

public class MainActivity extends Activity {

	private static final String TAG = "main";
	private ListView listView;
	private MyAsyncTask myAsyncTask;
	private ProgressDialog progressDialog;
	private MyAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ListView) findViewById(R.id.listView);
		// String param =
		// "http://49.123.105.254:8080/xianfengProject/ReturnJsonServlet";
		// �����첽�������������ݡ���Ҫ�����֡�ͼƬ�Ⱥ����ء�
		progressDialog = new ProgressDialog(MainActivity.this);
		progressDialog.setTitle("��ʾ��");
		progressDialog.setMessage("���ڼ�������");
		myAsyncTask = new MyAsyncTask();
		myAsyncTask.execute(CommonUrl.TEXTURL);
		adapter = new MyAdapter();
		// new DownImageTask().execute(CommonUrl.IMAGEURL);
	}

	public class MyAdapter extends BaseAdapter {

		private List<Map<String, Object>> list;
		private LayoutInflater inflater=getLayoutInflater();
		private Context context;

		public void setList(List<Map<String, Object>> list) {
			this.list = list;
		}

		

		public MyAdapter() {
			super();
			// TODO Auto-generated constructor stub
		}



		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View view = null;
			if (convertView != null) {
				view = convertView;
			} else {
				view = inflater.inflate(R.layout.listview, null);
				TextView name = (TextView) view.findViewById(R.id.name);
				TextView price = (TextView) view.findViewById(R.id.price);
				TextView address = (TextView) view.findViewById(R.id.address);
				final ImageView imageView = (ImageView) view
						.findViewById(R.id.imageView1);
//				���θ�listview�����ļ��е�ÿ���ؼ���ֵ��
				name.setText(list.get(position).get("proname").toString());
				price.setText(list.get(position).get("proprice").toString());
				address.setText(list.get(position).get("proaddress").toString());
				DownloadImage downloadImage = new DownloadImage(
						CommonUrl.IMAGEURL
								+ list.get(position).get("proimage").toString());
				downloadImage.download(new ImageCallback(
						) {
//					��ͼƬ��ֵ��
					public void OnDownLoadFinishCallBack(Bitmap bitmap) {
						// TODO Auto-generated method stub
						System.out.println("--?"+bitmap);
						imageView.setImageBitmap(bitmap);
					}
				});
			}
			return view;

		}

	}

	public class MyAsyncTask extends
			AsyncTask<String, Void, List<Map<String, Object>>> {

		@Override
		protected List<Map<String, Object>> doInBackground(String... params) {
			// TODO Auto-generated method stub
			List<Map<String, Object>> list = null;
			// ��ȡ������
			InputStream inputStream = HttpUtil.getInputStream(params[0]);
			Log.i(TAG, "inputstream--->" + inputStream);
			// ��ȡ�ӷ�������������json��ʽ�����ݡ�
			String jsonStr = HttpUtil.changeInputStreamToString(inputStream,
					"utf-8");
			Log.i(TAG, "jsonStr--->" + jsonStr);
			// Ȼ����н�����
			list = FastJsonTool.getListMap(jsonStr);
			return list;
		}

		@Override
		protected void onPostExecute(List<Map<String, Object>> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			Log.i(TAG, "listMap--->" + result.toString());
			// ��listView ����������������
			// ����һ������������listView
			adapter.setList(result);
			listView.setAdapter(adapter);
			adapter.notifyDataSetChanged();
			progressDialog.dismiss();
		}

		/**
		 * 
		 * һ��������ʾ�Ի���
		 */
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			// ʵ�������������ĶԻ���Ȼ�������������ԡ�
			// ���ý�����Ϊˮƽ��ʽ��
		//	progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			progressDialog.show();
		}

		// ����һ�θ��½������Ľ���
		@Override
		protected void onProgressUpdate(Void... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			// ����������ֵ��
			// progressDialog.setProgress(values[0]);
		}

	}

}
