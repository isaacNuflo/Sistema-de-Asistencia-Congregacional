package edu.g3.sac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.g3.sac.dao.beans.Celula;

public class DaoCelula implements Dao<Celula> {

    private Connection conexion;

    public DaoCelula(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public Celula getRegistro(int id) {
        String sql = "SELECT * FROM celula " + "WHERE idcelula = ? ;";
        Celula registro = null;
        try (PreparedStatement pst = this.conexion.prepareStatement(sql)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    registro = new Celula();
                    registro.setIdcelula(rs.getInt("idcelula"));
                    registro.setIdzona(rs.getInt("idzona"));
                    registro.setNumerocelula(rs.getInt("numerocelula"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return registro;
    }

    @Override
    public List<Celula> listRegistros() {
        String sql = "SELECT * FROM celula ";
        List<Celula> registros;
        try (PreparedStatement pst = this.conexion.prepareStatement(sql)) {
            try (ResultSet rs = pst.executeQuery()) {
                registros = new ArrayList<>();
                while (rs.next()) {
                    Celula registro = new Celula();
                    registro.setIdcelula(rs.getInt("idcelula"));
                    registro.setIdzona(rs.getInt("idzona"));
                    registro.setNumerocelula(rs.getInt("numerocelula"));

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
        String sql = String.format("DELETE FROM celula %s ", this.condicional(filtros));
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
    public boolean insertRegistro(Celula registro) {
        boolean retorno;
        String sql = "INSERT INTO public.celula(idzona, numerocelula)\n" +
                     "	VALUES (?, ?);\n";
        try (PreparedStatement pst = this.conexion.prepareStatement(sql)) {
            pst.setInt(1, registro.getIdzona());
            pst.setInt(2, registro.getNumerocelula());

            pst.execute();
            retorno = true;
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            retorno = false;
        }
        return retorno;
    }

    @Override
    public int updateRegistro(Celula registro) {
        String sql = "UPDATE public.celula\n" +
                "SET idcelula=?, idzona=?, numerocelula=? \n" +
                "WHERE idcelula = ?;";
        int retorno;
        try(PreparedStatement pst = this.conexion.prepareStatement(sql)){
            pst.setInt(1, registro.getIdcelula());
            pst.setInt(2, registro.getIdzona());
            pst.setInt(3, registro.getNumerocelula());
            pst.setInt(4, registro.getIdcelula());

            retorno = pst.executeUpdate();
        }
        catch (SQLException e){
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
    public Celula getRegistro(List<Parametro> filtros) {
        return null;
    }

    @Override
    public List<Celula> listRegistros(int id) {
        return null;
    }

}