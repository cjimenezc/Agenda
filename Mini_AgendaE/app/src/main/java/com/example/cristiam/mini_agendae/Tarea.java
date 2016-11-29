package com.example.cristiam.mini_agendae;

/**
 * Creado por Cristiam
 */

import java.io.Serializable;

public class Tarea implements Serializable {

    private long id;
    private String titulo, des, hora, lugar;

    public Tarea(){
        id = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    @Override
    public String toString(){
        return "TITULO: "+ titulo + "\nHORA: "+ des;
    }
}
