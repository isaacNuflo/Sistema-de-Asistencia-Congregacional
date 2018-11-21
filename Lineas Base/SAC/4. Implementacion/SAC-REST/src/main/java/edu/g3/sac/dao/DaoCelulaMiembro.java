package edu.g3.sac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.g3.sac.dao.beans.CelulaMiembro;;

public class DaoCelulaMiembro implements Dao<CelulaMiembro> {

    private Connection conexion;

    public DaoCelulaMiembro(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public CelulaMiembro getRegistro(int id) {
        return null;
    }

    @Override
    public List<CelulaMiembro> listRegistros() {
        String sql = "SELECT * FROM celula_miembro ";
        List<CelulaMiembro> registros;
        try (PreparedStatement pst = this.conexion.prepareStatement(sql)) {
            try (ResultSet rs = pst.executeQuery()) {
                registros = new ArrayList<>();
                while (rs.next()) {
                    CelulaMiembro registro = new CelulaMiembro();
                    registro.setIdcelula(rs.getInt("idcelula"));
                    registro.setIdmiembro(rs.getInt("idmiembro"));
                    registro.setLider(rs.getBoolean("lider"));

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
        String sql = String.format("DELETE FROM celula_miembro %s ", this.condicional(filtros));
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
    public boolean insertRegistro(CelulaMiembro registro) {
        boolean retorno;
        String sql = "INSERT INTO public.celula_miembro(idcelula, idmiembro, lider)\n" + "	VALUES (?, ?, ?);\n";
        try (PreparedStatement pst = this.conexion.prepareStatement(sql)) {
            pst.setInt(1, registro.getIdcelula());
            pst.setInt(2, registro.getIdmiembro());
            pst.setBoolean(3, registro.isLider());

            pst.execute();
            retorno = true;
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            retorno = false;
        }
        return retorno;
    }

    @Override
    public int updateRegistro(CelulaMiembro registro) {
        String sql = "UPDATE public.asistencia\n" +
                "SET idcelula=?, idmiembro=?, lider=? \n" +
                "WHERE idcelula = ? AND idmiembro = ?;";
        int retorno;
        try(PreparedStatement pst = this.conexion.prepareStatement(sql)){
            pst.setInt(1, registro.getIdcelula());
            pst.setInt(2, registro.getIdmiembro());
            pst.setBoolean(3, registro.isLider());
            pst.setInt(4, registro.getIdcelula());
            pst.setInt(5, registro.getIdmiembro());

            retorno = pst.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace(System.err);
            retorno = -1;
        }
        return retorno;
    }

    @Override
    public CelulaMiembro getRegistro(List<Parametro> filtros) {
        String sql = String.format("SELECT * FROM celula_miembro %s ", this.condicional(filtros));
        CelulaMiembro registro = null;
        try (PreparedStatement pst = this.conexion.prepareStatement(sql)) {
            int i = 1;
            for (Parametro p : filtros)
                pst.setObject(i++, p.getValor(), p.getTipo());

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    registro = new CelulaMiembro();
                    registro.setIdcelula(rs.getInt("idcelula"));
                    registro.setIdmiembro(rs.getInt("idmiembro"));
                    registro.setLider(rs.getBoolean("lider"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return registro;
    }

    @Override
    public List<CelulaMiembro> listRegistros(int id) {
        String sql = "SELECT * FROM celula_miembro" + "WHERE idcelula = ?";
        List<CelulaMiembro> registros;
        try (PreparedStatement pst = this.conexion.prepareStatement(sql)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                registros = new ArrayList<>();
                while (rs.next()) {
                    CelulaMiembro registro = new CelulaMiembro();
                    registro.setIdcelula(rs.getInt("idcelula"));
                    registro.setIdmiembro(rs.getInt("idmiembro"));
                    registro.setLider(rs.getBoolean("lider"));

                    registros.add(registro);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            registros = null;
        }
        return registros;
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