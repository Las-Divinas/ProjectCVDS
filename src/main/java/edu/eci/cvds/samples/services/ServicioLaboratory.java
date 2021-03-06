package edu.eci.cvds.samples.services;

import java.util.Date;
import java.util.List;

import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Laboratory;

public interface ServicioLaboratory {
    void registrarLaboratorio(Laboratory l) throws ExceptionHistorialDeEquipos;
    int consultarUltimoIdLaboratorio() throws ExceptionHistorialDeEquipos;
    List<Laboratory> consultarLaboratorios() throws ExceptionHistorialDeEquipos;
    String consultarNombreLaboratorio(Integer laboratorioID) throws ExceptionHistorialDeEquipos;
    Integer consultarIDLaboratorioPorNombre(String nombreLaboratorio) throws ExceptionHistorialDeEquipos;
    List<Laboratory> laboratorioActivo() throws ExceptionHistorialDeEquipos;
    void cambiarEstadoLaboratorio(String estadoLaboratorio, Integer laboratorioID) throws ExceptionHistorialDeEquipos;
    void cambiarFechaDeCierre(Date fechaCierre, Integer laboratorioID) throws ExceptionHistorialDeEquipos;
    List<Laboratory> consultarLaboratoriosActivos() throws ExceptionHistorialDeEquipos;
}
