package edu.eci.cvds.sampleprj.dao;


import edu.eci.cvds.samples.entities.Usuario;

public interface UsuarioDAO {
    public void registrarUsuario(Usuario u) throws PersistenceException;

}
