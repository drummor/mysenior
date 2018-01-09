package com.weshape3d.aidldemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.List;

public class BinderPoolService extends Service {



    private Binder mBinderPool = new BinderPool.BinderPoolImpl();
    public BinderPoolService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
       return mBinderPool;
    }
}
