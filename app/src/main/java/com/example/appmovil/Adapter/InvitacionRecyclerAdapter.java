package com.example.appmovil.Adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmovil.Dominio.Invitacion;
import com.example.appmovil.Dominio.dao.InvitacionesRepository;
import com.example.appmovil.R;
import com.example.appmovil.ViewHolder.InvitacionViewHolder;

import java.util.Date;
import java.util.List;

public class InvitacionRecyclerAdapter extends RecyclerView.Adapter<InvitacionViewHolder> {

    private List<Invitacion> invitaciones;
    private View vista;
    //Atributo usado como contador para saber que color poner en la barra lateral
    private Integer contadorColorBarraLateral=0;

    public InvitacionRecyclerAdapter(List<Invitacion> Invitaciones){this.invitaciones=Invitaciones;}

    @NonNull
    @Override
    public InvitacionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_invitacion,null,false);

        return new InvitacionViewHolder(vista);    }

    @Override
    public void onBindViewHolder(@NonNull final InvitacionViewHolder holder, int position) {
        Invitacion invitacion = invitaciones.get(position);

        holder.tvNombreEvento.setText(invitacion.getEvento().getNombre());
        holder.tvFechaHora.setText(invitacion.getEvento().getFecha() +" "+ invitacion.getEvento().getHora());
        holder.tvEstado.setText(invitacion.getEstado());

        //DECODIFICO LA IMAGEN
        byte[] decodedString = Base64.decode(invitacion.getQr(), Base64.DEFAULT);
        final Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        holder.barraLateralInvitacion.setBackgroundColor(seleccionarColorBarraLateral());

        holder.ivQr.setImageBitmap(decodedByte);

        holder.ivQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(vista.getContext());

                //builder.setTitle("Ingresar correo del invitado");

                ImageView img = new ImageView(view.getContext());

                img.setImageBitmap(decodedByte);

                img.setMaxHeight(200);

                builder.setView(img);

                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return invitaciones.size();
    }
    /*Este metodo permite alterar colores en la barra latel del cardView
     *
     * */
    private int seleccionarColorBarraLateral(){
        switch (contadorColorBarraLateral){
            case 0:
                //Color violeta
                contadorColorBarraLateral++;
                return Color.rgb(186,104,200);
            case 1:
                //Color rojo
                contadorColorBarraLateral++;
                return Color.rgb(165,23,33);
            case 2:
                //Color amarillo
                contadorColorBarraLateral++;
                return Color.rgb(216,225,43);
            case 3:
                //Color celeste
                contadorColorBarraLateral++;
                return Color.rgb(43,191,192);
            default:
                //Color verde
                contadorColorBarraLateral=0;
                return Color.rgb(43,225,112);
        }
    }
}
