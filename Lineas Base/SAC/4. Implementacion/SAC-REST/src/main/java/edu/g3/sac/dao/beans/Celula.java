package edu.g3.sac.dao.beans;

import java.io.Serializable;

public class Celula implements Serializable{

    private static final long serialVersionUID = 1L;

    private int idcelula;
    private int idzona;
    private int numerocelula;

    public Celula(){}

    public int getIdcelula() {
        return this.idcelula;
    }

    public void setIdcelula(int idcelula) {
        this.idcelula = idcelula;
    }

    public int getIdzona() {
        return this.idzona;
    }

    public void setIdzona(int idzona) {
        this.idzona = idzona;
    }

    public int getNumerocelula() {
        return this.numerocelula;
    }

    public void setNumerocelula(int numerocelula) {
        this.numerocelula = numerocelula;
    }
}