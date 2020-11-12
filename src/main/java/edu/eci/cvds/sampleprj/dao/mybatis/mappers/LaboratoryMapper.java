package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Laboratory;

public interface LaboratoryMapper {
    public void registrarLaboratorio(@Param("laboratorio") Laboratory lab);
    public int consultarUltimoId();
    public List<Laboratory> consultarLaboratorios();
}
