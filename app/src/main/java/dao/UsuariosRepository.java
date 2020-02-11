package dao;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.List;

import dao.rest.UsuariosRest;
import models.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UsuariosRepository {
    public static String _SERVER="http://127.0.0.1:5000/";

    private Retrofit rf;

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
                .baseUrl("http://192.168.159.50:5001/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Log.d("APP_2","INSTANCIA CREADA");

        this.usuariosRest=this.rf.create(UsuariosRest.class);
    }

    public void obtenerUsuario(final Usuario usuario, final Handler h){
        Log.d("repo",usuario.getPassword());
        Call<List<Usuario>> llamada = this.usuariosRest.Obtener(usuario.getEmail(),usuario.getPassword());
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
}
