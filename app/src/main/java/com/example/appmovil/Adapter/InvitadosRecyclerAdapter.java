package com.example.appmovil.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmovil.Dominio.Invitacion;
import com.example.appmovil.Dominio.Usuario;
import com.example.appmovil.R;
import com.example.appmovil.ViewHolder.InvitadosViewHolder;

import java.util.List;

public class InvitadosRecyclerAdapter extends RecyclerView.Adapter<InvitadosViewHolder>  {
    private List<Invitacion> listaInvitados;
    private View vista;

    public InvitadosRecyclerAdapter(List<Invitacion> invitados){
        this.listaInvitados=invitados;
    }


    @NonNull
    @Override
    public InvitadosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_invitado,null,false);
        return new InvitadosViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull InvitadosViewHolder holder, int position) {
        final Invitacion invitado = listaInvitados.get(position);

        holder.tvCorreo.setText(invitado.getEmail().toString());
        holder.tvEstado.setText((invitado.getEstado().toString()));

    }

    @Override
    public int getItemCount() {
        return listaInvitados.size();
    }
}
