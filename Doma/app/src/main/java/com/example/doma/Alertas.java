package com.example.doma;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class Alertas extends AppCompatActivity implements TTSManager.InitListener{

    private TTSManager ttsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_alertas);
        ttsManager = new TTSManager(this, this);
    }

    public void onInitComplete() {
        ttsManager.speak("Você tem 1 alerta, Este alerta foi Hardcoded para apresentação do trabalho, sem que fosse necessário criar um servidor. Porém em um projeto real, seria desenvolvido um metodo para fazer fetch dos alertas de um servidor da Doma");
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
}