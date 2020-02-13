package com.example.appmovil.Dominio.dao;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.appmovil.Dominio.Invitacion;
import com.example.appmovil.Dominio.Usuario;
import com.example.appmovil.Dominio.dao.rest.InvitacionesRest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InvitacionesRepository {
    private List<Invitacion> listaInvitaciones;

    //INTERNET
    public static String _SERVER="http://192.157.192.222/api/";

    //LOCAL
    //public static String _SERVER="http://192.168.0.23:58500/api/";

    private Retrofit rf;

    private InvitacionesRest _invitacionesRest;

    private static InvitacionesRepository _INSTANCE;

    private InvitacionesRepository(){}

    public static InvitacionesRepository getInstance(){
        if(_INSTANCE==null){
            _INSTANCE = new InvitacionesRepository();
            _INSTANCE.configurarRetrofit();
            _INSTANCE.listaInvitaciones=new ArrayList<>();
        }
        return _INSTANCE;
    }

    private void configurarRetrofit() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        this.rf = new Retrofit.Builder()
                .baseUrl(_SERVER)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        this._invitacionesRest = this.rf.create(InvitacionesRest.class);
    }
    public void Obtener(final Usuario usuario, final Handler h){
        Call<List<Invitacion>> llamada = this._invitacionesRest.Obtener(usuario.getEmail());
        llamada.enqueue(new Callback<List<Invitacion>>() {
            @Override
            public void onResponse(Call<List<Invitacion>> call, Response<List<Invitacion>> response) {
                if(response.isSuccessful()){
                    listaInvitaciones.clear();
                    listaInvitaciones.addAll(response.body());
                    Message m=new Message();
                    m.arg1=0;
                    h.sendMessage(m);
                }
            }

            @Override
            public void onFailure(Call<List<Invitacion>> call, Throwable t) {
                Message m=new Message();
                m.arg1=1;
                h.sendMessage(m);
            }
        });
    }
    public void Guardar(final Invitacion invitacion, final Handler h) {
        Log.d("repo","ingreso");
        Call<Void> llamada = this._invitacionesRest.Guardar(invitacion);
        llamada.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    listaInvitaciones.add(invitacion);
                    Message m = new Message();
                    m.arg1 = 0;
                    h.sendMessage(m);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Message m = new Message();
                m.arg1 = 1;
                h.sendMessage(m);
            }
        });
    }

    public List<Invitacion> getListaInvitaciones() {
        return listaInvitaciones;
    }
}
