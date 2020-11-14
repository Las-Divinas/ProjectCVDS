package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Novelty;

import java.util.List;

public interface NovedadMapper {
    public void registrarNovedad(@Param("novedad") Novelty novedad);
    public List<Novelty> consultarNovedades();
}
