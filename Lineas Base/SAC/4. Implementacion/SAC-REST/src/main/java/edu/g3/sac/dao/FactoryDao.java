package edu.g3.sac.dao;

import java.sql.Connection;

public class FactoryDao {

    public static final int DAO_MIEMBRO = 1;
    public static final int DAO_ZONA = 2;
    public static final int DAO_ASISTENCIA = 3;
    public static final int DAO_CELULA = 4;
    public static final int DAO_CELULA_MIEMBRO = 5;
    public static final int DAO_ASISTENCIADETALLE = 6;

    private static FactoryDao factoryDao = null;


    FactoryDao(){
    }

    public static FactoryDao getInstance(){
        if(factoryDao == null)
            factoryDao = new FactoryDao();

        return factoryDao;
    }

    public Dao getDao(Connection conexion, int tipo){
        Dao retorno = null;
        switch (tipo) {
            case DAO_MIEMBRO:
                retorno = new DaoMiembro(conexion);
                break;
            case DAO_ZONA:
                retorno = new DaoZona(conexion);
                break;
            case DAO_ASISTENCIA:
                retorno = new DaoAsistencia(conexion);
                break;
            case DAO_CELULA:
                retorno = new DaoCelula(conexion);
                break; 
            case DAO_CELULA_MIEMBRO:
                retorno = new DaoCelulaMiembro(conexion);
                break;
            case DAO_ASISTENCIADETALLE:
                retorno = new DaoAsistenciaDetalle(conexion);
                break;              
        }
        return retorno;
    }
}
