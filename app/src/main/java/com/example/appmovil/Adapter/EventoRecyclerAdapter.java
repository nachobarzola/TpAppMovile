package com.example.appmovil.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import android.os.StrictMode;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmovil.Dominio.Evento;
import com.example.appmovil.Dominio.Invitacion;
import com.example.appmovil.Dominio.dao.InvitacionesRepository;
import com.example.appmovil.Presentacion.DetalleEventoActivity;
import com.example.appmovil.Presentacion.ListaEventos;
import com.example.appmovil.R;
import com.example.appmovil.Services.MyBroadcastReceiver;
import com.example.appmovil.ViewHolder.EventoViewHolder;

import java.util.Date;
import java.util.List;
import java.util.Properties;

/*
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

 */

public class EventoRecyclerAdapter extends RecyclerView.Adapter<EventoViewHolder> {

    private List<Evento> listaEventos;
    private View vista;
    //Atributo usado como contador para saber que color poner en la barra lateral
    private Integer contadorColorBarraLateral=0;
    //-----

    private Context contexto;
    //Logica de correo
    //private Session session;
    private String _CORREOAPP="controlEventosQR@gmail.com";
    private String _CONTRASEÑACOREEOAPP="facunacho";
    //-----

    public EventoRecyclerAdapter(List<Evento> eventos){
        this.listaEventos=eventos;
    }

    @NonNull
    @Override
    public EventoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_evento,parent,false);
        return new EventoViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull EventoViewHolder holder, int position) {

        final Evento evento = listaEventos.get(position);

        contexto=holder.tvNombre.getContext();

        holder.tvNombre.setText(evento.getNombre());

        holder.tvFechaHora.setText(evento.getFecha() + " " + evento.getHora());

        holder.tvUbicacion.setText(evento.getUbicacion());

        holder.barraLateral.setBackgroundColor(seleccionarColorBarraLateral());



        //---------------BOTONES:
        holder.btnInvitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Ingresar correo del invitado");


                // Set up the input
                final EditText input = new EditText(view.getContext());
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String email;
                        email = input.getText().toString();

                        Invitacion invitacion=new Invitacion(0,email,evento.getId(),"CREADA", new Date());
                        Log.d("invitacionCreada",String.valueOf(invitacion.getEventoId()));
                        InvitacionesRepository.getInstance().Guardar(invitacion,miHandler);
                //Envio de la envitacion por mail
                        enviarCorreoElectronico(email,"Beach Fest","Juan Blanco","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sullae consulatum? Non risu potius quam oratione eiciendum? Cum praesertim illa perdiscere ludus esset.","16/12/2020","02:00");

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
        holder.cardViewEventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent iDetallesEvento= new Intent(v.getContext(), DetalleEventoActivity.class);
                iDetallesEvento.putExtra("evento",evento);
                v.getContext().startActivity(iDetallesEvento);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaEventos.size();
    }
    Handler miHandler = new Handler(Looper.myLooper()){

        @Override
        public void handleMessage(Message msg) {

            switch (msg.arg1 ){
                case 0:
                    Intent i = new Intent();
                    i.putExtra("data1","Invitación guardada");
                    i.putExtra("data2","Se creó la nueva invitación al evento");
                    i.setAction(MyBroadcastReceiver.EVENTO_02);
                    contexto.sendBroadcast(i);
                    break;
                case 1:
                    Log.d("recycler","Invitacion erronea");

                    break;

            }
        }
    };
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

    private void enviarCorreoElectronico(String correoReceptor,String nombreEvento,String nombreUsuarioCreadorEvento,String descripcionEvento,String fechaEvento,String horaEvento){
        //La libreria usada es JavaMail de android

        String msj_asunto="Envitación a participar del evento "+nombreEvento;
        String msj_titulo="Usted ha sido invitado por: "+nombreUsuarioCreadorEvento;
        String msj_cuerpo=msj_titulo+"<br><br><br>"+
                descripcionEvento+"."+ "<br><br>" +
                "Se realizara el día: "+fechaEvento+"."+ "<br>" +
                "Hora: "+horaEvento+"."+ "<br>"+
                "Codigo QR de ingreso al evento: <br>";
        //<br> es el salto de linea


        StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Properties properties= new Properties();
        properties.put("mail.smtp.host","smtp.googlemail.com");
        properties.put("mail.smtp.socketFactory.port","465");
        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.port","465");
      /*  try{
            session= Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(_CORREOAPP,_CONTRASEÑACOREEOAPP);
                }
            });
            if(session!=null){
                javax.mail.Message message= new MimeMessage(session);
                //Correo emisor
                message.setFrom(new InternetAddress(_CORREOAPP));
                //Asunto del mensaje
                message.setSubject(msj_asunto);
                //Correo receptor
                message.setRecipients(javax.mail.Message.RecipientType.TO,InternetAddress.parse(correoReceptor));
                //mensaje
                message.setContent(msj_cuerpo,"text/html; charset=utf-8");
                //enviamos correo
                Transport.send(message);
            }
        }catch (Exception e){ }
*/

    }
}
