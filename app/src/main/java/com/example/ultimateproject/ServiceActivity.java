package com.example.ultimateproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ultimateproject.Service.BoundService;
import com.example.ultimateproject.Service.ForegroundService;
import com.example.ultimateproject.Service.StartedService;

public class ServiceActivity extends AppCompatActivity {

    boolean sflag=true;

    BoundService mBoundService;
    boolean mServiceBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);


        Button sBtn,nStartBtn,bStartBtn,bStopBtn,nStopBtn;
        sBtn=(Button) findViewById(R.id.started_service);
        nStartBtn=(Button) findViewById(R.id.noti_service_start);
        nStopBtn=(Button) findViewById(R.id.noti_service_stop);
        bStartBtn=(Button) findViewById(R.id.bound_service_start);
        bStopBtn=(Button) findViewById(R.id.bound_service_stop);

        final TextView timestampText = (TextView) findViewById(R.id.timestamp_text);

        sBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sflag) {
                    startStartedService();
                    sflag=false;
                }
                else {
                    sflag=true;
                    stopStartedService();
                }

            }
        });

        nStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startService(new Intent(getApplicationContext(), MyService.class));
                notificationStartService();
            }
        });

        nStopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                stopService(new Intent(getApplicationContext(), MyService.class));
                notificationStopService();
            }
        });

        final ServiceConnection mServiceConnection = new ServiceConnection() {

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mServiceBound = false;
            }

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {


                BoundService.MyBinder myBinder = (BoundService.MyBinder) service;
                mBoundService = myBinder.getService();
                mServiceBound = true;
            }
        };

        bStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mServiceBound) {
                    Intent intent = new Intent(getApplicationContext(), BoundService.class);
                    startService(intent);
                    bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);

                    timestampText.setText(mBoundService.getTimestamp());
                }
            }
        });



        bStopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mServiceBound) {
                    unbindService(mServiceConnection);
                    mServiceBound = false;
                }
                Intent intent = new Intent(ServiceActivity.this,
                        BoundService.class);
                stopService(intent);

                if (mServiceBound) {
                    unbindService(mServiceConnection);
                    mServiceBound = false;
                }
            }
        });


    }

    public void notificationStartService() {
        Intent serviceIntent = new Intent(this, ForegroundService.class);
        serviceIntent.putExtra("inputExtra", "Foreground Service Example in Android");

        ContextCompat.startForegroundService(this, serviceIntent);
    }

    public void notificationStopService() {
        Intent serviceIntent = new Intent(this, ForegroundService.class);
        stopService(serviceIntent);
    }


    public void startStartedService() {
        // Construct our Intent specifying the Service
        Intent i = new Intent(this, StartedService.class);
//        // Add extras to the bundle
//        i.putExtra("foo", "bar");
//        // Start the service
        startService(i);
    }

    public void stopStartedService(){
        // Create the same Explicit Intent
        Intent intent = new Intent(this, StartedService.class);
// Stop the Service
        stopService(intent);
    }



//    @Override
//    protected void onStart() {
//        super.onStart();
//        Intent intent = new Intent(this, BoundService.class);
//        startService(intent);
//        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
//    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        if (mServiceBound) {
//            unbindService(mServiceConnection);
//            mServiceBound = false;
//        }
//    }

}
