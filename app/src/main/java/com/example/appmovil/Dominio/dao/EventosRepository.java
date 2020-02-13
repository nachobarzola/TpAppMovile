package com.example.appmovil.Dominio.dao;

import android.os.Handler;
import android.os.Message;

import com.example.appmovil.Dominio.Evento;
import com.example.appmovil.Dominio.Usuario;
import com.example.appmovil.Dominio.dao.rest.EventosRest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EventosRepository {

    private List<Evento> listaEventos;

    public static String _SERVER="http://192.157.192.222/api/";

    private Retrofit rf;

    private EventosRest _eventosRest;

    private static EventosRepository _INSTANCE;

    private EventosRepository(){}

    public static EventosRepository getInstance(){
        if(_INSTANCE==null){
            _INSTANCE = new EventosRepository();
            _INSTANCE.configurarRetrofit();
            _INSTANCE.listaEventos=new ArrayList<>();
        }
        return _INSTANCE;
    }

    private void configurarRetrofit(){
        this.rf = new Retrofit.Builder()
                .baseUrl(_SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this._eventosRest=this.rf.create(EventosRest.class);
    }

    public void ObtenerEventos(final Usuario usuario, final Handler h){
        Call<List<Evento>> llamada = this._eventosRest.Obtener(usuario.getId());
        llamada.enqueue(new Callback<List<Evento>>() {
            @Override
            public void onResponse(Call<List<Evento>> call, Response<List<Evento>> response) {
                if(response.isSuccessful()){
                    listaEventos.clear();
                    listaEventos.addAll(response.body());
                    Message m=new Message();
                    m.arg1=0;
                    h.sendMessage(m);
                }
            }

            @Override
            public void onFailure(Call<List<Evento>> call, Throwable t) {
                Message m=new Message();
                m.arg1=1;
                h.sendMessage(m);
            }
        });
    }

    public void Guardar(final Evento evento, final Handler h){
        Call<Void> llamada = this._eventosRest.Guardar(evento);
        llamada.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    listaEventos.add(evento);
                    Message m=new Message();
                    m.arg1=0;
                    h.sendMessage(m);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Message m=new Message();
                m.arg1=1;
                h.sendMessage(m);
            }
        });
    }
    public List<Evento> getListaEventos() {
        return listaEventos;
    }
}
