package com.example.ultimateproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {
    Boolean finishFlag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        splashWithBackgroundThread();

        finishFlag=false;

        // background thread
//        new BackgroundProcess(this).execute();
    }

    /**
     * run in background but the UI can not be changed from it
     */
    private void splashWithBackgroundThread() {
        /****** Create Thread that will sleep for 5 seconds****/
        Thread background = new Thread() {
            public void run() {
                try {
                    // Thread will sleep for 2 seconds
                    sleep(1 * 1000);

                    // After 5 seconds redirect to another intent
                    Intent i = new Intent(getBaseContext(), OnBoard.class);
                    startActivity(i);

                    //Remove activity
                    finish();
                } catch (Exception e) {
                }
            }
        };
        // start thread
        background.start();

    }

    /**
     * run in background but UI can be change from this
     */
    private void splashWithoutBackgroundThread(){
        Handler handler;
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }

    /**
     * Background thread to do some background work
     * it will not hamper UI
     * even it works fine if the current activity is destroyed
     */
    public class BackgroundProcess extends AsyncTask<String, Boolean, Void> {

        Context context;

        public BackgroundProcess(Context context) {
            this.context = context;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected Void doInBackground(String... params) {

//            myDbHelper = new DatabaseHelper(getApplicationContext());
//            initializeDatabase();

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Log.d("dipbanik","complete");
//            Toast.makeText(getApplicationContext(),"Database Copied",Toast.LENGTH_SHORT).show();
            finishFlag=true;
//            btnNext.setVisibility();
        }

    }


}
