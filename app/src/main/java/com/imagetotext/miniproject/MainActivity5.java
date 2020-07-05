package com.imagetotext.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity5 extends AppCompatActivity {

    private AdView mAdView;
    ImageView AayushGmail, AayushInstagram, AayushTwitter, AjinkyaGmail, AjinkyaInstagram, AjinkyaTwitter;
    TextView AayushGmailAdd, AayushInstagramAdd, AayushTwitterAdd, AjinkyaGmailAdd, AjinkyaInstagramAdd, AjinkyaTwitterAdd;
    String selectedAdd = "";
    Button backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_5);

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-7595014258577987/6364463412");

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);




        AayushGmail = findViewById(R.id.name_1_gmail_logo);
        AayushGmailAdd = findViewById(R.id.name_1_gmail);

        AayushInstagram = findViewById(R.id.name_1_instagram_logo);
        AayushInstagramAdd = findViewById(R.id.name_1_instagram);

        AayushTwitter = findViewById(R.id.name_1_twitter_logo);
        AayushTwitterAdd = findViewById(R.id.name_1_twitter);

        AjinkyaGmail = findViewById(R.id.name_2_gmail_logo);
        AjinkyaGmailAdd = findViewById(R.id.name_2_gmail);

        AjinkyaInstagram = findViewById(R.id.name_2_instagram_logo);
        AjinkyaInstagramAdd = findViewById(R.id.name_2_instagram);

        AjinkyaTwitter = findViewById(R.id.name_2_twitter_logo);
        AjinkyaTwitterAdd = findViewById(R.id.name_2_twitter);

        backbtn = findViewById(R.id.back_button);

        AayushGmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAdd = AayushGmailAdd.getText().toString();
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("EditText", selectedAdd);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(MainActivity5.this, "Address Copied", Toast.LENGTH_SHORT).show();
            }
        });

        AayushInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAdd = AayushInstagramAdd.getText().toString();
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("EditText", selectedAdd);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(MainActivity5.this, "Address Copied", Toast.LENGTH_SHORT).show();
            }
        });

        AayushTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAdd = AayushTwitterAdd.getText().toString();
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("EditText", selectedAdd);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(MainActivity5.this, "Address Copied", Toast.LENGTH_SHORT).show();
            }
        });

        AjinkyaGmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAdd = AjinkyaGmailAdd.getText().toString();
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("EditText", selectedAdd);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(MainActivity5.this, "Address Copied", Toast.LENGTH_SHORT).show();
            }
        });

        AjinkyaInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAdd = AjinkyaInstagramAdd.getText().toString();
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("EditText", selectedAdd);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(MainActivity5.this, "Address Copied", Toast.LENGTH_SHORT).show();
            }
        });

        AjinkyaTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAdd = AjinkyaTwitterAdd.getText().toString();
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("EditText", selectedAdd);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(MainActivity5.this, "Address Copied", Toast.LENGTH_SHORT).show();
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity5.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}
