package com.imagetotext.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity3 extends AppCompatActivity {

    public AdView howToAdd;
    Button backbtn, howToButton;
    RelativeLayout upload, scan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_3);

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-7595014258577987/5650147542");

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        howToAdd = findViewById(R.id.howTo_Add);
        AdRequest adRequest = new AdRequest.Builder().build();
        howToAdd.loadAd(adRequest);



        backbtn = findViewById(R.id.back_button);
        howToButton = findViewById(R.id.how_to_button);
        upload = findViewById(R.id.upload);
        scan = findViewById(R.id.scan);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        howToButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(upload.getVisibility() == View.VISIBLE){

                    upload.setVisibility(View.INVISIBLE);
                    scan.setVisibility(View.VISIBLE);
                    howToButton.setText(R.string.previous);
                }

                else if(scan.getVisibility() == View.VISIBLE){

                    scan.setVisibility(View.INVISIBLE);
                    upload.setVisibility(View.VISIBLE);
                    howToButton.setText(R.string.next);
                }

            }
        });
    }
}
