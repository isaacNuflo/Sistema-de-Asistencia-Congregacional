package edu.g3.sac.dao;

public class Parametro {
    private Object valor;
    private String paramName;
    private int tipo;


    public Parametro(Object valor, String paramName, int tipo) {
        this.valor = valor;
        this.paramName = paramName;
        this.tipo = tipo;
    }

    public Object getValor() {
        return valor;
    }


    public String getParamName() {
        return paramName;
    }


    public int getTipo() {
        return tipo;
    }

}