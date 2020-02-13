package com.example.appmovil.Dominio.dao.rest;

import com.example.appmovil.Dominio.Evento;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EventosRest {
    @GET("eventos/{usuario_id}")
    Call<List<Evento>> Obtener(@Path(value = "usuario_id", encoded = true) String usuario_id);

    @POST("eventos")
    Call<Void> Guardar(@Body Evento evento);
}
