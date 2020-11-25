package edu.eci.cvds.sampleprj.dao;
import java.util.List;


import edu.eci.cvds.samples.entities.Element;

public interface ElementoDAO {
    void registrarElemento(Element element) throws Exception;
    int consultarUltimoId() throws PersistenceException;
    Element consultarElementoPorId(int id) throws PersistenceException;
    List<Element> consultarElementos() throws PersistenceException;
    void cambiarIdEquipo(Integer idEquipment, Integer idElement) throws PersistenceException;
    void eliminarElementosPorId(int id) throws PersistenceException;
    void cambiarEstadoElementosId(int id, String estado) throws PersistenceException;
    Element selecionarElementoPorIdEquipo(int idEquipment, String tipo) throws PersistenceException;
    String consultarNombreElemento(Integer elementoID) throws PersistenceException;
    List<Element> consultarElementoNoAsociado(String type) throws PersistenceException;
}
