package com.nombre.Vista;

import com.nombre.Controlador.ControladorUsuario;
import com.nombre.Modelo.Clases.Usuario;
import java.sql.*;
import java.util.List;

public class Jdbc {

    public static void main(String[] args) throws SQLException ,ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println(ControladorUsuario.registrarUsuario("Pepe"));

        List<Usuario> users = ControladorUsuario.ConsultarUsuario("Pepe");
        for (Usuario user : users) {
            System.out.println(user);
        }

        // System.out.println(ControladorUsuario.borrarUsuario("Pepe"));

        List<Usuario> users2 = ControladorUsuario.ConsultarUsuario("Pepe");
        for (Usuario user : users2) {
            System.out.println(user);
        }
    }
}
