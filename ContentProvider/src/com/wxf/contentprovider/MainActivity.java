package com.wxf.contentprovider;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.wxf.dialog.AddDialogFragment;

public class MainActivity extends Activity implements LoaderCallbacks<Cursor> {

	private SimpleCursorAdapter cursorAdapter;
	private ListView listView;
	// private AddDialogFragment dialogFragment;
	private AlertDialog alertDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 实例化listView，从布局文件中取得listView
		listView = (ListView) findViewById(R.id.listView1);
		// 定义一个游标适配器。
		cursorAdapter = new SimpleCursorAdapter(this, R.layout.item, null,
				new String[] { "name", "age", "_id" }, new int[] {
						R.id.textView1, R.id.textView2, R.id.textView3 });
		// 给listView设置适配器。
		listView.setAdapter(cursorAdapter);
		// 通过该方法来初始胡加载器。
		getLoaderManager().initLoader(1000, null, this);
		// 给listView注册一个浮动菜单。
		registerForContextMenu(listView);
	}

	/**
	 * 
	 * 重写activity中的创建浮动菜单的方法。
	 */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		// 动态加载布局。
		MenuInflater inflater = getMenuInflater();
		// 设置菜单的布局。
		inflater.inflate(R.menu.listview, menu);
	}

	/**
	 * 
	 * 给菜单选项设置监听器。
	 */
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		int id = item.getItemId();
		// 通过switch分之语句来判断。
		switch (id) {
		// 添加菜单。
		case R.id.add:
			// 首先构建一个对话框。然后从里面取得要添加的数据。
			AlertDialog.Builder builder = new Builder(MainActivity.this);
			// 自定义对话框的布局。、
			final View view = LayoutInflater.from(MainActivity.this).inflate(
					R.layout.add, null);
			// 获取确定按钮。并设置监听器。
			Button button = (Button) view.findViewById(R.id.addok);
			button.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					// TODO Auto-generated method stub
					// 取得输入框，并且获取其中的输入的数值。
					EditText editText = (EditText) view.findViewById(R.id.name);
					EditText editText2 = (EditText) view.findViewById(R.id.age);
					String name = editText.getText().toString();
					int age = Integer.parseInt(editText2.getText().toString());
					// 构建一个contentResolver对象。通过contentProvider来存储数据。
					ContentResolver contentResolver = getContentResolver();
					// 解析uri对象。
					Uri uri = Uri
							.parse("content://com.wxf.contentprovider.MyContextProvider/user");
					ContentValues values = new ContentValues();
					values.put("name", name);
					values.put("age", age);
					// 执行添加的操作
					uri = contentResolver.insert(uri, values);
					if (uri != null) {
						// 添加成功后.动态的跟新数据.
						getLoaderManager().restartLoader(1000, null,
								MainActivity.this);
					}
					// 影藏对话框.
					alertDialog.dismiss();
					// 通过toast来提示.
					Toast.makeText(MainActivity.this, "添加成功",
							Toast.LENGTH_SHORT).show();

				}
			});
			// 创建对话框实例.
			builder.setView(view);
			alertDialog = builder.show();
			return true;
		case R.id.update:

			return true;
			// 执行删除的操作
		case R.id.delete:
			// 构建一个contentResolver对象。
			ContentResolver contentResolver = getContentResolver();
			// 获取adapter适配器的菜单信息。通过该信息。可以获取点击的listView的每个一行的布局文件，从而获取其中的控件和值。
			AdapterContextMenuInfo adapterContextMenuInfo = (AdapterContextMenuInfo) item
					.getMenuInfo();
			System.out.println(adapterContextMenuInfo.targetView + ","
					+ adapterContextMenuInfo.position);
//			通过属性targetView获取布局属性。通过postition获取行数。默认从0开始。
			View view2 = adapterContextMenuInfo.targetView;
			TextView id1 = (TextView) view2.findViewById(R.id.textView3);
//			获取每个记录的id。然后对其进行删除的操作。
			int selectedId = Integer.parseInt(id1.getText().toString());
			Uri uri = Uri
					.parse("content://com.wxf.contentprovider.MyContextProvider/user/");
//			执行删除的操作。
			int result = contentResolver.delete(uri, "_id=?",
					new String[] { selectedId + "" });
			if (result == 1) {
//			删除成功的话。然后执行重新加载数据。
				getLoaderManager().restartLoader(1000, null, MainActivity.this);

			}
//			通过toast提示。
			Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT)
					.show();
			return true;
		case R.id.query:

			return true;
		}
		return false;

	}

	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		// TODO Auto-generated method stub
		// 构建一个游标加载器。
		CursorLoader loader = null;
		// 然后对其各个查询的属性进行赋值。
		// 也可以使用，CursorLoader(Context context, Uri uri, String[] projection,
		// String selection, String[] selectionArgs, String sortOrder)该构造器
		loader = new CursorLoader(this);
		loader.setProjection(null);
		loader.setSortOrder(null);
		loader.setSelection(null);
		loader.setSelectionArgs(null);
		Uri uri = Uri
				.parse("content://com.wxf.contentprovider.MyContextProvider/user");
		loader.setUri(uri);
		return loader;
	}

//	数据加载完成.
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		// TODO Auto-generated method stub
		cursorAdapter.swapCursor(data);
	}

	public void onLoaderReset(Loader<Cursor> loader) {
		// TODO Auto-generated method stub
		cursorAdapter.swapCursor(null);
	}

}
