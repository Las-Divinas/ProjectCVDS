package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Usuario;

public interface UsuarioMapper {
    public void registrarUsuario(@Param("usuario") Usuario usuario);
}
