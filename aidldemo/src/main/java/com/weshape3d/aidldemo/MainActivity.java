package com.weshape3d.aidldemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ReflectDemo.ReflectDemo;
import ReflectDemo.Student;


/**
 * 1、AIDL创建过程 注意事项。
 * 2、服务端
 * 3、客户端
 * 4、onTransact transact
 * 5、连接失败监听
 * 6、绑定监听
 * 7、binder池
 * 8、Binder线程池
 */
public class MainActivity extends AppCompatActivity {
    private IBookManager bookManager = null;
    private ServiceConnection mConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("drummor","连接的地方线程："+Thread.currentThread().getName());
             bookManager = IBookManager.Stub.asInterface(service);
            try {
                bookManager.registerListener(onNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();

            }
            try {
                List<Book> books = bookManager.getBookList();
                Log.d("drummor","获得远程list"+books.size()+"::::"+books.toString());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private IOnNewBookArrivedListener onNewBookArrivedListener  = new IOnNewBookArrivedListener.Stub() {

        @Override
        public void onNewBookArrived(Book newBook) throws RemoteException {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                Log.d("drummor","添加了一本新书"+newBook.bookName
                        +"当前线程:"+Thread.currentThread().getName());
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Student student = new Student("你好","123");
        Object object = ReflectDemo.getProperty(student,"name");
        Log.d("drummor","获取student 对象的那么属性值:"+object.toString());

        String id = (String) ReflectDemo.getStaticPropertyValue(Student.class,"ID");
        Log.d("drummor","获取student 静态的那么属性值:"+id.toString());
        ReflectDemo.invokeMethod(student,"print",new Object[]{});
        Intent intent  = new Intent(this,BookManagerService.class);
        bindService(intent,mConn, Context.BIND_AUTO_CREATE);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConn);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(bookManager!=null){
                try {
                    bookManager.unregisterListener(onNewBookArrivedListener);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            Map map = new HashMap();

            return true;
        }
        return super.onKeyDown(keyCode, event);
     }

    public void doWork(){
        BinderPool binderPool = BinderPool.getInstance(this);
        binderPool.queryBinder(BinderPool.BINDER_COMPUTE);
    }
}
