package com.example.hama;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class dashActivity extends AppCompatActivity {

//living room
    String[] itemslivRoom = {"Tv : ON","Tv : OFF","Lights : ON","Lights : OFF","Curtains : DRAW", "Curtains : CLOSE"};
//Garage
    String [] itemsGarage = {"OPEN DOOR","CLOSE DOOR","Lights : ON","Lights : OFF"};
    //Security Cameras
    String [] itemsCameras = {"NORTH","WEST","EAST","SOUTH"};
    //Security Lights
    String [] itemsLights = {"NORTH","WEST","EAST", "SOUTH"};

    AutoCompleteTextView LV , GD , SC , SL;
    Button Status;
    ArrayAdapter<String> LVadapterItems, GDadapterItems, SCadapterItems, SLadapterItems;
    TextInputLayout TLV, TGD, TSC, TSL;

    roomDev DAB;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        Status = findViewById(R.id.StatusCol);
        Status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent CurStatus = new Intent(dashActivity.this,triggerAlert.class);
                startActivity(CurStatus);
            }
        });


        DAB = new roomDev(this);

//living room devices
        TLV = findViewById(R.id.tLv);
        LV = findViewById(R.id.LivingRoom);
        LVadapterItems = new ArrayAdapter<String>(this,R.layout.living_room_dev,itemslivRoom);
        LV.setAdapter(LVadapterItems);
        LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                //get current time onclick of an item
                DateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm.ss aaa z");
                String TimeActivated = sdf.format(new Date());
                Toast.makeText(getApplicationContext(), "Living Room: "+item+" Time: "+TimeActivated,Toast.LENGTH_SHORT).show();

                //insert into database selected values
               Boolean Confirm = DAB.insertLV(item,TimeActivated);
                if (!Confirm) {
                    Toast.makeText(getApplicationContext(),"0",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_SHORT).show();
                }

            }

        });
           // garage control

            TGD = findViewById(R.id.tGar);
            GD = findViewById(R.id.Garage);
            GDadapterItems = new ArrayAdapter<String>(this,R.layout.living_room_dev,itemsGarage);
        GD.setAdapter(GDadapterItems);
        GD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String item = adapterView.getItemAtPosition(i).toString();
                    //get current time onclick of an item
                    DateFormat sdf = new SimpleDateFormat("yyyy.MM.dd  'at' HH:mm.ss aaa z");
                    String TimeActivated = sdf.format(new Date());
                    Toast.makeText(getApplicationContext(), "Garage: "+item+" Time: "+TimeActivated,Toast.LENGTH_SHORT).show();


                    //insert into database selected values
                    Boolean Confirm = DAB.insertGD(item,TimeActivated);
                    if (!Confirm) {
                        Toast.makeText(getApplicationContext(),"0",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_SHORT).show();
                    }

                }

        });

        //Security Cameras

        TSC = findViewById(R.id.tCameras);
        SC = findViewById(R.id.SecCameras);
        SCadapterItems = new ArrayAdapter<String>(this,R.layout.living_room_dev,itemsCameras);
        SC.setAdapter(SCadapterItems);
        SC.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                //get current time onclick of an item
                DateFormat sdf = new SimpleDateFormat("yyyy.MM.dd  'at' HH:mm.ss aaa z");
                String TimeActivated = sdf.format(new Date());
                Toast.makeText(getApplicationContext(), "Security Cameras: "+item+" Time: "+TimeActivated,Toast.LENGTH_SHORT).show();

                //insert into database selected values
                Boolean Confirm = DAB.insertSC(item,TimeActivated);
                if (!Confirm) {
                    Toast.makeText(getApplicationContext(),"0",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_SHORT).show();
                }

            }

        });

//Security Lights

        TSL = findViewById(R.id.tSLights);
        SL = findViewById(R.id.SecLights);
        SLadapterItems = new ArrayAdapter<String>(this,R.layout.living_room_dev,itemsLights);
        SL.setAdapter(SLadapterItems);
        SL.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                //get current time onclick of an item

                DateFormat sdf = new SimpleDateFormat("yyyy.MM.dd  HH:mm.ss aaa z");
                String TimeActivated = sdf.format(new Date());
                Toast.makeText(getApplicationContext(), "Security Lights: "+item+" Time: "+TimeActivated,Toast.LENGTH_SHORT).show();

                //insert into database selected values
                Boolean Confirm = DAB.insertSL( item,TimeActivated);
                if (!Confirm) {
                    Toast.makeText(getApplicationContext(),"0",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_SHORT).show();
                }
            }

        });


        Status = findViewById(R.id.StatusCol);
        Status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent CurStatus = new Intent(dashActivity.this,triggerAlert.class);
                startActivity(CurStatus);
            }
        });



    }


}

