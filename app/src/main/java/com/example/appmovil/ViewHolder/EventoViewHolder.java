package com.example.appmovil.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmovil.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EventoViewHolder extends RecyclerView.ViewHolder{
    public ImageView img;
    public TextView tvDescripcion;
    public TextView tvFecha;
    public TextView tvHora;
    public TextView tvUbicacion;
    public FloatingActionButton btnInvitar;

    public EventoViewHolder(@NonNull View itemView) {
        super(itemView);

        this.img=itemView.findViewById(R.id.evento_img);
        this.tvDescripcion=itemView.findViewById(R.id.evento_descripcion);
        this.tvFecha=itemView.findViewById(R.id.evento_fecha);
        this.tvHora=itemView.findViewById(R.id.evento_hora);
        this.tvUbicacion=itemView.findViewById(R.id.evento_ubicacion);
        this.btnInvitar =itemView.findViewById(R.id.btn_evento_invitar);


    }
}
