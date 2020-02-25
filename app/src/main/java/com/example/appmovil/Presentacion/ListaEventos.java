package com.example.appmovil.Presentacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.appmovil.Adapter.EventoRecyclerAdapter;
import com.example.appmovil.Dominio.Evento;
import com.example.appmovil.Dominio.Usuario;
import com.example.appmovil.Dominio.dao.EventosRepository;
import com.example.appmovil.Dominio.dao.UsuariosRepository;
import com.example.appmovil.R;
import com.example.appmovil.Services.MyBroadcastReceiver;

import java.util.ArrayList;
import java.util.List;

public class ListaEventos extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<Evento> eventos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lista_eventos);

        Toolbar toolbar = findViewById(R.id.miToolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //NOTIFICACIONES
        BroadcastReceiver br = new MyBroadcastReceiver();
        IntentFilter filtro = new IntentFilter();
        filtro.addAction(MyBroadcastReceiver.EVENTO_02);
        getApplication().getApplicationContext()
                .registerReceiver(br,filtro);

        //NOTIFICACIONES

        Usuario usuario = UsuariosRepository.getInstance().getUser();

        EventosRepository.getInstance().ObtenerEventos(usuario,miHandler);

        mRecyclerView = findViewById(R.id.rec_view_evento);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager=new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter=new EventoRecyclerAdapter(eventos);

        mRecyclerView.setAdapter(mAdapter);

    }

    private void InicializarInterfaz() {

        Toolbar toolbar = findViewById(R.id.miToolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    Handler miHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.arg1 ){
                case 0:
                    eventos.clear();

                    eventos.addAll( EventosRepository.getInstance().getListaEventos());

                    mAdapter.notifyDataSetChanged();

                    break;
                case 1:

                    Log.d("lista","Ocurrió un problema al obtener los eventos");
                    break;

            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();

        eventos.clear();

        eventos.addAll( EventosRepository.getInstance().getListaEventos());

        mAdapter.notifyDataSetChanged();

    }
}
