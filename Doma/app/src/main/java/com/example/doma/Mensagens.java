package com.example.doma;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Mensagens extends AppCompatActivity implements TTSManager.InitListener {
    private TTSManager ttsManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mensagens);
        ttsManager = new TTSManager(this, this);
    }

    public void onInitComplete() {
        ttsManager.speak("Você tem 0 mensageNs. ");
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
}