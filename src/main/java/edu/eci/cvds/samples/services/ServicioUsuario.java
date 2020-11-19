package edu.eci.cvds.samples.services;

import edu.eci.cvds.samples.entities.Usuario;

public interface ServicioUsuario {
    public abstract void registrarUsuario(Usuario u) throws ExceptionHistorialDeEquipos;
    public abstract Usuario consultarIdUsuarioPorCorreo(String correo) throws ExceptionHistorialDeEquipos;
}
