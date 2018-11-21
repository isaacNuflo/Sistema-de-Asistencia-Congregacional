package edu.g3.sac.dao.beans;

import java.io.Serializable;

public class Zona implements Serializable{

    private static final long serialVersionUID = 1L;

    private int idzona;
    private int idpastor;
    private String nombrezona;

    public Zona(){}

    public int getIdzona() {
        return this.idzona;
    }

    public void setIdzona(int idzona) {
        this.idzona = idzona;
    }

    public int getIdpastor() {
        return this.idpastor;
    }

    public void setIdpastor(int idpastor) {
        this.idpastor = idpastor;
    }

    public String getNombrezona() {
        return this.nombrezona;
    }

    public void setNombrezona(String nombrezona) {
        this.nombrezona = nombrezona;
    }

}