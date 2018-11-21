package edu.g3.sac.dao.beans;

import java.io.Serializable;

public class CelulaMiembro implements Serializable{

    private static final long serialVersionUID = 1L;

    private int idcelula;
    private int idmiembro;
    private boolean lider;

    public CelulaMiembro(){}

    public int getIdcelula() {
        return this.idcelula;
    }

    public void setIdcelula(int idcelula) {
        this.idcelula = idcelula;
    }

    public int getIdmiembro() {
        return this.idmiembro;
    }

    public void setIdmiembro(int idmiembro) {
        this.idmiembro = idmiembro;
    }

    public boolean isLider() {
        return this.lider;
    }

    public void setLider(boolean lider) {
        this.lider = lider;
    }
}