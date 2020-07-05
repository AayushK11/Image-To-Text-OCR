package com.imagetotext.miniproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

import java.io.IOException;



public class MainActivity2 extends AppCompatActivity {


    ImageView imageView;
    Button chooseImagebtn, scanNowbtn, copytextbtn;
    ScrollView scrollView;
    TextView textguide, detecttextguide, outputText;
    private final int PICK_IMAGE = 100;
    Uri imageUri;
    Bitmap bitmap;
    FirebaseVisionImage fbvImage;
    public AdView ads;

    @Override
    //On Starting
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-7595014258577987/7294401704");

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        ads = findViewById(R.id.ads);
        AdRequest adRequest = new AdRequest.Builder().build();
        ads.loadAd(adRequest);

        //Find Elements
        imageView = findViewById(R.id.imageView);
        chooseImagebtn = findViewById(R.id.chooseImageButton);
        textguide = findViewById(R.id.text_guide);
        detecttextguide = findViewById(R.id.detectTextGuide);
        scrollView = findViewById(R.id.ScrollView);
        scanNowbtn = findViewById(R.id.scanNow);
        outputText = findViewById(R.id.outputText);
        copytextbtn = findViewById(R.id.copyButton);

        //OnClick Listener for Choose Image Button
        chooseImagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detecttextguide.setText(R.string.text_guide_3);    //Change Text
                scrollView.setVisibility(View.INVISIBLE);          //Make the Next Text Visible
                OpenGallery();                                     //Function Call
            }
        });

        //OnCLick Listener for Scan Now Button
        scanNowbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (detecttextguide.getVisibility() == View.INVISIBLE) {        //If Invisible means the image has not been selected hence error message
                    Toast toast = Toast.makeText(getApplicationContext(), "Please choose an image first", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    detecttextguide.setText(R.string.text_guide_4);             //Change Text
                    analyse();                                                  //Function Call
                }
            }
        });


    }

    //If Menu Button is clicked
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.extras_menu, menu);
        return true;
    }

    //If Option Is selected from Menu
    public boolean onOptionsItemSelected(MenuItem item){

        if(item.getItemId() == R.id.HowTo){
            Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
            startActivity(intent);
        }

        else if(item.getItemId() == R.id.AboutUs){
            Intent intent = new Intent(MainActivity2.this, MainActivity4.class);
            startActivity(intent);
        }

        else if(item.getItemId() == R.id.ContactUs){
            Intent intent = new Intent(MainActivity2.this, MainActivity5.class);
            startActivity(intent);
        }
        return true;
    }

    //If Choose Image is Clicked
    public void OpenGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);       //Function Call with param as the Image and Code
    }

    @Override
    //When the image is uploaded
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {                             //Check if Result is ok and code matches
                imageUri = data.getData();                                                          //Get the URI data
                imageView.setImageURI(imageUri);                                                    //Show the image
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);    //Convert to Bitmap
                textguide.setText(R.string.text_guide_2);                                           //Change Text
                detecttextguide.setVisibility(View.VISIBLE);                                        //Change Visibility of text
                scanNowbtn.setVisibility(View.VISIBLE);                                             //Make the Scan Now Button visible
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    //When Scan Now is Clicked
    public void analyse() {
        fbvImage = FirebaseVisionImage.fromBitmap(bitmap);                                                     //Call FBVision
        FirebaseVisionTextRecognizer detector = FirebaseVision.getInstance().getOnDeviceTextRecognizer();      //Start Recognizing

        Task<FirebaseVisionText> result = detector.processImage(fbvImage)
                .addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {                            //If Successful, Add Listener
                    @Override
                    public void onSuccess(FirebaseVisionText firebaseVisionText) {
                        process(firebaseVisionText);                                                           //Function Call with param as the op text
                    }
                })
                .addOnFailureListener(new OnFailureListener() {                                                //If Failure, add Listener
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();        //Tell the user
                    }
                });
    }

    //To Decode Text and copy it
    public void process(FirebaseVisionText text){

        scrollView.setVisibility(View.VISIBLE);                                 //Set Scroll View as visible
        outputText.setVisibility(View.VISIBLE);                                 //Set The output text as visible
        copytextbtn.setVisibility(View.VISIBLE);                                //Set the Copy button as visible

        outputText.setText(null);                                               //Clear the area

        if(text.getTextBlocks().size() == 0){                                   //If Text size is 0
            outputText.setText(R.string.no_text);                               //Output No Text
        }
        for(FirebaseVisionText.TextBlock block : text.getTextBlocks()){         //Get Each Block
            outputText.append(block.getText());                                 //Print the block
            outputText.append("\n\n");                                          //Line Break
        }

        //OnClick Listener for Copy Text Button
        copytextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE); //Call Clipboard Service
                ClipData clip = ClipData.newPlainText("EditText", outputText.getText().toString());    //Convert text into plain text
                clipboard.setPrimaryClip(clip);                                                              //Set it in Clipboard

                Toast.makeText(MainActivity2.this, "Text Copied", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
