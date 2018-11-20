package edu.g3.sac.dao.beans;

import java.io.Serializable;
import java.sql.Date;

public class AsistenciaClase implements Serializable{

    private static final long serialVersionUID = 1L;

    private int idasistenciaclase;
    private int idclase;
    private int numeroleccion;
    private int total;
    private Date fechaclase;

    public AsistenciaClase(){}

    public int getIdasistenciaclase() {
        return this.idasistenciaclase;
    }

    public void setIdasistenciaclase(int idasistenciaclase) {
        this.idasistenciaclase = idasistenciaclase;
    }

    public int getIdclase() {
        return this.idclase;
    }

    public void setIdclase(int idclase) {
        this.idclase = idclase;
    }

    public int getNumeroleccion() {
        return this.numeroleccion;
    }

    public void setNumeroleccion(int numeroleccion) {
        this.numeroleccion = numeroleccion;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Date getFechaclase() {
        return this.fechaclase;
    }

    public void setFechaclase(Date fechaclase) {
        this.fechaclase = fechaclase;
    }

}