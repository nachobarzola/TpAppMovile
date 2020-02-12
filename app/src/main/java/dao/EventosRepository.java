package dao;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.appmovil.Dominio.Evento;
import com.example.appmovil.Dominio.Usuario;

import java.util.ArrayList;
import java.util.List;

import com.example.appmovil.Dominio.dao.rest.EventosRest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EventosRepository {

    private Retrofit rf;

    private EventosRest eventosRest;

    private static EventosRepository _INSTANCE;

    public List<Evento> getEventos() {
        return eventos;
    }

    private List<Evento> eventos;

    private EventosRepository(){}

    public static EventosRepository getInstance(){
        if(_INSTANCE==null){
            _INSTANCE = new EventosRepository();
            _INSTANCE.configurarRetrofit();
            _INSTANCE.eventos=new ArrayList<Evento>();
        }
        return _INSTANCE;
    }

    private void configurarRetrofit(){
        this.rf = new Retrofit.Builder()
                .baseUrl("http://192.168.159.50:5001/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.eventosRest=this.rf.create(EventosRest.class);
    }

    public void obtenerEventos(final Usuario usuario, final Handler h){
        Call<List<Evento>> llamada = this.eventosRest.Obtener(usuario.getId());
        llamada.enqueue(new Callback<List<Evento>>() {
            @Override
            public void onResponse(Call<List<Evento>> call, Response<List<Evento>> response) {
                if(response.isSuccessful()){
                    eventos.addAll(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Evento>> call, Throwable t) {
                Log.d("failure", "loguie mal ");
                Message m=new Message();
                m.arg1=1;
                h.sendMessage(m);
            }
        });
    }

}
