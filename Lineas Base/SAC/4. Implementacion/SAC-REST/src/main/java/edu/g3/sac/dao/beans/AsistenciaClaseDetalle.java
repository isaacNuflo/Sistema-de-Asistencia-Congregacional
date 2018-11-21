package edu.g3.sac.dao.beans;

import java.io.Serializable;

public class AsistenciaClaseDetalle implements Serializable{

    private static final long serialVersionUID = 1L;

    private int idasistenciaclasedetalle;
    private int idasistenciaclase;
    private int idmiembro;
    private boolean estado;

    public AsistenciaClaseDetalle(){}

    public int getIdasistenciaclasedetalle() {
        return this.idasistenciaclasedetalle;
    }

    public void setIdasistenciaclasedetalle(int idasistenciaclasedetalle) {
        this.idasistenciaclasedetalle = idasistenciaclasedetalle;
    }

    public int getIdasistenciaclase() {
        return this.idasistenciaclase;
    }

    public void setIdasistenciaclase(int idasistenciaclase) {
        this.idasistenciaclase = idasistenciaclase;
    }

    public int getIdmiembro() {
        return this.idmiembro;
    }

    public void setIdmiembro(int idmiembro) {
        this.idmiembro = idmiembro;
    }

    public boolean isEstado() {
        return this.estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}