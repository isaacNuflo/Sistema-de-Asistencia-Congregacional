package edu.g3.sac.servicios;

import edu.g3.sac.dao.ConnectionGenerator;
import edu.g3.sac.dao.Dao;
import edu.g3.sac.dao.FactoryDao;
import edu.g3.sac.dao.beans.Asistencia;

import javax.naming.NamingException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.SQLException;

@Path("/asistencia")
public class AsistenciaAPI {

    private ConnectionGenerator connectionGenerator = new ConnectionGenerator();
    private FactoryDao factoryDao = FactoryDao.getInstance();

    @GET
    @Path("/getAsistencia")
    @Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
    public Asistencia getMiembro(
            @QueryParam("id") @DefaultValue("-1") int id
    ){
        if(id == -1)
            throw new WebApplicationException(Response.ok("{\"error\":\"Debe de enviar un id\"}").build());

        Asistencia retorno;
        try(Connection conexion = connectionGenerator.getConnection()){
            Dao<Asistencia> dao = factoryDao.getDao(conexion,FactoryDao.DAO_ASISTENCIA);

            retorno = dao.getRegistro(id);
            if(retorno == null)
                throw new WebApplicationException(Response.ok("{\"error\":\"no se encontro una asistencia con el id "+id+"\"}").build());
        }
        catch (SQLException | NamingException e){
            e.printStackTrace(System.err);
            throw new WebApplicationException(Response.ok("{\"error\":\"error en la conexion a la BD\"}").build());
        }
        return retorno;
    }

    @POST
    @Path("/insertarAsistencia")
    @Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
    @Consumes(MediaType.APPLICATION_JSON+";charset=utf-8")
    public String insertarMiembro(Asistencia asistencia){
        boolean retorno;
        try(Connection conexion = connectionGenerator.getConnection()){
            Dao<Asistencia> dao = factoryDao.getDao(conexion,FactoryDao.DAO_ASISTENCIA);

            retorno = dao.insertRegistro(asistencia);
            if(retorno == false)
                throw new WebApplicationException(Response.ok("{\"error\":\"No se puedo ingresar la asistencia\"}").build());
        }
        catch (SQLException | NamingException e){
            e.printStackTrace(System.err);
            throw new WebApplicationException(Response.ok("{\"error\":\"error en la conexion a la BD\"}").build());
        }
        return "{\"exito\":\"Asistencia ingresada\"}";
    }
}