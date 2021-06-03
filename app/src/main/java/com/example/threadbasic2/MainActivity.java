package com.example.threadbasic2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    Thread wr;
    WorkerThread wt;
    boolean running = true;
    final String TAG = "THREAD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    class WorkerThread extends Thread {
        public void run() {
            int i = 0;
            for (i = 0; i < 20 && running; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                Log.v(TAG, "Thread time=" + i);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        wr = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                for (i = 0; i < 20 && running; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    Log.v(TAG, "Runnable time=" + i);
                }
            }
        });
        wt = new WorkerThread();
        wr.start();
        wt.start();
        Log.v(TAG, "Now I am in onStart");
    }
    @Override
    protected void onStop() {
        super.onStop();
        running = false;
        Log.v(TAG, "Now I am in onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG, "Now I am in onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "Now I am in onResume");
    }
}