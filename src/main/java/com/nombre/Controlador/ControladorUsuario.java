
package com.nombre.Controlador;

import com.nombre.Modelo.Clases.Usuario;
import com.nombre.Modelo.Persistencia.ConexionBD;
import com.nombre.Modelo.Persistencia.Operaciones;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class ControladorUsuario {
    private static int generarIdUsuarios() throws SQLException {
        Operaciones.setConnection(ConexionBD.MySQLConnection());
        String sentencia = "SELECT MAX(idusers) FROM users;";
        PreparedStatement ps = Operaciones.getConnection().prepareStatement(sentencia);
        ResultSet rs = Operaciones.consultar_BD(ps);
    
        int maxId = 0;
    
        if (rs.next()) {
            maxId = rs.getInt(1);
        }
    
        Operaciones.cerrarConexion();
    
        return maxId + 1;
    }
    
    public static boolean registrarUsuario(String nombre) throws SQLException {
        int nuevoId = generarIdUsuarios();
        Usuario u = new Usuario(nuevoId, nombre);
        Operaciones.setConnection(ConexionBD.MySQLConnection());
        String sentencia = "INSERT INTO users(idusers, name) VALUES (?, ?);";
        PreparedStatement ps = Operaciones.getConnection().prepareStatement(sentencia);
        ps.setInt(1, u.getIdusers());
        ps.setString(2, u.getName());
    
        if (Operaciones.setAutoCommitBD(false)) {
            if (Operaciones.insertar_actualizar_borrar_BD(ps) > 0) {
                Operaciones.commitBD();
                Operaciones.cerrarConexion();
                return true;
            } else {
                Operaciones.rollbackBD();
                Operaciones.cerrarConexion();
                return false;
            }
        } else {
            Operaciones.cerrarConexion();
            return false;
        }
    }
    

    public static boolean actualizarUsuario(int id, String nombre) throws SQLException {
        Usuario u = new Usuario(id, nombre);
        Operaciones.setConnection(ConexionBD.MySQLConnection());
        String sentencia = "UPDATE users SET name = ? WHERE idusers = ?";
        PreparedStatement ps = Operaciones.getConnection().prepareStatement(sentencia);
        ps.setString(1, u.getName());
        ps.setInt(2, u.getIdusers());
        if (Operaciones.setAutoCommitBD(false)) {
            if (Operaciones.insertar_actualizar_borrar_BD(ps) > 0) {
                Operaciones.commitBD();
                Operaciones.cerrarConexion();
                return true;
            } else {
                Operaciones.rollbackBD();
                Operaciones.cerrarConexion();
                return false;
            }
        } else {
            Operaciones.cerrarConexion();
            return false;
        }
    }

    public static boolean borrarUsuario(String nombre) throws SQLException {
        Usuario u = new Usuario(nombre);
        Operaciones.setConnection(ConexionBD.MySQLConnection());
        String sentencia = "DELETE FROM users WHERE name = ?";
        PreparedStatement ps = Operaciones.getConnection().prepareStatement(sentencia);
        ps.setString(1, u.getName());
        if (Operaciones.setAutoCommitBD(false)) {
            if (Operaciones.insertar_actualizar_borrar_BD(ps) > 0) {
                Operaciones.commitBD();
                Operaciones.cerrarConexion();
                return true;
            } else {
                Operaciones.rollbackBD();
                Operaciones.cerrarConexion();
                return false;
            }
        } else {
            Operaciones.cerrarConexion();
            return false;
        }
    }

    public static List<Usuario> ConsultarUsuario(String nombre) throws SQLException {
        List<Usuario> users = new ArrayList<>();
        Operaciones.setConnection(ConexionBD.MySQLConnection());
        String sentencia = "SELECT * FROM users WHERE name = ?";
        PreparedStatement ps = Operaciones.getConnection().prepareStatement(sentencia);
        ps.setString(1, nombre);
        ResultSet rs = Operaciones.consultar_BD(ps);
        while (rs.next()) {
            String name = rs.getString("name");
            int idusers = rs.getInt("idusers");
            Usuario u = new Usuario(idusers, name);
            users.add(u);
        }

        Operaciones.cerrarConexion();
        return users;

    }
}
