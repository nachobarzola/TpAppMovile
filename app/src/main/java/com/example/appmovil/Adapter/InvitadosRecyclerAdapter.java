package com.example.appmovil.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmovil.Dominio.Usuario;
import com.example.appmovil.R;
import com.example.appmovil.ViewHolder.EventoViewHolder;
import com.example.appmovil.ViewHolder.InvitadosViewHolder;

import java.util.List;

public class InvitadosRecyclerAdapter extends RecyclerView.Adapter<InvitadosViewHolder>  {
    private List<Usuario> listaInvitados;
    private View vista;

    public InvitadosRecyclerAdapter(List<Usuario> invitados){
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
        final Usuario invitado = listaInvitados.get(position);

        holder.tvCorreo.setText(invitado.getEmail().toString());

    }

    @Override
    public int getItemCount() {
        return listaInvitados.size();
    }
}
