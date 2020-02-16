package com.example.appmovil.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmovil.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EventoViewHolder extends RecyclerView.ViewHolder{
    public ImageView img;
    public TextView tvNombre;
    public TextView tvFechaHora;
    public TextView tvUbicacion;
    public FloatingActionButton btnInvitar;
    public FrameLayout barraLateral;

    public EventoViewHolder(@NonNull View itemView) {
        super(itemView);

        //this.img=itemView.findViewById(R.id.evento_img);
        this.tvNombre=itemView.findViewById(R.id.evento_nombre);
        this.tvFechaHora=itemView.findViewById(R.id.evento_fecha_hora);
        this.tvUbicacion=itemView.findViewById(R.id.evento_ubicacion);
        this.btnInvitar =itemView.findViewById(R.id.btn_evento_invitar);
        this.barraLateral =itemView.findViewById(R.id.barraLateral);


    }
}
