package com.example.appmovil.Dominio.dao.rest;

import com.example.appmovil.Dominio.Evento;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface EventosRest {
    @GET("eventos")
    Call<List<Evento>> Obtener(@Query("usuario_id") String usuario_id);

    @POST("eventos")
    Call<Evento> Guardar(@Body Evento evento);
}
