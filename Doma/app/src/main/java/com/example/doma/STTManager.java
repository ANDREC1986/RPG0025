package com.example.doma;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import java.util.ArrayList;

public class STTManager {

    private SpeechRecognizer mSpeechRecognizer;
    private Context context;

    public STTManager(Context context) {
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.RECORD_AUDIO}, 1);
        }
        this.context = context;
        Initialize();
    }

    private void Initialize() {
        if (SpeechRecognizer.isRecognitionAvailable(context)) {
            mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(context);
            mSpeechRecognizer.setRecognitionListener(new Recognition());
            Toast.makeText(context, "Reconhecimento de fala disponível", Toast.LENGTH_SHORT).show();
            Log.i("Sucesso", "Iniciado com Sucesso");
        } else {
            Log.e("Falhou", "Não foi possivel usar o reconhecimento de voz");
            Toast.makeText(context, "Reconhecimento de fala não disponível neste dispositivo", Toast.LENGTH_SHORT).show();
        }
    }

    public void Listen() {
        if (mSpeechRecognizer != null) {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "pt-BR");
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Fale");
            mSpeechRecognizer.startListening(intent);
        } else {
            // Tratar caso o SpeechRecognizer não tenha sido inicializado corretamente
        }
    }

    private class Recognition implements RecognitionListener {

        @Override
        public void onReadyForSpeech(Bundle params) {
        }

        @Override
        public void onBeginningOfSpeech() {
        }

        @Override
        public void onRmsChanged(float rmsdB) {
        }

        @Override
        public void onBufferReceived(byte[] buffer) {
        }

        @Override
        public void onEndOfSpeech() {
        }

        @Override
        public void onError(int error) {
        }

        @Override
        public void onResults(Bundle results) {
            ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            if (matches != null && matches.size() > 0) {
                String text = matches.get(0);
            }
        }
        @Override
        public void onPartialResults(Bundle partialResults) {}

        @Override
        public void onEvent(int eventType, Bundle params) {}
    }
}