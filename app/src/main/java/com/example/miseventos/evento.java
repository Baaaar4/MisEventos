package com.example.miseventos;

import java.util.Date;

public class evento {
    private String titulo;
    private String fecha;
    private String importancia;
    private String observacion;
    private String lugar;
    private String taviso;

    //region constructores

    public evento(String titulo, String fecha, String importancia, String observacion, String lugar, String taviso){
        this.titulo = titulo;
        this.fecha = fecha;
        this.importancia = importancia;
        this.observacion = observacion;
        this.lugar = lugar;
        this.taviso= taviso;
    }

    //region Get y Set

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getImportancia() {
        return importancia;
    }

    public void setImportancia(String importancia) {
        this.importancia = importancia;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
    public String getTaviso() {
        return taviso;
    }

    public void setTaviso(String taviso) {
        this.taviso = taviso;
    }

    //endregion
}

