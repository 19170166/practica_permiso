package com.example.permisos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class inicio extends AppCompatActivity {


    TextView pant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        pant=findViewById(R.id.pantalla);

        CountDownTimer cdt=new CountDownTimer(3000,1000) {
            @Override
            public void onTick(long segundos) {
                pant.setText(String.valueOf(segundos/1000));
            }

            @Override
            public void onFinish() {
                Intent i = new Intent(inicio.this,MainActivity.class);;
                startActivity(i);
                finish();
            }
        }.start();

    }

}