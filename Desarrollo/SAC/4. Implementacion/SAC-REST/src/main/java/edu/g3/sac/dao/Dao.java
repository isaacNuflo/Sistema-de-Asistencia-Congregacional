package edu.g3.sac.dao;

import java.util.List;

public interface Dao <T> {

    T getRegistro(int id);

    List<T> listRegistros();

    int deleteRegistro(List<Parametro> filtros);

    boolean insertRegistro(T registro);

    int updateRegistro(T registro);

    T getRegistro(List<Parametro> filtros);

    List<T> listRegistros(int id);
    
    default boolean insertManyRegistros(List<T> registros){
        throw new UnsupportedOperationException("Metodo no soportado");
    }
}
