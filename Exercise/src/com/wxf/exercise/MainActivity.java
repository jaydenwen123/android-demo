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
		// 启动异步任务来下载数据。主要是文字。图片等候下载。
		progressDialog = new ProgressDialog(MainActivity.this);
		progressDialog.setTitle("提示：");
		progressDialog.setMessage("正在加载数据");
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
//				依次给listview布局文件中的每个控件赋值。
				name.setText(list.get(position).get("proname").toString());
				price.setText(list.get(position).get("proprice").toString());
				address.setText(list.get(position).get("proaddress").toString());
				DownloadImage downloadImage = new DownloadImage(
						CommonUrl.IMAGEURL
								+ list.get(position).get("proimage").toString());
				downloadImage.download(new ImageCallback(
						) {
//					给图片赋值。
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
			// 获取输入流
			InputStream inputStream = HttpUtil.getInputStream(params[0]);
			Log.i(TAG, "inputstream--->" + inputStream);
			// 获取从服务器下载来的json格式的数据。
			String jsonStr = HttpUtil.changeInputStreamToString(inputStream,
					"utf-8");
			Log.i(TAG, "jsonStr--->" + jsonStr);
			// 然后进行解析。
			list = FastJsonTool.getListMap(jsonStr);
			return list;
		}

		@Override
		protected void onPostExecute(List<Map<String, Object>> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			Log.i(TAG, "listMap--->" + result.toString());
			// 给listView 的适配器设置数据
			// 构建一个适配器。给listView
			adapter.setList(result);
			listView.setAdapter(adapter);
			adapter.notifyDataSetChanged();
			progressDialog.dismiss();
		}

		/**
		 * 
		 * 一般用来显示对话框。
		 */
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			// 实例化带进度条的对话框，然后设置其他属性。
			// 设置进度条为水平方式。
		//	progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			progressDialog.show();
		}

		// 用来一次更新进度条的进度
		@Override
		protected void onProgressUpdate(Void... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			// 给进度条赋值。
			// progressDialog.setProgress(values[0]);
		}

	}

}
