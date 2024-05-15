package com.example.doma;

import android.content.Intent;
import android.gesture.Gesture;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Home extends AppCompatActivity {

    private GestureDetector gestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        gestureDetector = new GestureDetector(this, new GestureListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event) || super.onTouchEvent(event);
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int DISTANCIA = 1;
        private static final int VELOCIDADE = 5;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float diffX = e2.getX() - e1.getX();
            float diffY = e2.getY() - e1.getY();

            if(Math.abs(diffX) > Math.abs(diffY)) {
                if((Math.abs(diffX) > DISTANCIA) && (Math.abs(velocityX) > VELOCIDADE)){
                    if(diffX > 0) {
                        startActivity(new Intent(Home.this, Configurar.class));
                    } else {
                        startActivity(new Intent(Home.this, Mensagens.class ));
                    }
                }
                return true;
            } else {
                if ((Math.abs(diffY) > DISTANCIA) && (Math.abs(velocityY) > VELOCIDADE)) {
                    if (diffY > 0) {
                        startActivity(new Intent(Home.this, Alertas.class));
                    } else {
                        startActivity(new Intent(Home.this, Lembretes.class));
                    }
                }
                return true;
            }

        }

    }
}

