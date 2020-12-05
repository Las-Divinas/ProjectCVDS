package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Laboratory;

public interface LaboratoryMapper {
    void registrarLaboratorio(@Param("laboratorio") Laboratory lab);
    int consultarUltimoId();
    List<Laboratory> consultarLaboratorios();
    String consultarNombreLaboratorio(@Param("laboratorioID") Integer laboratorioID);
    Integer consultarIDLaboratorioPorNombre(@Param("nombreLaboratorio") String nombreLaboratorio);
    List<Laboratory> laboratorioActivo();
    void cambiarEstadoLaboratorio(@Param("estadoLaboratorio") String estadoLaboratorio, @Param("laboratorioID") Integer laboratorioID);
    void cambiarFechaDeCierre(@Param("fechaCierre") Date fechaCierre, @Param("laboratorioID") Integer laboratorioID);
    List<Laboratory> consultarLaboratoriosActivos();
}
