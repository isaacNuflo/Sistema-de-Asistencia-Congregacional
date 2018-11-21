package edu.g3.sac.dao.beans;

import java.io.Serializable;
import java.sql.Time;

public class Clase implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private int idclase;
    private int idmaestro;
    private String nombreclase;
    private int nivelclase;
    private String periodo;
    private Time horainicio;
    private Time horafin;
    private String lugarclase;

    public Clase(){}

    public int getIdclase() {
        return this.idclase;
    }

    public void setIdclase(int idclase) {
        this.idclase = idclase;
    }

    public int getIdmaestro() {
        return this.idmaestro;
    }

    public void setIdmaestro(int idmaestro) {
        this.idmaestro = idmaestro;
    }

    public String getNombreclase() {
        return this.nombreclase;
    }

    public void setNombreclase(String nombreclase) {
        this.nombreclase = nombreclase;
    }

    public int getNivelclase() {
        return this.nivelclase;
    }

    public void setNivelclase(int nivelclase) {
        this.nivelclase = nivelclase;
    }

    public String getPeriodo() {
        return this.periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Time getHorainicio() {
        return this.horainicio;
    }

    public void setHorainicio(Time horainicio) {
        this.horainicio = horainicio;
    }

    public Time getHorafin() {
        return this.horafin;
    }

    public void setHorafin(Time horafin) {
        this.horafin = horafin;
    }

    public String getLugarclase() {
        return this.lugarclase;
    }

    public void setLugarclase(String lugarclase) {
        this.lugarclase = lugarclase;
    }
}