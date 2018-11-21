package edu.g3.sac.dao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionGenerator {

    private static ConnectionParams config = null;

    public ConnectionGenerator() {
        if(ConnectionGenerator.config == null)
            reload();
    }
    void reload() {
        try {
            ConnectionGenerator.config = new ConnectionParams();
        } catch (IOException  e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException, NamingException {
        if(ConnectionGenerator.config.getTipo().equals("server")) {
            InitialContext ctx = new InitialContext();
            return ((DataSource)ctx.lookup(ConnectionGenerator.config.getJndi())).getConnection();
        }

        else {
            String url;
            switch(ConnectionGenerator.config.getDbProvider()) {
                case "mysql":
                    DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                    url = "jdbc:mysql://%s:%s/%s";
                    break;
                case "postgres":
                    DriverManager.registerDriver(new org.postgresql.Driver());
                    url = "jdbc:postgresql://%s:%s/%s";
                    break;
                default:
                    throw new IllegalArgumentException ("El proveedor de BD no "
                            + "es soportado por el sistema");
            }
            return DriverManager.getConnection(
                    String.format(url,
                            ConnectionGenerator.config.getIp(),
                            ConnectionGenerator.config.getPort(),
                            ConnectionGenerator.config.getDb()) ,
                    ConnectionGenerator.config.getUser(),
                    ConnectionGenerator.config.getPass());
        }

    }
}
