package com.weshape3d.aidldemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by WESHAPE-DEV02 on 2017/10/26.
 *
 * Binder 理解
 */

public class BinderPool {

    public static final int BINDER_NODE = -1;
    public static final int BINDER_COMPUTE = 0;
    public static final int BINDER_SECURITY_CENTER = 1;
    private static IBinderPool mBinderpool;

    private Context mContext;
    private CountDownLatch mConnectBinderPoolCountDownlatch;
    public static  BinderPool getInstance(Context context){
        return  new BinderPool(context);
    }
    private BinderPool(Context context){
        this.mContext = context.getApplicationContext();
        connectBinderPoolService();
    }

    private synchronized void connectBinderPoolService(){
        mConnectBinderPoolCountDownlatch = new CountDownLatch(1);
        Intent intent = new Intent(mContext,BinderPoolService.class);
        mContext.bindService(intent,mBinderPoolConnection,Context.BIND_AUTO_CREATE);

        try {
            mConnectBinderPoolCountDownlatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private ServiceConnection mBinderPoolConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            /**
             * 将服务端的Binder对象 转换成AIDL接口类型对象
             * 如果服务进程和当前进程属于同一个进程 那个返回的就是Stub本身
             * 否则返回的是系统封装后的stub.proxy
             */
            mBinderpool = IBinderPool.Stub.asInterface(service);

            try {
                mBinderpool.asBinder().linkToDeath((IBinder.DeathRecipient) mConnectBinderPoolCountDownlatch,0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    /**
     * 实现
     */
    public static class BinderPoolImpl extends IBinderPool.Stub{
        public BinderPoolImpl(){
            super();
        }
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public IBinder queryBinder(int binderCode) throws RemoteException {
            IBinder binder = null;
            switch (binderCode){
                case BINDER_COMPUTE:
                    binder = new IBookManager.Stub() {
                        @Override
                        public List<Book> getBookList() throws RemoteException {
                            return null;
                        }

                        @Override
                        public void addBook(Book book) throws RemoteException {

                        }

                        @Override
                        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {

                        }

                        @Override
                        public void unregisterListener(IOnNewBookArrivedListener listener) throws RemoteException {

                        }
                    };
                    break;
                case BINDER_NODE:
                    break;
                default:
                    break;
            }
            return binder;
        }
    }

    public IBinder queryBinder(int binderCode){
        IBinder binder = null;
        if(mBinderpool!=null){
            try {
                binder = mBinderpool.queryBinder(binderCode);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }
        return binder;
    }
}
