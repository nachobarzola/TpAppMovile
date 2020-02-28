package com.example.appmovil.Presentacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.appmovil.Adapter.InvitadosRecyclerAdapter;
import com.example.appmovil.Dominio.Evento;
import com.example.appmovil.Dominio.Invitacion;
import com.example.appmovil.Dominio.Usuario;
import com.example.appmovil.Dominio.dao.InvitacionesRepository;
import com.example.appmovil.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class DetalleEventoActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Invitacion> invitados = new ArrayList<>();
    private Evento evento;

    private TextView tvNombre,tvDescripcion;
    private FloatingActionButton fabEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_evento);

        Intent i = getIntent();

        evento=(Evento)i.getSerializableExtra("evento");

        InicializarInterfaz();

        InvitacionesRepository.getInstance().ObtenerPorEvento(evento.getId(),miHandler);

        mRecyclerView = findViewById(R.id.rec_view_invitados);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager=new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter=new InvitadosRecyclerAdapter(invitados);

        mRecyclerView.setAdapter(mAdapter);

        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iEditarEvento= new Intent(v.getContext(), CrearEventoActivity.class);
                iEditarEvento.putExtra("evento",evento);
                v.getContext().startActivity(iEditarEvento);
                finish();
            }
        });
        //Dialogo que muestra "ver más" de la descripción
        tvDescripcion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("Descripción del evento");
                    builder.setMessage(evento.getDescripcion());
                    builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                }

        });



    }

    private void InicializarInterfaz(){
        tvNombre=findViewById(R.id.tvNombre);
        tvDescripcion=findViewById(R.id.tvDescripcion);
        fabEditar=findViewById(R.id.fabEditar);

        tvNombre.setText(evento.getNombre());
        tvDescripcion.setText(evento.getDescripcion());
    }

    Handler miHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            if(msg.obj!=null){
                invitados.addAll((Collection<? extends Invitacion>) msg.obj);
                mAdapter.notifyDataSetChanged();
            }
        }
    };
}
