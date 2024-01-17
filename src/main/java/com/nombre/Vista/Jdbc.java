package com.nombre.jdbc.Vista;

import com.nombre.jdbc.Controlador.ControladorUsuario;
import com.nombre.jdbc.Modelo.Clases.Usuario;
import java.sql.*;
import java.util.List;

public class Jdbc {

    public static void main(String[] args) throws SQLException {
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
