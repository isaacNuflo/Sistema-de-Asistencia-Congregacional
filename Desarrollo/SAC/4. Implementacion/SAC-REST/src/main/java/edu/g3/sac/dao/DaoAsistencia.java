package edu.g3.sac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.g3.sac.dao.beans.Asistencia;

public class DaoAsistencia implements Dao<Asistencia> {

    private Connection conexion;

    public DaoAsistencia(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public Asistencia getRegistro(int id) {
        String sql = "SELECT * FROM asistencia " + "WHERE idasistencia = ? ;";
        Asistencia registro = null;
        try (PreparedStatement pst = this.conexion.prepareStatement(sql)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    registro = new Asistencia();
                    registro.setIdasistencia(rs.getInt("idasistencia"));
                    registro.setIdcelula(rs.getInt("idcelula"));
                    registro.setFechareunion(rs.getDate("fechareunion"));
                    registro.setHorainicio(rs.getTime("horainicio"));
                    registro.setTotal(rs.getInt("total"));
                    registro.setLugarreunion(rs.getString("lugarreunion"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return registro;
    }

    @Override
    public List<Asistencia> listRegistros() {
        String sql = "SELECT * FROM asistencia ";
        List<Asistencia> registros;
        try (PreparedStatement pst = this.conexion.prepareStatement(sql)) {
            try (ResultSet rs = pst.executeQuery()) {
                registros = new ArrayList<>();
                while (rs.next()) {
                    Asistencia registro = new Asistencia();
                    registro.setIdasistencia(rs.getInt("idasistencia"));
                    registro.setIdcelula(rs.getInt("idcelula"));
                    registro.setFechareunion(rs.getDate("fechareunion"));
                    registro.setHorainicio(rs.getTime("horainicio"));
                    registro.setTotal(rs.getInt("total"));
                    registro.setLugarreunion(rs.getString("lugarreunion"));

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
        String sql = String.format("DELETE FROM asistencia %s ", this.condicional(filtros));
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
    public boolean insertRegistro(Asistencia registro) {
        boolean retorno;
        String sql = "INSERT INTO public.asistencia(idcelula, total, lugarreunion, horainicio, fechareunion)\n"
                + "	VALUES (?, ?, ?, ?, ?);\n";
        try (PreparedStatement pst = this.conexion.prepareStatement(sql)) {
            pst.setInt(1, registro.getIdcelula());
            pst.setInt(2, registro.getTotal());
            pst.setString(3, registro.getLugarreunion());
            pst.setTime(4, registro.getHorainicio());
            pst.setDate(5, registro.getFechareunion());

            pst.execute();
            retorno = true;
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            retorno = false;
        }
        return retorno;
    }

    @Override
    public int updateRegistro(Asistencia registro) {
        String sql = "UPDATE public.asistencia\n"
                + "SET idasistencia=?, idcelula=?, total=?, lugarreunion=?, horainicio=?, fechareunion=? \n"
                + "WHERE idasistencia = ?;";
        int retorno;
        try (PreparedStatement pst = this.conexion.prepareStatement(sql)) {
            pst.setInt(1, registro.getIdasistencia());
            pst.setInt(2, registro.getIdcelula());
            pst.setInt(3, registro.getTotal());
            pst.setString(4, registro.getLugarreunion());
            pst.setTime(5, registro.getHorainicio());
            pst.setDate(6, registro.getFechareunion());
            pst.setInt(7, registro.getIdasistencia());

            retorno = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            retorno = -1;
        }
        return retorno;
    }

    @Override
    public Asistencia getRegistro(List<Parametro> filtros) {
        String sql = String.format("SELECT * FROM asistencia %s ", this.condicional(filtros));
        Asistencia registro = null;
        try (PreparedStatement pst = this.conexion.prepareStatement(sql)) {
            int i = 1;
            for (Parametro p : filtros)
                pst.setObject(i++, p.getValor(), p.getTipo());
                
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    registro = new Asistencia();
                    registro.setIdasistencia(rs.getInt("idasistencia"));
                    registro.setIdcelula(rs.getInt("idcelula"));
                    registro.setFechareunion(rs.getDate("fechareunion"));
                    registro.setHorainicio(rs.getTime("horainicio"));
                    registro.setTotal(rs.getInt("total"));
                    registro.setLugarreunion(rs.getString("lugarreunion"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return registro;
    }

    @Override
    public List<Asistencia> listRegistros(int id) {
        return null;
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

}