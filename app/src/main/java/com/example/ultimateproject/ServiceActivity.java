package com.example.ultimateproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ultimateproject.Service.StartedService;

public class ServiceActivity extends AppCompatActivity {

    boolean sflag=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        Button sBtn,nBtn,bBtn;
        sBtn=(Button) findViewById(R.id.started_service);
        nBtn=(Button) findViewById(R.id.noti_service);
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
