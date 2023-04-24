package com.example.hama;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {

    EditText UsrNm, Pwd;
    TextView Attempts;
    Button SiN;
    int counter = 3;

    regDatabase DB;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        UsrNm = findViewById(R.id.username);
        Pwd = findViewById(R.id.password);
        Attempts = findViewById(R.id.Counter);
        SiN = findViewById(R.id.SiN);
        DB = new regDatabase(this);


            SiN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                //check whether username and password fields are empty.
                  String Username = UsrNm.getText().toString();
                  String Passwd = Pwd.getText().toString();

                if (TextUtils.isEmpty(Username)) {
                    UsrNm.setError("Username Is Required!!");
                }
                else if (TextUtils.isEmpty(Passwd)) {
                        Pwd.setError("Password Is Required");

                    }else if (Passwd.length() < 6) {
                            Pwd.setError("Should not be less than 6 characters");
                        }else {

                    //verify username and password.


                    Boolean res = DB.getPassword(Username,Passwd);

                    if (res) {
                        Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();

                        //open Dashboard on validation.

                        openDashboard();
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid Login", Toast.LENGTH_SHORT).show();

                        Attempts.setVisibility(view.VISIBLE);
                        Attempts.setBackgroundColor(android.R.color.darker_gray);
                        counter--;
                        Attempts.setText(Integer.toString(counter));

                        if (counter == 0) {
                            SiN.setEnabled(false);
                        }
                    }

                }
            }
        });

    }



    public void openDashboard() {
        Intent intent = new Intent(login.this, dashActivity.class);
        startActivity(intent);
    }


}
