package edu.g3.sac.servicios;

import edu.g3.sac.dao.ConnectionGenerator;
import edu.g3.sac.dao.Dao;
import edu.g3.sac.dao.FactoryDao;
import edu.g3.sac.dao.beans.Celula;

import javax.naming.NamingException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.SQLException;

@Path("/celula")
public class CelulaAPI {

    private ConnectionGenerator connectionGenerator = new ConnectionGenerator();
    private FactoryDao factoryDao = FactoryDao.getInstance();

    @GET
    @Path("/getCelula")
    @Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
    public Celula getMiembro(
            @QueryParam("id") @DefaultValue("-1") int id
    ){
        if(id == -1)
            throw new WebApplicationException(Response.ok("{\"error\":\"Debe de enviar un id\"}").build());

        Celula retorno;
        try(Connection conexion = connectionGenerator.getConnection()){
            Dao<Celula> dao = factoryDao.getDao(conexion,FactoryDao.DAO_CELULA);

            retorno = dao.getRegistro(id);
            if(retorno == null)
                throw new WebApplicationException(Response.ok("{\"error\":\"no se encontro una celula con el id "+id+"\"}").build());
        }
        catch (SQLException | NamingException e){
            e.printStackTrace(System.err);
            throw new WebApplicationException(Response.ok("{\"error\":\"error en la conexion a la BD\"}").build());
        }
        return retorno;
    }

    @POST
    @Path("/insertarCelula")
    @Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
    @Consumes(MediaType.APPLICATION_JSON+";charset=utf-8")
    public String insertarMiembro(Celula asistencia){
        boolean retorno;
        try(Connection conexion = connectionGenerator.getConnection()){
            Dao<Celula> dao = factoryDao.getDao(conexion,FactoryDao.DAO_CELULA);

            retorno = dao.insertRegistro(asistencia);
            if(retorno == false)
                throw new WebApplicationException(Response.ok("{\"error\":\"No se puedo ingresar la celula\"}").build());
        }
        catch (SQLException | NamingException e){
            e.printStackTrace(System.err);
            throw new WebApplicationException(Response.ok("{\"error\":\"error en la conexion a la BD\"}").build());
        }
        return "{\"exito\":\"Celula ingresada\"}";
    }
}