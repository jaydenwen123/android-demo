package com.wxf.sql;

import android.R.integer;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteDataBaseHelper extends SQLiteOpenHelper{

	static final int  VERSION=1;
	public SqliteDataBaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	public SqliteDataBaseHelper(Context context, String name, int version) {
		// TODO Auto-generated constructor stub
		this(context, name, null, version);
	}
	public SqliteDataBaseHelper(Context context, String name) {
		// TODO Auto-generated constructor stub
		this(context, name, VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		System.out.println("the database is  creating ");
		String sql="create table user(id int(11) not null primary key,name varchar(32))";
		db.execSQL(sql);
		System.out.println("the user table is creating succeed");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		System.out.println("the database is upgraded");
	}
	@Override
	public void onOpen(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		super.onOpen(db);
		System.out.println("the database is opened");
	}
	

}
