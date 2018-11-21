package edu.g3.sac.dao.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.sql.Date;

public class Miembro implements Serializable {

    private static final long serialVersionUID = 1L;

    private int idmiembro;
    private String numeromiembro;
    private String nombres;
    private String apellidos;
    private int edad;
    private Date fechanacimiento;
    private String telefono;
    private String direccion;
    private boolean bautizado;
    private String cargo;
    @JsonIgnore
    private boolean rol;
    
    public Miembro() {}

    public int getIdmiembro() {
        return this.idmiembro;
    }

    public void setIdmiembro(int idmiembro) {
        this.idmiembro = idmiembro;
    }

    public String getNumeromiembro() {
        return this.numeromiembro;
    }

    public void setNumeromiembro(String numeromiembro) {
        this.numeromiembro = numeromiembro;
    }

    public String getNombres() {
        return this.nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return this.edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Date getFechanacimiento() {
        return this.fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean isBautizado() {
        return this.bautizado;
    }

    public void setBautizado(boolean bautizado) {
        this.bautizado = bautizado;
    }

    public String getCargo() {
        return this.cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public boolean isRol() {
        return this.rol;
    }

    public void setRol(boolean rol) {
        this.rol = rol;
    }

}