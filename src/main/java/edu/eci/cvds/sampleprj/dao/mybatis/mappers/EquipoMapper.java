package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Equipment;

public interface EquipoMapper {
    public void registrarEquipo(@Param("equipo") Equipment equipo);
    
}