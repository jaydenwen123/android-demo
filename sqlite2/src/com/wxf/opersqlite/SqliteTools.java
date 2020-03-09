package com.wxf.opersqlite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.R.integer;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ImageView;

import com.wxf.db.helper.SqliteDbHelper;

public class SqliteTools {

	public SqliteDbHelper sqliteDbHelper;
	public static final int READDATABASE = 1;
	public static final int WRITEDATABASE = 0;

	public SqliteTools(Context context) {
		// TODO Auto-generated constructor stub
		sqliteDbHelper = new SqliteDbHelper(context);
	}

	public SqliteDbHelper getSqliteDbHelper() {
		return sqliteDbHelper;
	}

	/**
	 * ���뷽��
	 * 
	 * @param tableName
	 * @param values
	 * @return
	 * @throws SQLException
	 */
	public boolean insert(int dbOption, String tableName, ContentValues values)
			throws SQLException {
		boolean flag = false;
		long result = 0;
		SQLiteDatabase database = getDatabase(dbOption);

		try {
			result = database.insert(tableName, null, values);
			// System.out.println(result);
			flag = result > 0 ? true : false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (database != null) {
				database.close();
			}
		}
		return flag;
	}

	/**
	 * ���·���
	 * 
	 * @param tableName
	 * @param values
	 * @param whereClause
	 * @param whereArgs
	 * @return
	 * @throws SQLException
	 */
	public boolean update(int dbOption, String tableName, ContentValues values,
			String whereClause, String[] whereArgs) throws SQLException {
		boolean flag = false;
		SQLiteDatabase database = getDatabase(dbOption);
		long result = 0;
		try {
			result = database.update(tableName, values, whereClause, whereArgs);
			flag = result > 0 ? true : false;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (database != null) {
				database.close();
			}
		}
		return flag;

	}

	/**
	 * ɾ������
	 * 
	 * @param tableName
	 * @param whereClause
	 * @param whereArgs
	 * @return
	 */
	public boolean delete(int dbOption, String tableName, String whereClause,
			String[] whereArgs) throws SQLException {
		boolean flag = false;
		SQLiteDatabase database = getDatabase(dbOption);
		long result = 0;
		try {
			result = database.delete(tableName, whereClause, whereArgs);
			flag = result > 0 ? true : false;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (database != null) {
				database.close();
			}
		}
		return flag;
	}

	/**
	 * ��ȡ���еļ�¼
	 * 
	 * @param tableName
	 * @param columns
	 * @param selection
	 * @param selectionArgs
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> queryUniqueResult(int dbOption,
			String tableName, String[] columns, String selection,
			String[] selectionArgs) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		SQLiteDatabase database = getDatabase(dbOption);
		Cursor cursor = null;
		try {
			cursor = database.query(tableName, columns, selection,
					selectionArgs, null, null, null, null);
			int rowCount = cursor.getColumnCount();
			while (cursor.moveToNext()) {
				for (int i = 0; i < rowCount; i++) {
					String columnName = cursor.getColumnName(i);
					Object columnValue = cursor.getString(cursor
							.getColumnIndex(columnName));
					map.put(columnName, columnValue);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (database != null) {
				database.close();
			}
		}
		return map;
	}

	/**
	 * ��ѯ������¼
	 * 
	 * @param tableName
	 * @param columns
	 * @param selection
	 * @param selectionArgs
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> queryMoreResults(int dbOption,
			String tableName, String[] columns, String selection,
			String[] selectionArgs) throws SQLException {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		SQLiteDatabase database = getDatabase(dbOption);
		Cursor cursor = null;
		try {
			cursor = database.query(tableName, columns, selection,
					selectionArgs, null, null, null);
			int columnCount = cursor.getColumnCount();
			while (cursor.moveToNext()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 0; i < columnCount; i++) {
					String columnName = cursor.getColumnName(i);
					Object columnValue = cursor.getString(cursor
							.getColumnIndex(columnName));
					map.put(columnName, columnValue);
				}
				list.add(map);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (database != null) {
				database.close();
			}
		}
		return list;
	}

	// ʹ��sqlԭ����sql�����ִ����ɾ�Ĳ�
	/**
	 * 
	 * @param dbOption
	 * @param sql
	 * @param paramsռλ������
	 * @throws java.sql.SQLException
	 *             ִ����ɾ�Ĳ���
	 */
	public void execute(int dbOption, String sql, String[] params)
			throws java.sql.SQLException {
		SQLiteDatabase database=getDatabase(dbOption);
		database.execSQL(sql, params);
	}

	/**��ѯ������¼
	 * @param dbOption
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryMoreResults(int dbOption, String sql,
			String[] params) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		SQLiteDatabase database = getDatabase(dbOption);
		Cursor cursor=null;
		try {
			cursor=database.rawQuery(sql, params);
			int columnCount=cursor.getColumnCount();
			while(cursor.moveToNext()){
				Map<String, Object> map=new HashMap<String, Object>();
				for(int i=0;i<columnCount;i++){
					String columnName=cursor.getColumnName(i);
					Object columnValue=cursor.getString(cursor.getColumnIndex(columnName));
					map.put(columnName, columnValue);
				}
				list.add(map);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * @param dbOption
	 * @param sql
	 * @param params
	 * @return
	 * @throws java.sql.SQLException
	 *             ��ȡ������¼��ͨ��ԭ����sql�������ѯ
	 */
	public Map<String, Object> queryUniqueResult(int dbOption, String sql,
			String[] params) throws java.sql.SQLException {

		Map<String, Object> map = new HashMap<String, Object>();
		SQLiteDatabase database = getDatabase(dbOption);
		Cursor cursor = null;
		try {
			cursor = database.rawQuery(sql, params);
//			��ȡ�еļ�¼��
			int columnCount = cursor.getColumnCount();
			while (cursor.moveToNext()) {
				for (int i = 0; i < columnCount; i++) {
					String columnName = cursor.getColumnName(i);
					Object columnValue = cursor.getString(cursor
							.getColumnIndex(columnName));
					map.put(columnName, columnValue);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

//	ͨ������Ĳ�������ȡ��ͬ������Ӧ�����ݿ�.
	private SQLiteDatabase getDatabase(int option) {
		SQLiteDatabase database = null;
		switch (option) {
//		��ȡ�ɶ������ݿ⡣ִ�в�ѯ������
		case READDATABASE:
			database = sqliteDbHelper.getReadableDatabase();
			break;

		case WRITEDATABASE:
//			��ȡ��д�����ݿ⡣ִ�����ӣ�ɾ�����޸Ĳ���
			database = sqliteDbHelper.getWritableDatabase();
			break;
		}
		return database;
	}

}
