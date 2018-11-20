package edu.g3.sac.dao;

import edu.g3.sac.dao.beans.Miembro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoMiembro implements Dao<Miembro>{

    private Connection conexion;
    public DaoMiembro(Connection conexion){
        this.conexion = conexion;
    }

    @Override
    public Miembro getRegistro(int id) {
        String sql = "SELECT * FROM miembro " +
                "WHERE idmiembro = ? ;";
        Miembro registro = null;
        try(PreparedStatement pst = this.conexion.prepareStatement(sql)){
            pst.setInt(1,id);
            try(ResultSet rs = pst.executeQuery()){
                if(rs.next()){
                    registro = new Miembro();
                    registro.setIdmiembro(rs.getInt("idmiembro"));
                    registro.setNumeromiembro(rs.getString("numeromiembro"));
                    registro.setNombres(rs.getString("nombres"));
                    registro.setApellidos(rs.getString("apellidos"));
                    registro.setEdad(rs.getInt("edad"));
                    registro.setFechanacimiento(rs.getDate("fechanacimiento"));
                    registro.setTelefono(rs.getString("telefono"));
                    registro.setDireccion(rs.getString("direccion"));
                    registro.setBautizado(rs.getBoolean("bautizado"));
                    registro.setCargo(rs.getString("cargo"));
                    registro.setRol(rs.getBoolean("rol"));
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace(System.err);
        }
        return registro;
    }

    @Override
    public List<Miembro> listRegistros() {
        String sql = "SELECT * FROM miembro ";
        List<Miembro> registros;
        try(PreparedStatement pst = this.conexion.prepareStatement(sql)){
            try(ResultSet rs = pst.executeQuery()){
                registros = new ArrayList<>();
                while(rs.next()){
                    Miembro registro = new Miembro();
                    registro.setIdmiembro(rs.getInt("idmiembro"));
                    registro.setNumeromiembro(rs.getString("numeromiembro"));
                    registro.setNombres(rs.getString("nombres"));
                    registro.setApellidos(rs.getString("apellidos"));
                    registro.setEdad(rs.getInt("edad"));
                    registro.setFechanacimiento(rs.getDate("fechanacimiento"));
                    registro.setTelefono(rs.getString("telefono"));
                    registro.setDireccion(rs.getString("direccion"));
                    registro.setBautizado(rs.getBoolean("bautizado"));
                    registro.setCargo(rs.getString("cargo"));
                    registro.setRol(rs.getBoolean("rol"));

                    registros.add(registro);
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace(System.err);
            registros = null;
        }
        return registros;
    }

    @Override
    public int deleteRegistro(List<Parametro> filtros) {
        int afectados;
        String sql = String.format("DELETE FROM miembro %s ",this.condicional(filtros));
        try(PreparedStatement pst = this.conexion.prepareStatement(sql)){
            int i = 1;
            for(Parametro p : filtros)
                pst.setObject(i++,p.getValor(),p.getTipo());

            afectados = pst.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace(System.err);
            afectados = -1;
        }
        return afectados;
    }

    @Override
    public boolean insertRegistro(Miembro registro) {
        boolean retorno;
        String sql = "INSERT INTO public.miembro(numeromiembro, nombres, apellidos, edad, fechanacimiento,telefono, direccion, bautizado, cargo, rol)\n" +
                "    VALUES (?,?,?,?, ?,?, ?, ?, ?, ?);\n";
        try(PreparedStatement pst = this.conexion.prepareStatement(sql)){
            pst.setString(1,registro.getNumeromiembro());
            pst.setString(2,registro.getNombres());
            pst.setString(3,registro.getApellidos());
            pst.setInt(4,registro.getEdad());
            pst.setDate(5,registro.getFechanacimiento());
            pst.setString(6,registro.getTelefono());
            pst.setString(7,registro.getDireccion());
            pst.setBoolean(8,registro.isBautizado());
            pst.setString(9,registro.getCargo());
            pst.setBoolean(10,registro.isRol());

            pst.execute();
            retorno = true;
        }
        catch (SQLException e){
            e.printStackTrace(System.err);
            retorno = false;
        }
        return retorno;
    }

    @Override
    public int updateRegistro(Miembro registro) {
        String sql = "UPDATE public.miembro\n" +
                "SET idmiembro=?, numeromiembro=?, nombres=?, apellidos=?, edad=?, \n" +
                "fechanacimiento=?, telefono=?, direccion=?, bautizado=?, cargo=?, rol=?\n" +
                "WHERE idmiembro = ?;";
        int retorno;
        try(PreparedStatement pst = this.conexion.prepareStatement(sql)){
            pst.setInt(1,registro.getIdmiembro());
            pst.setString(2,registro.getNumeromiembro());
            pst.setString(3,registro.getNombres());
            pst.setString(4,registro.getApellidos());
            pst.setInt(5,registro.getEdad());
            pst.setDate(6,registro.getFechanacimiento());
            pst.setString(7,registro.getTelefono());
            pst.setString(8,registro.getDireccion());
            pst.setBoolean(9,registro.isBautizado());
            pst.setString(10,registro.getCargo());
            pst.setBoolean(11,registro.isRol());
            pst.setInt(12,registro.getIdmiembro());

            retorno = pst.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace(System.err);
            retorno = -1;
        }
        return retorno;
    }


    private String condicional(List<Parametro> parametros){
        String retorno;
        if(parametros == null)
            retorno = "";
        else if(parametros.size() == 0)
            retorno = "";
        else{
            StringBuilder builder = new StringBuilder(" WHERE ");
            parametros.forEach(p -> builder.append(String.format(" %s = ? AND ",p.getParamName())));
            builder.delete(builder.length()-4,builder.length());
            retorno = builder.toString();
        }
        return retorno;
    }

    @Override
    public Miembro getRegistro(List<Parametro> filtros) {
        return null;
    }

    @Override
    public List<Miembro> listRegistros(int id) {
        return null;
    }
}
