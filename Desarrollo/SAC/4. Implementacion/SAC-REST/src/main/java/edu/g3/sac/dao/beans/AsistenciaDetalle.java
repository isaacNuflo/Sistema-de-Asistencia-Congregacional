package edu.g3.sac.dao.beans;

import java.io.Serializable;

public class AsistenciaDetalle implements Serializable{

    private static final long serialVersionUID = 1L;

    private int idasistenciadetalle;
    private int idasistencia;
    private int idmiembro;
    private boolean estado;

    public AsistenciaDetalle(){}

    public int getIdasistenciadetalle() {
        return this.idasistenciadetalle;
    }

    public void setIdasistenciadetalle(int idasistenciadetalle) {
        this.idasistenciadetalle = idasistenciadetalle;
    }

    public int getIdasistencia() {
        return this.idasistencia;
    }

    public void setIdasistencia(int idasistencia) {
        this.idasistencia = idasistencia;
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