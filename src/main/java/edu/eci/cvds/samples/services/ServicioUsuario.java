package edu.eci.cvds.samples.services;

import edu.eci.cvds.samples.entities.Usuario;

public interface ServicioUsuario {
    void registrarUsuario(Usuario u) throws ExceptionHistorialDeEquipos;
    Usuario consultarIdUsuarioPorCorreo(String correo) throws ExceptionHistorialDeEquipos;
    String consultarNombreUsuario(String usuarioID) throws ExceptionHistorialDeEquipos;
}
