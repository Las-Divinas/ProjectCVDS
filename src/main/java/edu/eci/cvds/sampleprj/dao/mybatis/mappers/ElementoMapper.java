package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Element;

public interface ElementoMapper {
    public void registrarElemento(@Param("elemento") Element elemento);
    
}
