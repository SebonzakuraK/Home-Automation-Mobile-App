package com.example.hama;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ImportantActivity extends AppCompatActivity {

    TextView AltTxt;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifiction);
        //size the pop up window

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout( (int) (width*.8),(int) (height*.6) );

        AltTxt = findViewById(R.id.AltText);
        String NOTIFICATION =  getIntent().getStringExtra("Message");
        NOTIFICATION = NOTIFICATION + "\nSecurity Measures Will take effect in 60s";
        AltTxt.setText(NOTIFICATION);
    }
}
