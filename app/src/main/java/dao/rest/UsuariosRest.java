package dao.rest;

import java.util.List;

import models.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UsuariosRest {

    @GET("usuarios")
    Call<List<Usuario>> Obtener(@Query("email") String email,@Query("password") String password);
}
