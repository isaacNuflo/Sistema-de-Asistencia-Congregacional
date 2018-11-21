package edu.g3.sac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.g3.sac.dao.beans.Zona;

public class DaoZona implements Dao<Zona> {

    private Connection conexion;

    public DaoZona(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public Zona getRegistro(int id) {
        String sql = "SELECT * FROM zona " + "WHERE idzona = ? ;";
        Zona registro = null;
        try (PreparedStatement pst = this.conexion.prepareStatement(sql)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    registro = new Zona();
                    registro.setIdzona(rs.getInt("idzona"));
                    registro.setIdpastor(rs.getInt("idpastor"));
                    registro.setNombrezona(rs.getString("nombrezona"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return registro;
    }

    @Override
    public List<Zona> listRegistros() {
        String sql = "SELECT * FROM zona ";
        List<Zona> registros;
        try (PreparedStatement pst = this.conexion.prepareStatement(sql)) {
            try (ResultSet rs = pst.executeQuery()) {
                registros = new ArrayList<>();
                while (rs.next()) {
                    Zona registro = new Zona();
                    registro.setIdzona(rs.getInt("idzona"));
                    registro.setIdpastor(rs.getInt("idpastor"));
                    registro.setNombrezona(rs.getString("nombrezona"));

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
        String sql = String.format("DELETE FROM zona %s ", this.condicional(filtros));
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
    public boolean insertRegistro(Zona registro) {
        boolean retorno;
        String sql = "INSERT INTO public.zona(idpastor, nombrezona)\n" + "	VALUES (?, ?);\n";
        try (PreparedStatement pst = this.conexion.prepareStatement(sql)) {
            pst.setInt(1, registro.getIdpastor());
            pst.setString(2, registro.getNombrezona());

            pst.execute();
            retorno = true;
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            retorno = false;
        }
        return retorno;
    }

    @Override
    public int updateRegistro(Zona registro) {
        String sql = "UPDATE public.zona\n" + "SET idzona=?, idpastor=?, nombrezona=? \n" + "WHERE idzona = ?;";
        int retorno;
        try (PreparedStatement pst = this.conexion.prepareStatement(sql)) {
            pst.setInt(1, registro.getIdzona());
            pst.setInt(2, registro.getIdpastor());
            pst.setString(3, registro.getNombrezona());
            pst.setInt(4, registro.getIdzona());

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
    public Zona getRegistro(List<Parametro> filtros) {
        return null;
    }

    @Override
    public List<Zona> listRegistros(int id) {
        return null;
    }

}