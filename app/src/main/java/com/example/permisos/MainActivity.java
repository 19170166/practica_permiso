package com.example.permisos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final static int REQUEST_CODE_ASK_PERMISSIONS = 1;
    private final static int PERMISO_MAP=2;
    Button boton1,boton2,boton3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton1=findViewById(R.id.btn1);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                permisos();
            }
        });

        boton2=findViewById(R.id.btn2);

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int permisomap=ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.ACCESS_COARSE_LOCATION);
                if (permisomap!=PackageManager.PERMISSION_GRANTED){
                    if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},PERMISO_MAP);
                    }
                }
                if (permisomap==PackageManager.PERMISSION_GRANTED){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0")));
                }
            }
        });

    }
    public void permisos(){
        int permisophone= ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CALL_PHONE);
        int permisolocation=ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permisolocation==PackageManager.PERMISSION_DENIED || permisophone==PackageManager.PERMISSION_DENIED){
            Toast.makeText(MainActivity.this,"Permisos necesarios",Toast.LENGTH_SHORT).show();
        }
        if (permisolocation!=PackageManager.PERMISSION_GRANTED || permisophone!=PackageManager.PERMISSION_GRANTED){
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CALL_PHONE},REQUEST_CODE_ASK_PERMISSIONS);
            }
        }

        if (permisolocation==PackageManager.PERMISSION_GRANTED){
            Toast.makeText(MainActivity.this,"permiso 1 concedido",Toast.LENGTH_SHORT).show();
        }
        if (permisophone==PackageManager.PERMISSION_GRANTED){
            Toast.makeText(MainActivity.this,"permiso 2 concedido",Toast.LENGTH_SHORT).show();

        }

        //telefono();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode==REQUEST_CODE_ASK_PERMISSIONS){
            if (grantResults.length>=1){
                for (int i=0;i<grantResults.length;i++){
                    if (grantResults[i]==PackageManager.PERMISSION_GRANTED) {
                        //APLICACION
                    }
                }
            }
        }
    }

    public void telefono(){
        startActivity(new Intent(Intent.ACTION_DIAL));
    }
    public void llamar(){
        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:8717435577")));
    }

}