package com.example.appmovil.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmovil.Dominio.Evento;
import com.example.appmovil.Presentacion.ListaEventos;
import com.example.appmovil.R;
import com.example.appmovil.ViewHolder.EventoViewHolder;

import java.util.List;

public class EventoRecyclerAdapter extends RecyclerView.Adapter<EventoViewHolder> {

    private List<Evento> listaEventos;

    public EventoRecyclerAdapter(List<Evento> eventos){
        this.listaEventos=eventos;
    }

    @NonNull
    @Override
    public EventoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_evento,parent,false);

        return new EventoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EventoViewHolder holder, int position) {

        Evento evento = listaEventos.get(position);

        holder.tvDescripcion.setText(evento.getDescripcion());

        holder.tvFecha.setText(evento.getFecha());

        holder.tvHora.setText(evento.getHora());

        holder.tvUbicacion.setText(evento.getUbicacion());

    }

    @Override
    public int getItemCount() {
        return listaEventos.size();
    }
}
