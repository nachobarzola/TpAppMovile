package com.example.appmovil.Dominio;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Invitacion  {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("eventoId")
    @Expose
    private int eventoId;

    @SerializedName("evento")
    @Expose
    private Evento evento;

    @SerializedName("estado")
    @Expose
    private String estado;

    @SerializedName("fechaAct")
    @Expose
    private Date fechaAct;

    @SerializedName("qr")
    @Expose
    private String qr;

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEventoId() {
        return eventoId;
    }

    public void setEventoId(int eventoId) {
        this.eventoId = eventoId;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaAct() {
        return fechaAct;
    }

    public void setFechaAct(Date fechaAct) {
        this.fechaAct = fechaAct;
    }

    public Invitacion(int id, String email, int eventoId, String estado, Date fechaAct) {
        this.id = id;
        this.email = email;
        this.eventoId = eventoId;
        this.estado = estado;
        this.fechaAct = fechaAct;
    }
}
