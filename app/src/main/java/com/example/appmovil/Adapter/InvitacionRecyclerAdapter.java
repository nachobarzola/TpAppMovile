package com.example.appmovil.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmovil.Dominio.Invitacion;
import com.example.appmovil.R;
import com.example.appmovil.ViewHolder.InvitacionViewHolder;

import java.util.List;

public class InvitacionRecyclerAdapter extends RecyclerView.Adapter<InvitacionViewHolder> {

    private List<Invitacion> invitaciones;
    private View vista;

    public InvitacionRecyclerAdapter(List<Invitacion> Invitaciones){this.invitaciones=Invitaciones;}

    @NonNull
    @Override
    public InvitacionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_invitacion,parent,false);

        return new InvitacionViewHolder(vista);    }

    @Override
    public void onBindViewHolder(@NonNull InvitacionViewHolder holder, int position) {
        Invitacion invitacion = invitaciones.get(position);

        holder.tvUbicacion.setText(invitacion.getEvento().getUbicacion());
        holder.tvFechaHora.setText(invitacion.getEvento().getFecha() +" "+ invitacion.getEvento().getHora());
        holder.tvEstado.setText(invitacion.getEstado());

        //DECODIFICO LA IMAGEN
        byte[] decodedString = Base64.decode(invitacion.getQr(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        holder.ivQr.setImageBitmap(decodedByte);
    }

    @Override
    public int getItemCount() {
        return invitaciones.size();
    }
}
