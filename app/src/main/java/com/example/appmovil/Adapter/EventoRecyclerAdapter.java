package com.example.appmovil.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmovil.Dominio.Evento;
import com.example.appmovil.Dominio.Invitacion;
import com.example.appmovil.Dominio.dao.InvitacionesRepository;
import com.example.appmovil.Presentacion.ListaEventos;
import com.example.appmovil.R;
import com.example.appmovil.ViewHolder.EventoViewHolder;

import java.util.Date;
import java.util.List;

public class EventoRecyclerAdapter extends RecyclerView.Adapter<EventoViewHolder> {

    private List<Evento> listaEventos;
    private View vista;
    public EventoRecyclerAdapter(List<Evento> eventos){
        this.listaEventos=eventos;
    }

    @NonNull
    @Override
    public EventoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_evento,parent,false);

        return new EventoViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull EventoViewHolder holder, int position) {

        final Evento evento = listaEventos.get(position);

        holder.tvDescripcion.setText(evento.getDescripcion());

        holder.tvFecha.setText(evento.getFecha());

        holder.tvHora.setText(evento.getHora());

        holder.tvUbicacion.setText(evento.getUbicacion());


        //---------------BOTONES:
        holder.btnInvitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Ingresar correo del invitado");


                // Set up the input
                final EditText input = new EditText(view.getContext());
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String email;
                        email = input.getText().toString();

                        Invitacion invitacion=new Invitacion(0,email,evento.getId(),"CREADA", new Date());

                        Log.d("invitacionCreada",String.valueOf(invitacion.getEventoId()));
                        InvitacionesRepository.getInstance().Guardar(invitacion,miHandler);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaEventos.size();
    }
    Handler miHandler = new Handler(Looper.myLooper()){

        @Override
        public void handleMessage(Message msg) {

            switch (msg.arg1 ){
                case 0:
                   Log.d("recycler","Invitacion enviada");
                    break;
                case 1:
                    Log.d("recycler","Invitacion erronea");

                    break;

            }
        }
    };
}
