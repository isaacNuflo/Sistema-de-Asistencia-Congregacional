package edu.g3.sac.dao;

import java.sql.Connection;

public class FactoryDao {

    public static final int DAO_MIEMBRO = 1;

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
        }
        return retorno;
    }
}
