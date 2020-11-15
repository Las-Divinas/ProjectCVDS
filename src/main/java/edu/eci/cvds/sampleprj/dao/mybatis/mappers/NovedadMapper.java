package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import edu.eci.cvds.samples.entities.Element;
import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Novelty;

import java.util.List;

public interface NovedadMapper {
    public void registrarNovedad(@Param("novedad") Novelty novedad);
    public List<Novelty> consultarNovedades();
    public List<Novelty> consultarNovedadesPorIDEquipo(@Param("equipmentID") int equipmentID);
    public List<Novelty> consultarNovedadesPorIDElemento(@Param("elementID") int elementID);
}
