package com.wxf.db.helper;

import android.R.integer;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteDbHelper extends SQLiteOpenHelper {

//	数据库的版本
	private static final int VERSION = 1;
//	数据库的名称。
	private static final String DBNAME = "mydb";
	private  String[] sqls=null;

	
	
	public void setSqls(String[] sqls) {
		this.sqls = sqls;
	}

	public SqliteDbHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	public SqliteDbHelper(Context context, CursorFactory factory) {
		// TODO Auto-generated constructor stub
		this(context, DBNAME, factory, VERSION);
	}
	public SqliteDbHelper(Context context, String name) {
		// TODO Auto-generated constructor stub
		this(context, name, null, VERSION);
	}

	public SqliteDbHelper(Context context) {
		// TODO Auto-generated constructor stub
		this(context, DBNAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
//		执行创建表的操作。
		if(sqls!=null && sqls.length>0){
			for(String sql:sqls){
				System.out.println("heihei:"+sql);
				db.execSQL(sql);
			}
		}
		System.out.println("the database is oncreate");
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		System.out.println("the database is onupgrade");
	}

}
