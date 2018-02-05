package com.example.vcfr67.aidlclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ICommon common;
    private Context context;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            common = ICommon.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnCalc).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    Toast.makeText(context, Integer.toString(common.cal(1, 2)), Toast.LENGTH_LONG).show();
                }
                catch (RemoteException e)
                {
                    Log.e("REmote error", "Error");
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Intent i = new Intent("com.example.vcfr67.aidl.service");
        bindService(i, conn, BIND_AUTO_CREATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        unbindService(conn);
    }
}
