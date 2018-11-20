package edu.g3.sac.dao.beans;

import java.io.Serializable;

public class ClaseMiembro implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private int idmiembro;
    private int idclase;

    public ClaseMiembro(){}

    public int getIdmiembro() {
        return this.idmiembro;
    }

    public void setIdmiembro(int idmiembro) {
        this.idmiembro = idmiembro;
    }

    public int getIdclase() {
        return this.idclase;
    }

    public void setIdclase(int idclase) {
        this.idclase = idclase;
    }

}    