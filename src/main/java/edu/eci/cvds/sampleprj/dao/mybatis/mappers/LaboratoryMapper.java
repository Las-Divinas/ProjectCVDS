package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Laboratory;

public interface LaboratoryMapper {
    public void registrarLaboratorio(@Param("laboratorio") Laboratory lab);
    public int consultarUltimoId();
}
