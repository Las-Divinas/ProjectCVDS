package edu.eci.cvds.sampleprj.dao.mybatis;

import edu.eci.cvds.sampleprj.dao.ElementoDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Element;

public class MyBatisElementoDAO implements ElementoDAO {

    /* @Inject
    private ElementoMapper elementoMapper; */

    @Override
    public void registrarElemento(Element element) throws PersistenceException {
        try {
            /* elementoMapper.registrarElemento(element); */
        } catch (Exception e) {
            throw new PersistenceException("Error al ingresar el nuevo elemento");
        }

    }
    
}
