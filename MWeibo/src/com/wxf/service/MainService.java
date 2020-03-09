package com.wxf.service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

import weibo4j.http.AccessToken;
import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.wxf.domain.Task;
import com.wxf.ui.IActivity;
import com.wxf.util.AccessAuthUtil;
import com.wxf.util.JavaScriptInterface;

public class MainService extends Service implements Runnable {

	// �����е�����ŵ������С�
	private static Queue<Task> tasks = new ArrayDeque<Task>();

	// �����е�activity�ŵ�������
	private static ArrayList<Activity> activities = new ArrayList<Activity>();

	// ����handler
	private Myhandler hander = new Myhandler();

	// ����һ��task����
	private Task task;

	private boolean isRun;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		isRun = true;
		new Thread(this).start();
	}

	public MainService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run() ���߳���ִ�о���Ĳ���
	 */
	public void run() {

		while (isRun) {
			if (!tasks.isEmpty()) {
				task = tasks.poll();
				if (task != null) {
					doTask(task);
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * @param activity
	 *            ���activity
	 */
	public static void addActivity(Activity activity) {
		activities.add(activity);
	}

	/**
	 * @param task
	 *            ���task��������
	 */
	public static void addTask(Task task) {
		tasks.add(task);
	}

	/**
	 * @param task
	 */
	public void doTask(Task task) {
		// ����message����
		Message message = Message.obtain(hander);
		message.what = task.getTaskId();
		switch (task.getTaskId()) {
		case Task.LOGIN:
			message.obj = "��¼�ɹ�";
			break;
		case Task.AUTH:
			message.obj = "��ʼ";
			break;
		case Task.GETTOKEN:
			String url = AccessAuthUtil.getAuthorizationURL();
			if (url != null) {
				message.obj = url;
			}
			break;
		case Task.GET_ACCESSKON:
			String pin = null;
			// �����ȡ����pinΪ�գ���ѭ��һֱ��ȡ
			while (pin == null) {
				pin = JavaScriptInterface.PIN;
			}
			Log.i("accesstoken", pin);
			AccessToken accessToken = null;
			while (accessToken == null) {
				accessToken = AccessAuthUtil.getAccessToken(pin);
			}
			message.obj = accessToken;
			break;
		default:
			break;
		}
		message.sendToTarget();
	}

	private class Myhandler extends Handler {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case Task.LOGIN:
				// �������е�activityת�ɽӿڣ�Ȼ���ڵ��ø÷���������ui
				// IActivity activity = (IActivity)
				// getActivityByName("AuthActivity");
				// activity.refresh(msg.obj);
				break;
			case Task.AUTH:
				IActivity activity = (IActivity) getActivityByName("AuthActivity");
				activity.refresh(msg.obj);
				break;
			case Task.GETTOKEN:
				IActivity webViewActivity = (IActivity) getActivityByName("WebViewActivity");
				webViewActivity.refresh(msg.obj);
				break;

			case Task.GET_ACCESSKON:
				IActivity accessTokenActivity = (IActivity) getActivityByName("AccessTokenActivity");
				accessTokenActivity.refresh(msg.obj);
				break;

			default:
				break;
			}
		}

		/**
		 * @param name
		 * 
		 * @return ����activity����������ȡactivity
		 */
		private Activity getActivityByName(String name) {
			// TODO Auto-generated method stub
			Activity activity = null;
			// ͨ����ǿ��forѭ���������ü���
			if (!activities.isEmpty()) {
				for (Activity ac : activities) {
					if (ac.getClass().getName().indexOf(name) > 0) {
						activity = ac;
					}
				}
			}
			return activity;
		}
	}

}
