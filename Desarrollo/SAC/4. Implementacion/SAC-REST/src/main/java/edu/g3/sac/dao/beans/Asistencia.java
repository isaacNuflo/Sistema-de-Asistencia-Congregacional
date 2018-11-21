package edu.g3.sac.dao.beans;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class Asistencia implements Serializable{

    private static final long serialVersionUID = 1L;

    private int idasistencia;
    private int idcelula;
    private int total;
    private String lugarreunion;
    private Time horainicio;
    private Date fechareunion;

    public Asistencia(){}

    public int getIdasistencia() {
        return this.idasistencia;
    }

    public void setIdasistencia(int idasistencia) {
        this.idasistencia = idasistencia;
    }

    public int getIdcelula() {
        return this.idcelula;
    }

    public void setIdcelula(int idcelula) {
        this.idcelula = idcelula;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getLugarreunion() {
        return this.lugarreunion;
    }

    public void setLugarreunion(String lugarreunion) {
        this.lugarreunion = lugarreunion;
    }

    public Time getHorainicio() {
        return this.horainicio;
    }

    public void setHorainicio(Time horainicio) {
        this.horainicio = horainicio;
    }

    public Date getFechareunion() {
        return this.fechareunion;
    }

    public void setFechareunion(Date fechareunion) {
        this.fechareunion = fechareunion;
    }

}