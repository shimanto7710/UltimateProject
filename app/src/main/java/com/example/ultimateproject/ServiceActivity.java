package com.example.ultimateproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ultimateproject.Service.ForegroundService;
import com.example.ultimateproject.Service.StartedService;

public class ServiceActivity extends AppCompatActivity {

    boolean sflag=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        Button sBtn,nStartBtn,bBtn,nStopBtn;
        sBtn=(Button) findViewById(R.id.started_service);
        nStartBtn=(Button) findViewById(R.id.noti_service_start);
        nStopBtn=(Button) findViewById(R.id.noti_service_stop);
        bBtn=(Button) findViewById(R.id.bound_service);

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
                notificationStartService();
            }
        });

        nStopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificationStopService();
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

}
