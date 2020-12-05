package edu.eci.cvds.samples.services;

import java.util.List;

import edu.eci.cvds.samples.entities.Element;

public interface ServicioElement {
    void registrarElemento(Element e) throws Exception;
    int consultarUltimoIdElement() throws ExceptionHistorialDeEquipos;
    Element consultarElementoPorId(int id) throws ExceptionHistorialDeEquipos;
    List<Element> consultarElementos() throws ExceptionHistorialDeEquipos;
    void cambiarIdEquipoParaElemento(Integer idEquipment, Integer idElement) throws ExceptionHistorialDeEquipos;
    void eliminarElementosPorId(int id) throws ExceptionHistorialDeEquipos;
    void cambiarEstadoElementosId(int id,String estado) throws ExceptionHistorialDeEquipos;
    Element seleccionarElementoPorIdEquipo(int idEquipment, String tipo) throws ExceptionHistorialDeEquipos;
    String consultarNombreElemento(Integer elementoID) throws ExceptionHistorialDeEquipos;
    List<Element> consultarElementoNoAsociado(String type) throws ExceptionHistorialDeEquipos;
    List<Element> consultarElementosPorEquipo(Integer equipoID) throws ExceptionHistorialDeEquipos;
    Integer consultarElementoIDPorNombre(String nombreElemento) throws ExceptionHistorialDeEquipos;
}
