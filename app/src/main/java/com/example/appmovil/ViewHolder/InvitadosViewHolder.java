package com.example.appmovil.ViewHolder;

import android.media.Image;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmovil.R;

public class InvitadosViewHolder extends RecyclerView.ViewHolder{
    public TextView tvCorreo;
    public FrameLayout barraLateralDer,barraLateralIzq;


    public InvitadosViewHolder(@NonNull View itemView) {
        super(itemView);


        tvCorreo=itemView.findViewById(R.id.tvCorreo);
        barraLateralDer=itemView.findViewById(R.id.barraLateralDer);
        barraLateralIzq=itemView.findViewById(R.id.barraLateralIzq);




    }




}
