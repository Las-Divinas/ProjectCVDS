package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Novelty;

public interface NovedadMapper {
    public void registrarNovedad(@Param("novedad") Novelty novedad);
    
}
