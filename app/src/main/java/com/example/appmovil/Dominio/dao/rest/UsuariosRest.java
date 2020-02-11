package com.example.appmovil.Dominio.dao.rest;

import java.util.List;

import com.example.appmovil.Dominio.Usuario;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UsuariosRest {

    @GET("usuarios")
    Call<List<Usuario>> Obtener(@Query("email") String email,@Query("password") String password);
}
