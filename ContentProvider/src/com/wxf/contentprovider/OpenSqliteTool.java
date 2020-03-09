package com.wxf.contentprovider;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class OpenSqliteTool extends SQLiteOpenHelper {

//	���ݿ�İ汾
	private static final int VERSION=1;
//	���ݿ�����
	private static final String PROVIDER="provider"; 
//	���������䡣
	private  String[] sqls;
//	Ӧ��������
	private  Context context;
	
 	public String[] getSqls() {
		return sqls;
	}
	public void setSqls(String[] sqls) {
		this.sqls = sqls;
	}
	/**
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 */
	public OpenSqliteTool(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
 	/**
 	 * @param context
 	 * @param factory
 	 */
 	public OpenSqliteTool(Context context,
 			CursorFactory factory) {
 		super(context, PROVIDER, factory, VERSION);
 		// TODO Auto-generated constructor stub
 	}
 	

	/**
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 * @param errorHandler
	 * constructor method
	 */
	public OpenSqliteTool(Context context, String name,
			CursorFactory factory, int version,
			DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * �������ݿ��еı�
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		createTable(db);
	}
	
	private void createTable(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		for(String sql:sqls){
			db.execSQL(sql);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
