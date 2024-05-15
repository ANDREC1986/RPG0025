package com.example.doma;

import android.content.Context;
import android.provider.Settings;
import android.content.Intent;
import android.os.Bundle;
import android.media.AudioManager;
import android.media.AudioDeviceInfo;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
        finish();
    }


}