package com.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


       BroadcastReceiver receptor = new Receptor();

       IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
       filter.addAction(Intent.ACTION_BATTERY_LOW);
       filter.addAction(Intent.ACTION_POWER_CONNECTED);
       filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
       filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
       filter.addAction(Intent.ACTION_TIME_CHANGED);
       filter.addAction("android.provider.Telephony.SMS_RECEIVED");

       this.registerReceiver(receptor, filter);
    }
}