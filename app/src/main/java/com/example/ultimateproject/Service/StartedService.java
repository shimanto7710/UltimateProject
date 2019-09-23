package com.example.ultimateproject.Service;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class StartedService extends IntentService {
    Handler mHandler = new Handler();

    public StartedService() {
        super("startedService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        // Normally we would do some work here, like download a file.
        // For our sample, we just sleep for 5 seconds.
        try {
            Log.d("service","start");
//            for (int i=0;i<=100000;i++){
//                for(int j=0;j<100000;j++){
//                    //
//                }
//            }
            Thread.sleep(5000);
            Log.d("service","stop");
        } catch (InterruptedException e) {
            // Restore interrupt status.
            Thread.currentThread().interrupt();
        }


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy method.", Toast.LENGTH_LONG).show();
    }
}
