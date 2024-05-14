package com.example.doma;

import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;
import java.util.Locale;

public class TTSManager implements TextToSpeech.OnInitListener {

    private TextToSpeech textToSpeech;
    private boolean isInitialized = false;
    private final Context context;
    private InitListener initListener;

    public TTSManager(Context context, InitListener initListener) {
        this.context = context;
        this.initListener = initListener;
        textToSpeech = new TextToSpeech(context, this);
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = textToSpeech.setLanguage(new Locale("pt", "BR"));
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(context, "A lingua não é suportada", Toast.LENGTH_SHORT).show();
            } else {
                isInitialized = true;
                if(initListener != null) {
                    initListener.onInitComplete();
                }

            }
        } else {
            Toast.makeText(context, "Falha na inicialização do TTS", Toast.LENGTH_SHORT).show();
        }
    }

    public void speak(String text) {
        if (isInitialized) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH,null, null);
            } else {
                textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            }

        } else {
            Toast.makeText(context, "TTS não inicializado ainda", Toast.LENGTH_SHORT).show();
        }
    }

    public void shutdown() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
            textToSpeech = null;
        }
    }

    public interface InitListener {
        void onInitComplete();
    }
}