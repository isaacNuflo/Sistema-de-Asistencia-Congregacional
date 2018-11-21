package edu.g3.sac.servicios;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.g3.sac.dao.ConnectionGenerator;
import edu.g3.sac.dao.Dao;
import edu.g3.sac.dao.FactoryDao;
import edu.g3.sac.dao.Parametro;
import edu.g3.sac.dao.beans.Asistencia;
import edu.g3.sac.dao.beans.AsistenciaDetalle;
import edu.g3.sac.servicios.peticiones.PeticionAsistencia;

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
    public String insertarMiembro(PeticionAsistencia peticion){
        boolean retorno;
        if(peticion.getAsistenciadetalle() == null) 
            throw new WebApplicationException(Response.ok("{\"error\":\"Debes enviar un detalle\"}").build());
        else if(peticion.getAsistenciadetalle().size() == 0)
            throw new WebApplicationException(Response.ok("{\"error\":\"Debes enviar un detalle\"}").build());

        try(Connection conexion = connectionGenerator.getConnection()){
            conexion.setAutoCommit(false);
            
            Dao<Asistencia> daoAsistencia = factoryDao.getDao(conexion,FactoryDao.DAO_ASISTENCIA);
            Dao<AsistenciaDetalle> daoAsistenciaDetalle = factoryDao.getDao(conexion, FactoryDao.DAO_ASISTENCIADETALLE);
            retorno = daoAsistencia.insertRegistro(peticion.getAsistencia());
            
            if(retorno){
                List<Parametro> parametros = new ArrayList<Parametro>();
                Parametro idCelula = new Parametro(peticion.getAsistencia().getIdcelula(),"idcelula", Types.INTEGER);
                parametros.add(idCelula);
                Parametro fechareunion = new Parametro(peticion.getAsistencia().getFechareunion(), "fechareunion", Types.DATE);
                parametros.add(fechareunion);
                Asistencia idasistencia = daoAsistencia.getRegistro(parametros);
                if(idasistencia == null) 
                    throw new WebApplicationException(Response.ok("{\"error\":\"No se encontro el idasistencia\"}").build());
                
                peticion.getAsistenciadetalle().forEach(d -> d.setIdasistencia(idasistencia.getIdasistencia()));
                retorno = daoAsistenciaDetalle.insertManyRegistros(peticion.getAsistenciadetalle());
            }
            
            if(retorno == false){
                conexion.rollback();
                throw new WebApplicationException(Response.ok("{\"error\":\"No se puedo ingresar la asistencia\"}").build());
            }
            else 
                conexion.commit();
        
        }
        catch (SQLException | NamingException e){
            e.printStackTrace(System.err);
            throw new WebApplicationException(Response.ok("{\"error\":\"error en la conexion a la BD\"}").build());
        }
        return "{\"exito\":\"Asistencia ingresada\"}";
    }

    
}