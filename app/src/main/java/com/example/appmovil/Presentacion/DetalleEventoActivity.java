package com.example.appmovil.Presentacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.appmovil.Adapter.InvitadosRecyclerAdapter;
import com.example.appmovil.Dominio.Usuario;
import com.example.appmovil.R;

import java.util.ArrayList;
import java.util.List;

public class DetalleEventoActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Usuario> invitados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_evento);
        
        //TODO: FACU: aca deberiamos traer para un evento todos los invitados

        //Usuario usuario = UsuariosRepository.getInstance().getUser();



        mRecyclerView = findViewById(R.id.rec_view_invitados);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager=new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter=new InvitadosRecyclerAdapter(invitados);

        mRecyclerView.setAdapter(mAdapter);





    }
}
