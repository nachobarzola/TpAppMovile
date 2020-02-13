package com.example.appmovil.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmovil.R;

public class InvitacionViewHolder extends RecyclerView.ViewHolder {
    public TextView tvUbicacion;
    public TextView tvFechaHora;
    public TextView tvEstado;
    public ImageView ivQr;

    public InvitacionViewHolder(@NonNull View itemView) {
        super(itemView);

        tvUbicacion=itemView.findViewById(R.id.invitacion_ubicacion);
        tvFechaHora=itemView.findViewById(R.id.invitacion_fecha_hora);
        tvEstado=itemView.findViewById(R.id.invitacion_estado);
        ivQr=itemView.findViewById(R.id.invitacion_qr);
    }
}
