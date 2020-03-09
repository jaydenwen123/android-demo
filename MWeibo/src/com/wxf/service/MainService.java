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

	// 将所有的任务放到队列中。
	private static Queue<Task> tasks = new ArrayDeque<Task>();

	// 将所有的activity放到集合中
	private static ArrayList<Activity> activities = new ArrayList<Activity>();

	// 定义handler
	private Myhandler hander = new Myhandler();

	// 定义一个task对象
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
	 * @see java.lang.Runnable#run() 在线程中执行具体的操作
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
	 *            添加activity
	 */
	public static void addActivity(Activity activity) {
		activities.add(activity);
	}

	/**
	 * @param task
	 *            添加task到队列中
	 */
	public static void addTask(Task task) {
		tasks.add(task);
	}

	/**
	 * @param task
	 */
	public void doTask(Task task) {
		// 构建message对象
		Message message = Message.obtain(hander);
		message.what = task.getTaskId();
		switch (task.getTaskId()) {
		case Task.LOGIN:
			message.obj = "登录成功";
			break;
		case Task.AUTH:
			message.obj = "开始";
			break;
		case Task.GETTOKEN:
			String url = AccessAuthUtil.getAuthorizationURL();
			if (url != null) {
				message.obj = url;
			}
			break;
		case Task.GET_ACCESSKON:
			String pin = null;
			// 如果获取到的pin为空，则循环一直获取
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
				// 将集合中的activity转成接口，然后在调用该方法来更新ui
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
		 * @return 根据activity的名字来获取activity
		 */
		private Activity getActivityByName(String name) {
			// TODO Auto-generated method stub
			Activity activity = null;
			// 通过增强的for循环来遍历该集合
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
