package com.example.appmovil.ViewHolder;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmovil.R;

public class InvitacionViewHolder extends RecyclerView.ViewHolder {
    public TextView tvNombreEvento;
    public TextView tvFechaHora;
    public TextView tvEstado;
    public ImageView ivQr;
    public FrameLayout barraLateralInvitacion;

    public InvitacionViewHolder(@NonNull View itemView) {
        super(itemView);

        tvNombreEvento=itemView.findViewById(R.id.invitacion_nombre_evento);
        tvFechaHora=itemView.findViewById(R.id.invitacion_fecha_hora);
        tvEstado=itemView.findViewById(R.id.invitacion_estado);
        ivQr=itemView.findViewById(R.id.invitacion_qr);
        barraLateralInvitacion=itemView.findViewById(R.id.barraLateralInvitacion);
    }
}
