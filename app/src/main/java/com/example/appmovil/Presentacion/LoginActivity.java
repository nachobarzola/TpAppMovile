package com.example.appmovil.Presentacion;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.view.View;
import android.widget.Button;

import com.example.appmovil.R;

public class LoginActivity extends AppCompatActivity {
    private Button btn_iniciarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicializarElementosGraficos();

        btn_iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i= new Intent(v.getContext(),MenuActivity.class);
                startActivity(i);
            }
        });


    }


    private void inicializarElementosGraficos(){
        this.btn_iniciarSesion=findViewById(R.id.btn_IniciarSesion);
    }


}
