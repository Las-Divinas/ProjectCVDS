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
            throw new PersistenceException(e.getMessage());
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

    @Override
    public void cambiarIdEquipo(Integer idEquipment, Integer idElement) throws PersistenceException {
        try {
            elementoMapper.cambiarIdEquipo(idEquipment, idElement);
        } catch (Exception e) {
            throw new PersistenceException(e.toString());
        }

    }

    @Override
    public void eliminarElementosPorId(int id) throws PersistenceException {
        try {
            elementoMapper.eliminarElementosPorId(id);
        } catch (Exception e) {
            throw new PersistenceException("Error al eliminar el elemento con id "+id);
        }

    }

    @Override
    public void cambiarEstadoElementosId(int id,String estado) throws PersistenceException {
        try {
            elementoMapper.cambiarEstadoElementosId(id,estado);
        } catch (Exception e) {
            throw new PersistenceException("Error al cambiar el estado del elemento con Id "+id);
        }

    }

    @Override
    public Element selecionarElementoPorIdEquipo(int idEquipment, String tipo) throws PersistenceException {
        try {
            return elementoMapper.seleccionarElementoPorIdEquipo(idEquipment, tipo);
        } catch (Exception e) {
            throw new PersistenceException(e.toString());
        }
    }

    @Override
    public String consultarNombreElemento(Integer elementoID) throws PersistenceException{
        try {
            if(elementoID!=null){
                return elementoMapper.consultarNombreElemento(elementoID);
            }

            return "N/A";
        } catch (Exception e) {
            throw new PersistenceException("Error al consultar nombre de elemento");
        }
    }

    @Override
    public List<Element> consultarElementoNoAsociado(String type) throws PersistenceException {
        try {
            return elementoMapper.consultarElementoNoAsociado(type);
        } catch (Exception e) {
            throw new PersistenceException("Error al consultar elementos nos asociados");
        }
    }

    @Override
    public List<Element> consultarElementosPorEquipo(Integer equipoID) throws PersistenceException {
        try {
            return elementoMapper.consultarElementosPorEquipo(equipoID);
        } catch (Exception e) {
            throw new PersistenceException("Error al consultar elementos de un equipo");
        }
    }

    @Override
    public Integer consultarElementoIDPorNombre(String nombreElemento) throws PersistenceException {
        try {
            return elementoMapper.consultarElementoIDPorNombre(nombreElemento);
        } catch (Exception e) {
            throw new PersistenceException("Error al consultar ID elemento");
        }
    }
}
