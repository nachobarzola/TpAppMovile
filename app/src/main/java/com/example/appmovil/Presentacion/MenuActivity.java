package com.example.appmovil.Presentacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.appmovil.R;

public class MenuActivity extends AppCompatActivity {
    private Button btn_crearEvento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        inicializarElementosGraficos();

        btn_crearEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iNuevoEvento=new Intent(v.getContext(),CrearEventoActivity.class);
                startActivity(iNuevoEvento);
            }
        });

    }



    private void inicializarElementosGraficos(){
        btn_crearEvento=findViewById(R.id.btn_crearEvento);


    }



}
