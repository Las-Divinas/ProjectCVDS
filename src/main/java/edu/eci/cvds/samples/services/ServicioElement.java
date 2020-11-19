package edu.eci.cvds.samples.services;

import java.util.List;

import edu.eci.cvds.samples.entities.Element;

public interface ServicioElement {
    public abstract void registrarElemento(Element e) throws ExceptionHistorialDeEquipos;
    public abstract int consultarUltimoIdElement() throws ExceptionHistorialDeEquipos;
    public abstract Element consultarElementoPorId(int id) throws ExceptionHistorialDeEquipos;
    public abstract List<Element> consultarElementos() throws ExceptionHistorialDeEquipos;
    public abstract void cambiarIdEquipoParaElemento(int idEquipment, int idElement) throws ExceptionHistorialDeEquipos;
    public abstract void eliminarElementosPorId(int id) throws ExceptionHistorialDeEquipos;
    public abstract void cambiarEstadoElementosId(int id,String estado) throws ExceptionHistorialDeEquipos;
}
