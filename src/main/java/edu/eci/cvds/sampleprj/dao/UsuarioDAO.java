package edu.eci.cvds.sampleprj.dao;


import edu.eci.cvds.samples.entities.Usuario;

public interface UsuarioDAO {
    void registrarUsuario(Usuario u) throws PersistenceException;
    Usuario consultarIdPorCorreo(String correo) throws PersistenceException;
    String consultarNombreUsuario(String usuarioID) throws PersistenceException;
}
