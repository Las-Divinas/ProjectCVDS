package edu.eci.cvds.samples.services;

import java.util.List;

import edu.eci.cvds.samples.entities.Laboratory;

public interface ServicioLaboratory {
    public abstract void registrarLaboratorio(Laboratory l) throws ExceptionHistorialDeEquipos;
    public abstract int consultarUltimoIdLaboratorio() throws ExceptionHistorialDeEquipos;
    public abstract List<Laboratory> consultarLaboratorios() throws ExceptionHistorialDeEquipos;
    String consultarNombreLaboratorio(Integer laboratorioID) throws ExceptionHistorialDeEquipos;
}
