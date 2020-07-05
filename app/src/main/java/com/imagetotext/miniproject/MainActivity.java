package com.imagetotext.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread background = new Thread() {
            public void run() {
                try {
                    sleep(2500);

                    Intent i=new Intent(getBaseContext(), MainActivity2.class);
                    startActivity(i);

                    finish();
                } catch (Exception ignored) {
                }
            }
        };
        background.start();


    }
}
