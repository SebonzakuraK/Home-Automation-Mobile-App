package com.example.hama;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    public static final int BLUETOOTH_REQ_CODE = 1;
    ImageView ImgVadmin, ImgVguest;
    BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImgVadmin = findViewById(R.id.userAdmin);
        ImgVguest = findViewById(R.id.userGuest);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if(id==R.id.menuBlue){

                    BTconnect();
                    return true;
                }

                return false;
            }
        });

        ImgVguest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {openLogin();}
        });

        ImgVadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,adminPop.class));

            }



        });

           }





//BT CONNECTION
    public void BTconnect() {


        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            Toast.makeText(MainActivity.this, "This device doesn't support Bluetooth",
                    Toast.LENGTH_LONG).show();
        }
        if (!bluetoothAdapter.isEnabled()) {
            Intent blueToothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(blueToothIntent, BLUETOOTH_REQ_CODE);
        }else {
            bluetoothAdapter.disable();

            Toast.makeText(this, "Bluetooth Disabled", Toast.LENGTH_SHORT).show();
        }
            }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Toast.makeText(MainActivity.this, "Bluetooth is ON", Toast.LENGTH_SHORT).show();

        }else
        if(resultCode == RESULT_CANCELED){
            Toast.makeText(MainActivity.this, "Bluetooth operation is cancelled", Toast.LENGTH_SHORT).show();
        }
    }



    //redirect to login on Click
    public void openLogin() {
        Intent intent = new Intent(this,login.class);
        startActivity(intent);
    }
}