package edu.g3.sac.dao;

import java.util.List;

public interface Dao <T> {

    T getRegistro(int id);

    List<T> listRegistros();

    int deleteRegistro(List<Parametro> filtros);

    boolean insertRegistro(T registro);

    int updateRegistro(T registro);
}
