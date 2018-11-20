package edu.g3.sac.servicios;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import edu.g3.sac.dao.ConnectionGenerator;
import edu.g3.sac.dao.FactoryDao;

import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.SQLException;

@Path("/")
public class MainAPI {
    private ConnectionGenerator connectionGenerator = new ConnectionGenerator();
    private FactoryDao factoryDao = FactoryDao.getInstance();

    @Path("/descripcion")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getDescripcion(){

        StringBuilder descriptor = new StringBuilder("<html><head><title>Servicios SAC</title></head>");
        descriptor.append("<body>");
        descriptor.append("<h1>Servicios</h1>");
        descriptor.append("<ul>");
        descriptor.append("<li>Prueba</li>");
        descriptor.append("</ul>");

        return descriptor.toString();
    }

    @Path("/testConexion")
    @GET
    @Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
    public String probarConexion(){
        String retorno;
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode nodo = mapper.createObjectNode();
        try(Connection conn = connectionGenerator.getConnection()){
            nodo.put("conexion" , true);
        }
        catch (SQLException | NamingException e) {
            e.printStackTrace(System.err);
            nodo.put("conexion", false);
            nodo.put("mensaje",e.getMessage());
        }

        return nodo.toString();
    }
}
