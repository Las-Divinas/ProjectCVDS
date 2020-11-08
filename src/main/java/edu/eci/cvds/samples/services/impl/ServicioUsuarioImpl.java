package edu.eci.cvds.samples.services.impl;

import com.google.inject.Inject;

import edu.eci.cvds.sampleprj.dao.ElementoDAO;
import edu.eci.cvds.sampleprj.dao.EquipoDAO;
import edu.eci.cvds.sampleprj.dao.LaboratoryDAO;
import edu.eci.cvds.sampleprj.dao.NovedadDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.UsuarioDAO;
import edu.eci.cvds.samples.entities.Element;
import edu.eci.cvds.samples.entities.Equipment;
import edu.eci.cvds.samples.entities.Laboratory;
import edu.eci.cvds.samples.entities.Novelty;
import edu.eci.cvds.samples.entities.Usuario;
import edu.eci.cvds.samples.services.ExceptionHistorialDeEquipos;
import edu.eci.cvds.samples.services.ServicioUsuario;
import java.util.List;

public class ServicioUsuarioImpl implements ServicioUsuario {
    @Inject
    private UsuarioDAO usuarioDAO;
    @Inject
    private LaboratoryDAO laboratoryDAO;
    @Inject
    private EquipoDAO equipoDAO;
    @Inject
    private ElementoDAO elementoDAO;
    @Inject
    private NovedadDAO novedadDAO; 

    @Override
    public void registrarUsuario(Usuario u) throws ExceptionHistorialDeEquipos {
        try {
            usuarioDAO.registrarUsuario(u);
        } catch (PersistenceException e) {
            throw new ExceptionHistorialDeEquipos("Error al registrar el usuario");
        }
    }

    @Override
    public void registrarLaboratorio(Laboratory l) throws ExceptionHistorialDeEquipos {
        try {
            laboratoryDAO.registrarLaboratorio(l);
        } catch (PersistenceException e) {
            throw new ExceptionHistorialDeEquipos("Error al registrar el laboratorio");
        }
    }

    @Override
    public void registrarEquipment(Equipment e) throws ExceptionHistorialDeEquipos {
        try {
            equipoDAO.registrarEquipo(e);
        } catch (PersistenceException ex) {
            throw new ExceptionHistorialDeEquipos("Error al registrar el equipo");
        }
    }

    @Override
    public void registrarElemento(Element element) throws ExceptionHistorialDeEquipos {
        try {
            elementoDAO.registrarElemento(element);
        } catch (Exception e) {
            throw new ExceptionHistorialDeEquipos("Error al registrar el elemento");
        }

    }

    @Override
    public void registrarNovedad(Novelty n) throws ExceptionHistorialDeEquipos {
        try {
            novedadDAO.registrarNovedad(n);
        } catch (Exception e) {
            throw new ExceptionHistorialDeEquipos("Error al registrar la novedad");
        }
    }

    @Override
    public List<Equipment> consultarEquipos() throws ExceptionHistorialDeEquipos{
        try {
            return equipoDAO.consultarEquipos();
        } catch (Exception e) {
            throw new ExceptionHistorialDeEquipos("Error al consultar los equipos");
        }
    }

    @Override
    public int consultarUltimoId() throws ExceptionHistorialDeEquipos {
        try {
            return equipoDAO.consultarUltimoId();
        } catch (Exception e) {
            throw new ExceptionHistorialDeEquipos("Error al consultar el ultimo Id de los equipos");
        }
    }
}
