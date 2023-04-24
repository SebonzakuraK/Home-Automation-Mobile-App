package com.example.hama;

import static com.example.hama.R.color.colorAlert;
import static com.example.hama.R.id;
import static com.example.hama.R.layout;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.CheckBox;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class triggerAlert extends AppCompatActivity {
private static  final  String CHANNEL_ID;

    static {
        CHANNEL_ID = "MyChannel";
    }
private static final int NOTIFICATION_ID=100;
    CheckBox fireAlert, moveAlert,netAlert,powAlert;
    String notify;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(layout.triggeralert);

        fireAlert = findViewById(id.fire);
        moveAlert = findViewById(id.move);
        powAlert = findViewById(id.power);
        netAlert = findViewById(id.net);


        fireAlert.setOnClickListener(v -> {
            fireAlert.setTextColor(getResources().getColor(colorAlert));
            notify = "Fire Alert";
            alert();
        });

         moveAlert.setOnClickListener(view ->{
             moveAlert.setTextColor(getResources().getColor(colorAlert));
             notify = "Movement Censor";
             alert();
             } );

        powAlert.setOnClickListener(view -> {
            powAlert.setTextColor(getResources().getColor(colorAlert));
            notify = "Power Cut Alert";
            alert();
            });

        netAlert.setOnClickListener(view -> {
            netAlert.setTextColor(getResources().getColor(colorAlert));
            notify = "Network Cut Alert";
            alert();
        });




    }



    private void alert() {
        if(!netAlert.isChecked()){netAlert.setTextColor(getResources().getColor(R.color.colorPrimary));}
        if (!powAlert.isChecked()){powAlert.setTextColor(getResources().getColor(R.color.colorPrimary));}
        if (!moveAlert.isChecked()){moveAlert.setTextColor(getResources().getColor(R.color.colorPrimary));}
        if(!fireAlert.isChecked()){fireAlert.setTextColor(getResources().getColor(R.color.colorPrimary));}

        if(fireAlert.isChecked() || moveAlert.isChecked() || netAlert.isChecked() || powAlert.isChecked()) {
            NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            Notification notification;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                notification = new Notification.Builder(this)
                        .setSmallIcon(R.drawable.ic_alert)
                        .setContentTitle("ALERT! ALERT!")
                        .setContentText(notify + " Has Been Triggered")
                        .setChannelId(CHANNEL_ID)
                        .setAutoCancel(true)
                        .build();
                nm.createNotificationChannel(new NotificationChannel(CHANNEL_ID, "Alert Channel", NotificationManager.IMPORTANCE_HIGH));
            } else {
                notification = new Notification.Builder(this)
                        .setSmallIcon(R.drawable.ic_alert)
                        .setContentTitle("ALERT! ALERT!")
                        .setContentText(notify + " Has Been Triggered")
                        .setAutoCancel(true)
                        .build();
            }
            nm.notify(NOTIFICATION_ID, notification);
            Intent AlertText = new Intent(triggerAlert.this, ImportantActivity.class)
            .putExtra("Message",notify);
            startActivity(AlertText);
        }
}


}
