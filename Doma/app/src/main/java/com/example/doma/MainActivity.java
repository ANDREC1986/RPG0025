package com.example.doma;

import android.content.Context;
import android.provider.Settings;
import android.content.Intent;
import android.os.Bundle;
import android.media.AudioManager;
import android.media.AudioDeviceInfo;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements TTSManager.InitListener {
    private AudioManager audioManager;
    private TTSManager ttsManager;
    private STTManager sttManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        ttsManager = new TTSManager(this, this);
        sttManager = new STTManager(this);
        sttManager.Listen();
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onInitComplete() {
        AudioSetup();
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