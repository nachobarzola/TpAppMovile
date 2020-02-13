package com.example.appmovil.Presentacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.example.appmovil.Adapter.InvitacionRecyclerAdapter;
import com.example.appmovil.Dominio.Invitacion;
import com.example.appmovil.Dominio.Usuario;
import com.example.appmovil.Dominio.dao.InvitacionesRepository;
import com.example.appmovil.Dominio.dao.UsuariosRepository;
import com.example.appmovil.R;

import java.util.ArrayList;
import java.util.List;

public class MisInvitacionesActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Invitacion> invitacionList= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_invitaciones);

        Toolbar toolbar = findViewById(R.id.miToolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Usuario usuario = UsuariosRepository.getInstance().getUser();

        InvitacionesRepository.getInstance().Obtener(usuario,miHandler);

        mRecyclerView = findViewById(R.id.rec_view_misInvitaciones);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager=new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter=new InvitacionRecyclerAdapter(invitacionList);

        mRecyclerView.setAdapter(mAdapter);
    }

    Handler miHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.arg1 ){
                case 0:
                    invitacionList.addAll( InvitacionesRepository.getInstance().getListaInvitaciones());

                    Log.d("misInvitaciones", String.valueOf(invitacionList.size()));

                    mAdapter.notifyDataSetChanged();

                    break;
                case 1:

                    Log.d("lista","Ocurrió un problema al obtener los eventos");
                    break;

            }
        }
    };
}
