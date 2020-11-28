package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Novelty;

import java.util.List;

public interface NovedadMapper {
    void registrarNovedad(@Param("novedad") Novelty novedad);
    List<Novelty> consultarNovedades();
    List<Novelty> consultarNovedadesPorIDEquipo(@Param("equipmentID") Integer equipmentID);
    List<Novelty> consultarNovedadesPorIDElemento(@Param("elementID") Integer elementID);
    List<Novelty> consultarNovedadesPorIDLaboratorio(@Param("laboratorioID") Integer laboratorioID);
}
