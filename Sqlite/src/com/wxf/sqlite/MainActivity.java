package com.wxf.sqlite;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.wxf.sql.SqliteDataBaseHelper;

/**
 * 
 * ����sqlite����ɾ�Ĳ�Ĳ���
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public class MainActivity extends Activity {

	private Button createDatabase;
	private Button updateDatabase;
	private Button insert;
	private Button update;
	private Button delete;
	private Button query;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		createDatabase = (Button) findViewById(R.id.createDatabase);
		createDatabase.setOnClickListener(new createDataBaseButtonListener());
		updateDatabase = (Button) findViewById(R.id.updatedDatabase);
		updateDatabase.setOnClickListener(new updateDatabaseButtonListener());
		insert = (Button) findViewById(R.id.insert);
		insert.setOnClickListener(new insertButtonListener());
		update = (Button) findViewById(R.id.update);
		update.setOnClickListener(new updateButtonListener());
		delete = (Button) findViewById(R.id.delete);
		delete.setOnClickListener(new deleteButtonListener());
		query = (Button) findViewById(R.id.query);
		query.setOnClickListener(new queryButtonListener());
	}

	/**
	 * @author Administrator
	 *
	 *
	 *�ڸü������У�ִ�д������ݿ�Ĳ���
	 *����ͬʱ������
	 */
	private class createDataBaseButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			SqliteDataBaseHelper dbhelper = new SqliteDataBaseHelper(
					MainActivity.this, "firstAndroid");
			SQLiteDatabase db = dbhelper.getReadableDatabase();
		}

	}

	/**
	 * @author Administrator
	 *
	 *
	 *�ڸü������У�ִ�е����ݿⱻ���������޸ĵ�ʱ�򣬽���һЩ����
	 */
	private class updateDatabaseButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			getWritableDatabase();
		}


	}
//	��ȡ��д�����ݿ⣬���ڸ����ݿ���������ݣ��޸����ݣ�ɾ��ŭ���ݵĲ���
	private SQLiteDatabase getWritableDatabase() {
		SqliteDataBaseHelper dbhelper = new SqliteDataBaseHelper(
				MainActivity.this, "firstAndroid", 3);
		SQLiteDatabase db = dbhelper.getWritableDatabase();
		return db;
	}

	/**
	 * @author Administrator
	 *
	 *�ڸü������У�ִ�ж����ݿ��������ݵĲ���
	 */
	private class insertButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			/*
			 * public long insert(String table, String nullColumnHack,
			 * ContentValues values)Convenience method for inserting a row into
			 * the database. ͨ���ú��������ж����ݿ��еı�������� table:ָ���Ǹ�������������
			 * nullcolumnHack:
			 */
			SQLiteDatabase database=getWritableDatabase();
			ContentValues contentValues = new ContentValues();
			contentValues.put("id", 3);
			contentValues.put("name", "wenxiaofei");
			database.insert("user", null, contentValues);
			System.out.println("insert into datbase.user succeess");
		}

	}

	/**
	 * @author Administrator
	 *
	 *
	 *�ڸü������У�ִ�ж����ݿ����޸����ݵĲ���
	 */
	private class updateButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			SQLiteDatabase database=getWritableDatabase();
			ContentValues contentValues=new ContentValues();
			contentValues.put("name", "hello");
			database.update("user", contentValues, "id>?", new String[]{1+""});
			System.out.println("the database.user is update success");
		}

	}

	/**
	 * @author Administrator
	 *
	 *
	 *�ڸü������У�ִ�ж����ݿ������ݵ�ɾ������
	 */
	private class deleteButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			SQLiteDatabase database=getWritableDatabase();
			//ContentValues contentValues=new ContentValues();
			database.delete("user", "id=? or name=?",new String[]{1+"","123s"});
			System.out.println("the database.user is delete success");
		}

	}

	/**
	 * @author Administrator
	 *
	 *
	 *�ڸü������У�ִ�ж����ݵĲ�ѯ��ŷ��Ŷ
	 */
	private class queryButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			SQLiteDatabase database = getReadableDatabase();
			ContentValues contentValues=new ContentValues();
			/**
			 * Cursor android.database.sqlite.SQLiteDatabase.query(String table, 
			 * String[] columns, String selection, String[] selectionArgs, String groupBy,
			 *  String having, String orderBy)
			 */
			Cursor cursor=database.query("user", new String[]{"id","name"}, "id>? and id<?", new String[]{1+"",9+""}, null, null, null);
			while(cursor.moveToNext()){
				System.out.println("id="+cursor.getInt(cursor.getColumnIndex("id"))
						+"----name="+cursor.getString(cursor.getColumnIndex("name")));
			}
			System.out.println("the database.user is query success");
		}

	}
//	��ȡ�ɶ�����������ݿ⣬���ڲ�ѯ����
	private SQLiteDatabase getReadableDatabase() {
		SqliteDataBaseHelper helper=new SqliteDataBaseHelper(MainActivity.this, "firstAndroid", 3);
		SQLiteDatabase database=helper.getReadableDatabase();
		return database;
	}
}
