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
		// ʵ����listView���Ӳ����ļ���ȡ��listView
		listView = (ListView) findViewById(R.id.listView1);
		// ����һ���α���������
		cursorAdapter = new SimpleCursorAdapter(this, R.layout.item, null,
				new String[] { "name", "age", "_id" }, new int[] {
						R.id.textView1, R.id.textView2, R.id.textView3 });
		// ��listView������������
		listView.setAdapter(cursorAdapter);
		// ͨ���÷�������ʼ����������
		getLoaderManager().initLoader(1000, null, this);
		// ��listViewע��һ�������˵���
		registerForContextMenu(listView);
	}

	/**
	 * 
	 * ��дactivity�еĴ��������˵��ķ�����
	 */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		// ��̬���ز��֡�
		MenuInflater inflater = getMenuInflater();
		// ���ò˵��Ĳ��֡�
		inflater.inflate(R.menu.listview, menu);
	}

	/**
	 * 
	 * ���˵�ѡ�����ü�������
	 */
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		int id = item.getItemId();
		// ͨ��switch��֮������жϡ�
		switch (id) {
		// ��Ӳ˵���
		case R.id.add:
			// ���ȹ���һ���Ի���Ȼ�������ȡ��Ҫ��ӵ����ݡ�
			AlertDialog.Builder builder = new Builder(MainActivity.this);
			// �Զ���Ի���Ĳ��֡���
			final View view = LayoutInflater.from(MainActivity.this).inflate(
					R.layout.add, null);
			// ��ȡȷ����ť�������ü�������
			Button button = (Button) view.findViewById(R.id.addok);
			button.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					// TODO Auto-generated method stub
					// ȡ������򣬲��һ�ȡ���е��������ֵ��
					EditText editText = (EditText) view.findViewById(R.id.name);
					EditText editText2 = (EditText) view.findViewById(R.id.age);
					String name = editText.getText().toString();
					int age = Integer.parseInt(editText2.getText().toString());
					// ����һ��contentResolver����ͨ��contentProvider���洢���ݡ�
					ContentResolver contentResolver = getContentResolver();
					// ����uri����
					Uri uri = Uri
							.parse("content://com.wxf.contentprovider.MyContextProvider/user");
					ContentValues values = new ContentValues();
					values.put("name", name);
					values.put("age", age);
					// ִ����ӵĲ���
					uri = contentResolver.insert(uri, values);
					if (uri != null) {
						// ��ӳɹ���.��̬�ĸ�������.
						getLoaderManager().restartLoader(1000, null,
								MainActivity.this);
					}
					// Ӱ�ضԻ���.
					alertDialog.dismiss();
					// ͨ��toast����ʾ.
					Toast.makeText(MainActivity.this, "��ӳɹ�",
							Toast.LENGTH_SHORT).show();

				}
			});
			// �����Ի���ʵ��.
			builder.setView(view);
			alertDialog = builder.show();
			return true;
		case R.id.update:

			return true;
			// ִ��ɾ���Ĳ���
		case R.id.delete:
			// ����һ��contentResolver����
			ContentResolver contentResolver = getContentResolver();
			// ��ȡadapter�������Ĳ˵���Ϣ��ͨ������Ϣ�����Ի�ȡ�����listView��ÿ��һ�еĲ����ļ����Ӷ���ȡ���еĿؼ���ֵ��
			AdapterContextMenuInfo adapterContextMenuInfo = (AdapterContextMenuInfo) item
					.getMenuInfo();
			System.out.println(adapterContextMenuInfo.targetView + ","
					+ adapterContextMenuInfo.position);
//			ͨ������targetView��ȡ�������ԡ�ͨ��postition��ȡ������Ĭ�ϴ�0��ʼ��
			View view2 = adapterContextMenuInfo.targetView;
			TextView id1 = (TextView) view2.findViewById(R.id.textView3);
//			��ȡÿ����¼��id��Ȼ��������ɾ���Ĳ�����
			int selectedId = Integer.parseInt(id1.getText().toString());
			Uri uri = Uri
					.parse("content://com.wxf.contentprovider.MyContextProvider/user/");
//			ִ��ɾ���Ĳ�����
			int result = contentResolver.delete(uri, "_id=?",
					new String[] { selectedId + "" });
			if (result == 1) {
//			ɾ���ɹ��Ļ���Ȼ��ִ�����¼������ݡ�
				getLoaderManager().restartLoader(1000, null, MainActivity.this);

			}
//			ͨ��toast��ʾ��
			Toast.makeText(MainActivity.this, "ɾ���ɹ�", Toast.LENGTH_SHORT)
					.show();
			return true;
		case R.id.query:

			return true;
		}
		return false;

	}

	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		// TODO Auto-generated method stub
		// ����һ���α��������
		CursorLoader loader = null;
		// Ȼ����������ѯ�����Խ��и�ֵ��
		// Ҳ����ʹ�ã�CursorLoader(Context context, Uri uri, String[] projection,
		// String selection, String[] selectionArgs, String sortOrder)�ù�����
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

//	���ݼ������.
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		// TODO Auto-generated method stub
		cursorAdapter.swapCursor(data);
	}

	public void onLoaderReset(Loader<Cursor> loader) {
		// TODO Auto-generated method stub
		cursorAdapter.swapCursor(null);
	}

}
