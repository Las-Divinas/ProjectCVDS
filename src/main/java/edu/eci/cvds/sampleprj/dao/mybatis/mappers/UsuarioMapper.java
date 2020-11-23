package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Usuario;

public interface UsuarioMapper {
    void registrarUsuario(@Param("usuario") Usuario usuario);
    Usuario consultarIdPorCorreo(@Param("correo") String correo);
    String consultarNombreUsuario(@Param("userID") String userID);
}
