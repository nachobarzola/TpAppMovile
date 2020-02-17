package com.example.appmovil.Dominio;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Evento implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("nombre")
    @Expose
    private String nombre;

    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    @SerializedName("fecha")
    @Expose
    private String fecha;

    @SerializedName("hora")
    @Expose
    private String hora;

    @SerializedName("ubicacion")
    @Expose
    private String ubicacion;

    @SerializedName("usuarioId")
    @Expose
    private String usuario_id;

    @SerializedName("latitud")
    @Expose
    private double latitud;

    @SerializedName("longitud")
    @Expose
    private double longitud;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(String usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public Evento(int id, String nombre, String descripcion, String fecha, String hora, String ubicacion, String usuario_id, double latitud, double longitud) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.hora = hora;
        this.ubicacion = ubicacion;
        this.usuario_id = usuario_id;
        this.latitud = latitud;
        this.longitud = longitud;
    }
}
