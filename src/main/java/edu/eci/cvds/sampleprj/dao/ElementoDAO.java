package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Element;

public interface ElementoDAO {
    public void registrarElemento(Element element) throws PersistenceException;
    
}
