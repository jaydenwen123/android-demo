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
 * 测试sqlite的增删改查的操作
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
	 *在该监听器中，执行创建数据库的操作
	 *并且同时创建表
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
	 *在该监听器中，执行当数据库被升级或者修改的时候，进行一些操作
	 */
	private class updateDatabaseButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			getWritableDatabase();
		}


	}
//	获取可写的数据库，用于给数据库中添加数据，修改数据，删除怒数据的操作
	private SQLiteDatabase getWritableDatabase() {
		SqliteDataBaseHelper dbhelper = new SqliteDataBaseHelper(
				MainActivity.this, "firstAndroid", 3);
		SQLiteDatabase db = dbhelper.getWritableDatabase();
		return db;
	}

	/**
	 * @author Administrator
	 *
	 *在该监听器中，执行对数据库的添加数据的操作
	 */
	private class insertButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			/*
			 * public long insert(String table, String nullColumnHack,
			 * ContentValues values)Convenience method for inserting a row into
			 * the database. 通过该函数来进行对数据库中的表插入数据 table:指对那个表进行添加数据
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
	 *在该监听器中，执行对数据库中修改数据的操作
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
	 *在该监听器中，执行对数据库中数据的删除操作
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
	 *在该监听器中，执行对数据的查询擦欧洲哦
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
//	获取可读额的数据数据库，用于查询操作
	private SQLiteDatabase getReadableDatabase() {
		SqliteDataBaseHelper helper=new SqliteDataBaseHelper(MainActivity.this, "firstAndroid", 3);
		SQLiteDatabase database=helper.getReadableDatabase();
		return database;
	}
}
