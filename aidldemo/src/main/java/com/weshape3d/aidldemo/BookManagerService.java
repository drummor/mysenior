package com.weshape3d.aidldemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 运行在服务端的 一个Service
 */
public class BookManagerService extends Service {
    /**
     * 支持并发读写
     */
    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<>();
    //private CopyOnWriteArrayList<IOnNewBookArrivedListener> listenerList = new CopyOnWriteArrayList<IOnNewBookArrivedListener>();
    private RemoteCallbackList<IOnNewBookArrivedListener> listenerList = new RemoteCallbackList<IOnNewBookArrivedListener>();
    private AtomicBoolean destoryed = new AtomicBoolean(false);

    /**
     * 充当服务类 实现具体的方法  持有着
     */
    private Binder mBinder = new IBookManager.Stub(){
        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBookList;
        }
        @Override
        public void addBook(Book book) throws RemoteException {
                mBookList.add(book);
        }

        @Override
        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {
           listenerList.register(listener);
        }

        @Override
        public void unregisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
           listenerList.unregister(listener);
        }
    };




    @Override
    public void onCreate() {
        super.onCreate();
        mBookList.add(new Book(1,"Android"));
        mBookList.add(new Book(2,"iOS"));
        new Thread(new AddBookWork()).start();//开始加书
    }

    public BookManagerService() {
    }
    @Override
    public IBinder onBind(Intent intent) {
     return mBinder;
    }

    private void onNewBookArr(Book newBook) throws RemoteException {

        //Log.d("drummor","去掉用远程所处的线程"+Thread.currentThread().getName()+"监听的个数:"+listenerList.beginBroadcast());
        int N = listenerList.beginBroadcast();
        for(int i = 0;i<N;i++){
            IOnNewBookArrivedListener listener = listenerList.getBroadcastItem(i);
            if(listener != null){
                listener.onNewBookArrived(newBook);
            }
        }
       listenerList.finishBroadcast();
    }

    class AddBookWork implements Runnable{

        @Override
        public void run() {
            while (!destoryed.get()) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Book book = new Book(mBookList.size() + 1, "new Book" + mBookList.size() + 1);
                try {
                    onNewBookArr(book);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Override
    public void onDestroy() {
        destoryed.set(true);
        super.onDestroy();
    }
}
