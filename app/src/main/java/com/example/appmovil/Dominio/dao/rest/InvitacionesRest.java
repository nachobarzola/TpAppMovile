package com.example.appmovil.Dominio.dao.rest;


import com.example.appmovil.Dominio.Invitacion;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface InvitacionesRest {
    @GET("invitaciones/{email}")
    Call<List<Invitacion>> Obtener(@Path(value = "email", encoded = true) String email);

    @GET("invitaciones/evento/{eventoId}")
    Call<List<Invitacion>> ObtenerPorEvento(@Path(value = "eventoId", encoded = true) int eventoId);

    @POST("invitaciones")
    Call<Void> Guardar(@Body Invitacion invitacion);

    @POST("invitaciones/{usuarioId}/{invitacionId}")
    Call<Void> RegistrarIngreso(@Path(value = "usuarioId", encoded = true)String usuarioId,@Path(value = "invitacionId", encoded = true)int invitacionId);

}
