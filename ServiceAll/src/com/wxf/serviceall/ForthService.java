package com.wxf.serviceall;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * @author Administrator
 *���԰�ͨ���������service��activity��
 *�����service�ķ����������£�
 *1��������Ҫʵ��һ���̳�service���ࡣ
 *2.Ȼ��ʵ�ָ����onbind()��������
 *3.����һ��Binder�����ࡣ�ڸ������д���һ�����еķ��������������ظ�servcie��ʵ����
 *4.Ȼ����service�й���һ�����еķ�����������sercice���á��������ݡ�
 *5.��onbinder�����У����ظ�binder�����ࡣ
 *6.������androidManifest.xml�ļ��н���ע�ᡣ����һ����ʧ�ܡ�
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
	 * ����һ�����еķ�������service��activity�е��á�
	 */
	public  Integer  transferData(){
		return new Integer(5);
	}
	
	/**
	 * @author Administrator
	 *
	 *
	 *����һ��binder�����ࡣ������onbinder�����з���
	 */
	public class LocalBinder extends Binder{
		public ForthService getService(){
			return ForthService.this;
		}
		
		
		@Override
		protected boolean onTransact(int code, Parcel data, Parcel reply,
				int flags) throws RemoteException {
			// TODO Auto-generated method stub
			
//			����Ľ���Ǻ�д����Ⱥ�˳���йص�
			reply.writeInt(5);
			reply.writeString("hell");
			System.out.println("ForthService-receive-->"+data.readInt());
			System.out.println("ForthService---receive->"+data.readString());
			return super.onTransact(code, data, reply, flags);
		}
	}
	

}
