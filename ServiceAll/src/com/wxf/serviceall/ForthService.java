package com.wxf.serviceall;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * @author Administrator
 *测试绑定通过借口来绑定service和activity。
 *具体绑定service的方法步骤如下：
 *1。首先需要实现一个继承service的类。
 *2.然后实现父类的onbind()方法。，
 *3.创建一个Binder的子类。在该子类中创建一个共有的方法，。用来返回该servcie的实例。
 *4.然后再service中构建一个共有的方法。用来被sercice调用。传输数据。
 *5.在onbinder方法中，返回该binder的子类。
 *6.必须在androidManifest.xml文件中进行注册。否则一定会失败。
 */
public class ForthService extends Service {

	
	private IBinder binder=new LocalBinder();
	public ForthService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return binder;
	}
	
	/**
	 * @return
	 * 
	 * 构建一个共有的方法，供service在activity中调用。
	 */
	public  Integer  transferData(){
		return new Integer(5);
	}
	
	/**
	 * @author Administrator
	 *
	 *
	 *构建一个binder的子类。用来在onbinder方法中返回
	 */
	public class LocalBinder extends Binder{
		public ForthService getService(){
			return ForthService.this;
		}
		
		
		@Override
		protected boolean onTransact(int code, Parcel data, Parcel reply,
				int flags) throws RemoteException {
			// TODO Auto-generated method stub
			
//			输出的结果是和写入的先后顺序有关的
			reply.writeInt(5);
			reply.writeString("hell");
			System.out.println("ForthService-receive-->"+data.readInt());
			System.out.println("ForthService---receive->"+data.readString());
			return super.onTransact(code, data, reply, flags);
		}
	}
	

}
