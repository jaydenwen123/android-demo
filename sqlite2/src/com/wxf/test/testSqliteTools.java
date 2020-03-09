package com.wxf.test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.test.AndroidTestCase;

import com.wxf.opersqlite.SqliteTools;

@SuppressWarnings("deprecation")
public class testSqliteTools extends AndroidTestCase {

	private SqliteTools sqliteTools;

	public testSqliteTools() {
		// TODO Auto-generated constructor stub
	}

	public void testInsert() {
		sqliteTools = new SqliteTools(getContext());
		// ´´½¨±í
		String[] sqls = new String[] { "create table user(id integer primary key autoincrement,"
				+ "username varchar(64),"
				+ "password varchar(64),realname varchar(64))" };
		sqliteTools.getSqliteDbHelper().setSqls(sqls);
		String tableName = "user";
		ContentValues values = new ContentValues();
		values.put("username", "wenxiaofei");
		values.put("password", "123");
		values.put("realname", "xiaowen");
		boolean flag = sqliteTools.insert(SqliteTools.WRITEDATABASE, tableName,
				values);
		assertTrue(flag);
		// System.out.println(flag);

	}

	public void testDelete() {
		String sql = "delete from user where id=?";
		sqliteTools = new SqliteTools(getContext());
		boolean flag = false;
		try {
			sqliteTools.execute(SqliteTools.WRITEDATABASE, sql,
					new String[] { 2 + "" });
			flag=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(flag);
	}

	public void testUpdate() {

	}

	public void testQuery() {
		sqliteTools = new SqliteTools(getContext());
		String sql = "select * from user";
		List<Map<String, Object>> list = sqliteTools.queryMoreResults(
				SqliteTools.READDATABASE, sql, null);
		System.out.println(list);
	}

	public void testQueryUnique() {
		sqliteTools = new SqliteTools(getContext());
		Map<String, Object> map = sqliteTools.queryUniqueResult(
				SqliteTools.READDATABASE, "user", null, "id=?",
				new String[] { 1 + "" });
		System.out.println(map);
		assertNotNull(map);
	}

	public void testqueryMoreResults() {
		sqliteTools = new SqliteTools(getContext());
		List<Map<String, Object>> list = sqliteTools.queryMoreResults(
				SqliteTools.READDATABASE, "user", null, "id>?",
				new String[] { 1 + "" });
		System.out.println(list);
		assertNotNull(list);
	}

}
