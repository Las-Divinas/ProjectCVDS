package edu.eci.cvds.sampleprj.dao;
import java.util.List;
import edu.eci.cvds.samples.entities.Element;

public interface ElementoDAO {
    public void registrarElemento(Element element) throws PersistenceException;
    public int consultarUltimoId() throws PersistenceException;
    public Element consultarElementoPorId(int id) throws PersistenceException;      
    public List<Element> consultarElementos() throws PersistenceException;
    public void cambiarIdEquipo(int idEquipment, int idElement) throws PersistenceException;
}
