package edu.eci.cvds.samples.services.impl;

import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.sampleprj.dao.ElementoDAO;
import edu.eci.cvds.samples.entities.Element;
import edu.eci.cvds.samples.services.ExceptionHistorialDeEquipos;
import edu.eci.cvds.samples.services.ServicioElement;

public class ServicioElementImpl implements ServicioElement {

    @Inject
    private ElementoDAO elementoDAO;

    @Override
    public void registrarElemento(Element element) throws ExceptionHistorialDeEquipos {
        try {
            elementoDAO.registrarElemento(element);
        } catch (Exception e) {
            throw new ExceptionHistorialDeEquipos("Error al registrar el elemento");
        }
    }

    @Override
    public int consultarUltimoIdElement() throws ExceptionHistorialDeEquipos {
        try {
            return elementoDAO.consultarUltimoId();
        } catch (Exception e) {
            throw new ExceptionHistorialDeEquipos("Error al consultar el ultimo Id de los elementos");
        }
    }

    @Override
    public Element consultarElementoPorId(int id) throws ExceptionHistorialDeEquipos {
        try {
            return elementoDAO.consultarElementoPorId(id);
        } catch (Exception e) {
            throw new ExceptionHistorialDeEquipos("Error al consultar el elmento con el Id "+id);
        }
    }  

    @Override
    public List<Element> consultarElementos() throws ExceptionHistorialDeEquipos {
        try {
            return elementoDAO.consultarElementos();
        } catch (Exception e) {
            throw new ExceptionHistorialDeEquipos("Error al consultar los elementos");
        }
    }

    @Override
    public void cambiarIdEquipoParaElemento(int idEquipment, int idElement) throws ExceptionHistorialDeEquipos {
        try {
            elementoDAO.cambiarIdEquipo(idEquipment, idElement);
        } catch (Exception e) {
            throw new ExceptionHistorialDeEquipos(e.toString());
        }

    }

    @Override
    public void eliminarElementosPorId(int id) throws ExceptionHistorialDeEquipos {
        try {
            elementoDAO.eliminarElementosPorId(id);
        } catch (Exception e) {
            throw new ExceptionHistorialDeEquipos("Error al eliminar el elemento con id "+id);
        }
    }

    @Override
    public void cambiarEstadoElementosId(int id,String estado) throws ExceptionHistorialDeEquipos {
        try {
            elementoDAO.cambiarEstadoElementosId(id,estado);
        } catch (Exception e) {
            throw new ExceptionHistorialDeEquipos(e.toString());
        }
    }
}
