package com.example.hama;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class register extends AppCompatActivity {

    EditText edUsername, edPassword, edEmail;
    Button  btSignUp, btDel;
    regDatabase DB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);


        edUsername = findViewById(R.id.regUsername);
        edPassword = findViewById(R.id.regPassword);
        edEmail = findViewById(R.id.regEmail);

        btDel = findViewById(R.id.remUser);
        btSignUp = findViewById(R.id.regSoU);
        DB = new regDatabase(this);


        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name  = edUsername.getText().toString();
                String Email = edEmail.getText().toString();
                String Password = edPassword.getText().toString();

                edPassword.setVisibility(View.VISIBLE);

                if(TextUtils.isEmpty(Name)){

                    edUsername.setError("Required");

                }else if (TextUtils.isEmpty(Email)){
                    edEmail.setError("Required");
                }else if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
                    edEmail.setError("Enter Valid Email");
                }else if(TextUtils.isEmpty(Password)){
                edPassword.setError("Required");
            }else if(Password.length() < 6) {
                edPassword.setError("More than 6 characters!!");




                }else{
                    Boolean RegUser = DB.insertUserdata(Name,Email,Password);
                    if (RegUser == true) {

                        Toast.makeText(getApplicationContext(), "User Register Success", Toast.LENGTH_SHORT).show();



                    }else{
                        Toast.makeText(getApplicationContext(),"Registration Failed",Toast.LENGTH_SHORT).show();

                    }
                }



            }
        });

        btDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edPassword.setVisibility(View.GONE);


                String Name  = edUsername.getText().toString();
                String Email = edEmail.getText().toString();




                if(TextUtils.isEmpty(Name)){

                    edUsername.setError("Required");

                }else if (TextUtils.isEmpty(Email)){
                    edEmail.setError("Required");
                }else if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
                    edEmail.setError("Enter Valid Email");
                }else{
                    Boolean DelUser = DB.deleteUserdata(Name);
                    if (DelUser == true) {

                        Toast.makeText(getApplicationContext(), "User Removed Successfully", Toast.LENGTH_SHORT).show();



                    }else{
                        Toast.makeText(getApplicationContext(),"Removal Failed",Toast.LENGTH_SHORT).show();

                    }
                }


            }
        });

    }



}
