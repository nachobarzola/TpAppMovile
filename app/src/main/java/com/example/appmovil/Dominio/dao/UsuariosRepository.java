package com.example.appmovil.Dominio.dao;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.List;

import com.example.appmovil.Dominio.dao.rest.UsuariosRest;
import com.example.appmovil.Dominio.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UsuariosRepository {
    public static String _SERVER="http://192.168.0.23:5000/";

    private Retrofit rf;

    private Usuario user;

    private UsuariosRest usuariosRest;

    private static UsuariosRepository _INSTANCE;

    private UsuariosRepository(){}

    public static UsuariosRepository getInstance(){
        if(_INSTANCE==null){
            _INSTANCE = new UsuariosRepository();
            _INSTANCE.configurarRetrofit();
        }
        return _INSTANCE;
    }

    private void configurarRetrofit(){
        this.rf = new Retrofit.Builder()
                .baseUrl(_SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.usuariosRest=this.rf.create(UsuariosRest.class);
    }

    public void obtenerUsuario(final Usuario usuario, final Handler h){
        Call<List<Usuario>> llamada = this.usuariosRest.Obtener(usuario.getEmail());
        llamada.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if(response.isSuccessful()){
                    if(response.body().size()<1){
                        Message m=new Message();
                        m.arg1=1;
                        h.sendMessage(m);
                    }
                    else {
                        user= response.body().get(0);
                        Message m=new Message();
                        m.arg1=0;
                        h.sendMessage(m);
                    }


                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Log.d("failure", "loguie mal ");
                Message m=new Message();
                m.arg1=1;
                h.sendMessage(m);
            }
        });
    }

    public void Guardar(final Usuario usuario,final Handler h){
        Call<Usuario> llamada = this.usuariosRest.Guardar(usuario);

        llamada.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Message m=new Message();
                m.arg1=2;
                h.sendMessage(m);
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Message m=new Message();
                m.arg1=1;
                h.sendMessage(m);
            }
        });
    }
    public Usuario getUser(){
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
}
