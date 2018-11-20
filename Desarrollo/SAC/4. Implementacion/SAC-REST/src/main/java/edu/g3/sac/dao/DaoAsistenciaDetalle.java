package edu.g3.sac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.g3.sac.dao.beans.AsistenciaDetalle;

public class DaoAsistenciaDetalle implements Dao<AsistenciaDetalle> {

    private Connection conexion;

    public DaoAsistenciaDetalle(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public AsistenciaDetalle getRegistro(int id) {
        String sql = "SELECT * FROM asistenciadetalle " + "WHERE idasistenciadetalle = ? ;";
        AsistenciaDetalle registro = null;
        try (PreparedStatement pst = this.conexion.prepareStatement(sql)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    registro = new AsistenciaDetalle();
                    registro.setIdasistenciadetalle(rs.getInt("idasistenciadetalle"));
                    registro.setIdasistencia(rs.getInt("idasistencia"));
                    registro.setIdmiembro(rs.getInt("idmiembro"));
                    registro.setEstado(rs.getBoolean("estado"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return registro;
    }

    @Override
    public List<AsistenciaDetalle> listRegistros() {
        String sql = "SELECT * FROM asistencia ";
        List<AsistenciaDetalle> registros;
        try (PreparedStatement pst = this.conexion.prepareStatement(sql)) {
            try (ResultSet rs = pst.executeQuery()) {
                registros = new ArrayList<>();
                while (rs.next()) {
                    AsistenciaDetalle registro = new AsistenciaDetalle();
                    registro.setIdasistenciadetalle(rs.getInt("idasistenciadetalle"));
                    registro.setIdasistencia(rs.getInt("idasistencia"));
                    registro.setIdmiembro(rs.getInt("idmiembro"));
                    registro.setEstado(rs.getBoolean("estado"));

                    registros.add(registro);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            registros = null;
        }
        return registros;
    }

    @Override
    public int deleteRegistro(List<Parametro> filtros) {
        int afectados;
        String sql = String.format("DELETE FROM asistenciadetalle %s ", this.condicional(filtros));
        try (PreparedStatement pst = this.conexion.prepareStatement(sql)) {
            int i = 1;
            for (Parametro p : filtros)
                pst.setObject(i++, p.getValor(), p.getTipo());

            afectados = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            afectados = -1;
        }
        return afectados;
    }

    @Override
    public boolean insertRegistro(AsistenciaDetalle registro) {
        boolean retorno;
        String sql = "INSERT INTO public.asistenciadetalle(idasistencia, idmiembro, estado)\n"
                + "	VALUES (?, ?, ?);\n";
        try (PreparedStatement pst = this.conexion.prepareStatement(sql)) {
            pst.setInt(1, registro.getIdasistencia());
            pst.setInt(2, registro.getIdmiembro());
            pst.setBoolean(3, registro.isEstado());

            pst.execute();
            retorno = true;
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            retorno = false;
        }
        return retorno;
    }

    @Override
    public int updateRegistro(AsistenciaDetalle registro) {
        String sql = "UPDATE public.asistenciadetalle\n"
                + "SET idasistenciadetalle=?, idasistencia=?, idmiembro=?, estado=? \n"
                + "WHERE idasistenciadetalle = ?;";
        int retorno;
        try (PreparedStatement pst = this.conexion.prepareStatement(sql)) {
            pst.setInt(1, registro.getIdasistenciadetalle());
            pst.setInt(2, registro.getIdasistencia());
            pst.setInt(3, registro.getIdmiembro());
            pst.setBoolean(4, registro.isEstado());
            pst.setInt(5, registro.getIdasistenciadetalle());

            retorno = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            retorno = -1;
        }
        return retorno;
    }

    private String condicional(List<Parametro> parametros) {
        String retorno;
        if (parametros == null)
            retorno = "";
        else if (parametros.size() == 0)
            retorno = "";
        else {
            StringBuilder builder = new StringBuilder(" WHERE ");
            parametros.forEach(p -> builder.append(String.format(" %s = ? AND ", p.getParamName())));
            builder.delete(builder.length() - 4, builder.length());
            retorno = builder.toString();
        }
        return retorno;
    }

    @Override
    public AsistenciaDetalle getRegistro(List<Parametro> filtros) {
        return null;
    }

    @Override
    public List<AsistenciaDetalle> listRegistros(int id) {
        return null;
    }

    @Override
    public boolean insertManyRegistros(List<AsistenciaDetalle> registros) {
        boolean retorno;
        String sql = "INSERT INTO public.asistenciadetalle(idasistencia, idmiembro, estado)\n"
                + "	VALUES (?, ?, ?);\n";
        try (PreparedStatement pst = this.conexion.prepareStatement(sql)) {
            for(AsistenciaDetalle ad : registros){
                pst.setInt(1, ad.getIdasistencia());
                pst.setInt(2, ad.getIdmiembro());
                pst.setBoolean(3, ad.isEstado());
    
                pst.execute();
            }
            retorno = true;
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            retorno = false;
        }
        return retorno;
    }

}