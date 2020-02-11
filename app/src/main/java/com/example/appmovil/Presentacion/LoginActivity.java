package com.example.appmovil.Presentacion;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.appmovil.R;

import com.example.appmovil.Dominio.dao.UsuariosRepository;
import com.example.appmovil.Dominio.Usuario;

public class LoginActivity extends AppCompatActivity {
    private Button btn_iniciarSesion;
    private EditText etEmail;
    private EditText etPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicializarElementosGraficos();

        btn_iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                String email=etEmail.getText().toString();

                String pass=etPassword.getText().toString();

                Usuario usuario=new Usuario(email,pass);

                //HANDLER
                Handler miHandler = new Handler(Looper.myLooper()){
                    @Override
                    public void handleMessage(Message msg) {
                        switch (msg.arg1 ){
                            case 0:
                                Log.d("Login","Login OK");

                                Intent i= new Intent(v.getContext(),MenuActivity.class);
                                startActivity(i);

                                break;
                            case 1:
                                Log.d("Login","Error al loguear");
                                break;

                        }
                    }
                };

                UsuariosRepository.getInstance().obtenerUsuario(usuario,miHandler);

            }
        });


    }


    private void inicializarElementosGraficos(){
        this.btn_iniciarSesion=findViewById(R.id.btn_IniciarSesion);
        this.etEmail=findViewById(R.id.etEmail);
        this.etPassword=findViewById(R.id.etPassword);
    }

}
