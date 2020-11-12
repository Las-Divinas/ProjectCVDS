package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import java.util.List;
import edu.eci.cvds.sampleprj.dao.ElementoDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ElementoMapper;
import edu.eci.cvds.samples.entities.Element;

public class MyBatisElementoDAO implements ElementoDAO {

    @Inject
    private ElementoMapper elementoMapper;

    @Override
    public void registrarElemento(Element element) throws PersistenceException {
        try {
            elementoMapper.registrarElemento(element);
        } catch (Exception e) {
            throw new PersistenceException("Error al ingresar el nuevo elemento");
        }

    }

    @Override
    public int consultarUltimoId() throws PersistenceException {
        try {
            return elementoMapper.consultarUltimoId();
        } catch (Exception e) {
            throw new PersistenceException("Error al consultar el ultimo id de los elementos");
        }
    }

    @Override
    public Element consultarElementoPorId(int id) throws PersistenceException {
        try {
            return elementoMapper.consultarElementoPorId(id);
        } catch (Exception e) {
            throw new PersistenceException(e.toString());
        }
    }
    
    @Override
    public List<Element> consultarElementos() throws PersistenceException {
        try {
            return elementoMapper.consultarElementos();
        } catch (Exception e) {
            throw new PersistenceException(e.toString());
        }
    }
}
