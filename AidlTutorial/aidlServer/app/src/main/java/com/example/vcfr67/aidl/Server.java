package com.example.vcfr67.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class Server extends Service {
    private final MyImpl calcObject = new MyImpl();
    public Server() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return calcObject;
    }

    public class MyImpl extends ICommon.Stub
    {

        @Override
        public int cal(int num1, int num2) throws RemoteException {
            return num1 + num2;
        }
    }
}
