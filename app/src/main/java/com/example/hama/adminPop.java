package com.example.hama;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class adminPop extends Activity {

    Button Ok , Cancel;
    EditText admName, admPass;
    int counter = 3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminpop);

        Ok = findViewById(R.id.Ok);
        Cancel = findViewById(R.id.cancel_button);
        admName = findViewById(R.id.admName);
        admPass = findViewById(R.id.admPass);
        //size the pop up window

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout( (int) (width*.8),(int) (height*.6) );



        Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String aName = admName.getText().toString();
                String aPass = admPass.getText().toString();
      //verify default password
                if(aName.equals("Admin123") && aPass.equals("P@ssw0rd")){
                    Toast.makeText(adminPop.this, "Redirecting...", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(adminPop.this,register.class));
                }else{
                    Toast.makeText(adminPop.this, "Invalid Name or Password", Toast.LENGTH_SHORT).show();
//disable ok button after 3 trials
                    counter--;
                    Toast.makeText(adminPop.this, "Attempts Remaining: "+counter, Toast.LENGTH_SHORT).show();

                    if (counter == 0) {
                        Ok.setEnabled(false);
                    }
                }


            }
        });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                moveTaskToBack(true);

            }
        });


    }
}
