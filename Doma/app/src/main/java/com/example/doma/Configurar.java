package com.example.doma;

import android.content.Context;
import android.content.Intent;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.Settings;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Configurar extends AppCompatActivity {
    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar);
        AudioSetup();
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
        finish();
    }

    void AudioSetup() {
        if (audioManager == null) {
            audioManager = (AudioManager) getSystemService((Context.AUDIO_SERVICE));
        }
        AudioHelper audioHelper = new AudioHelper(this);
        boolean isBluetooth = audioHelper.audioOutputAvailable(AudioDeviceInfo.TYPE_BLUETOOTH_A2DP);
        if (isBluetooth) {
            audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
            audioManager.setBluetoothScoOn(true);
            audioManager.startBluetoothSco();
        } else {
            boolean isSpeaker = audioHelper.audioOutputAvailable(AudioDeviceInfo.TYPE_BUILTIN_SPEAKER);
            if(isSpeaker) {
                audioManager.setMode(AudioManager.MODE_NORMAL);
            } else {
                Intent intent = new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
                startActivity(intent);
            }
        }
    }
}