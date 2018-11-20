package edu.g3.sac.servicios;

import edu.g3.sac.dao.ConnectionGenerator;
import edu.g3.sac.dao.Dao;
import edu.g3.sac.dao.FactoryDao;
import edu.g3.sac.dao.Parametro;
import edu.g3.sac.dao.beans.CelulaMiembro;
import jersey.repackaged.org.objectweb.asm.Type;

import javax.naming.NamingException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;

@Path("/celulamiembro")
public class CelulaMiembroAPI {

    private ConnectionGenerator connectionGenerator = new ConnectionGenerator();
    private FactoryDao factoryDao = FactoryDao.getInstance();

    @GET
    @Path("/getCelulamiembro")
    @Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
    public CelulaMiembro getMiembro(
            @QueryParam("idcelula") @DefaultValue("-1") int idcelula,
            @QueryParam("idmiembro") @DefaultValue("-1") int idmiembro
    ){
        if(idcelula == -1 || idmiembro == -1)
            throw new WebApplicationException(Response.ok("{\"error\":\"Debe de enviar un id\"}").build());

        CelulaMiembro retorno;
        try(Connection conexion = connectionGenerator.getConnection()){
            Dao<CelulaMiembro> dao = factoryDao.getDao(conexion,FactoryDao.DAO_CELULA_MIEMBRO);

            List<Parametro> parametros = new ArrayList<Parametro>();
            Parametro idCelula = new Parametro(idcelula,"idcelula",Type.INT);
            parametros.add(idCelula);
            Parametro idMiembro = new Parametro(idmiembro, "idmiembro", Type.INT);
            parametros.add(idMiembro);
            retorno = dao.getRegistro(parametros);
            if(retorno == null)
                throw new WebApplicationException(Response.ok("{\"error\":\"no se encontro una celula_miembro con el id_celula "+idcelula+"\"}").build());
        }
        catch (SQLException | NamingException e){
            e.printStackTrace(System.err);
            throw new WebApplicationException(Response.ok("{\"error\":\"error en la conexion a la BD\"}").build());
        }
        return retorno;
    }

    @POST
    @Path("/insertarCelulamiembro")
    @Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
    @Consumes(MediaType.APPLICATION_JSON+";charset=utf-8")
    public String insertarMiembro(CelulaMiembro asistencia){
        boolean retorno;
        try(Connection conexion = connectionGenerator.getConnection()){
            Dao<CelulaMiembro> dao = factoryDao.getDao(conexion,FactoryDao.DAO_CELULA_MIEMBRO);

            retorno = dao.insertRegistro(asistencia);
            if(retorno == false)
                throw new WebApplicationException(Response.ok("{\"error\":\"No se puedo ingresar la celula_miembro\"}").build());
        }
        catch (SQLException | NamingException e){
            e.printStackTrace(System.err);
            throw new WebApplicationException(Response.ok("{\"error\":\"error en la conexion a la BD\"}").build());
        }
        return "{\"exito\":\"Celula_miembro ingresada\"}";
    }
}