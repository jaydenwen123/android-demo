package com.wxf.domain;

import java.util.Map;

import android.R.integer;

public class Task {

	public static final int LOGIN = 1;
	public static final int AUTH = 2;
	public static final int GETTOKEN = 3;
	public static final int GET_ACCESSKON=4;
	private int taskId;
	private Map<String, Object> taskParams;

	public Task() {
		// TODO Auto-generated constructor stub
	}

	public Task(int taskId, Map<String, Object> taskParams) {
		super();
		this.taskId = taskId;
		this.taskParams = taskParams;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public Map<String, Object> getTaskParams() {
		return taskParams;
	}

	public void setTaskParams(Map<String, Object> taskParams) {
		this.taskParams = taskParams;
	}

}
