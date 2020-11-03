package edu.eci.cvds.samples.services.impl;

import com.google.inject.Inject;

import edu.eci.cvds.sampleprj.dao.LaboratoryDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.UsuarioDAO;
import edu.eci.cvds.samples.entities.Laboratory;
import edu.eci.cvds.samples.entities.Usuario;
import edu.eci.cvds.samples.services.ExceptionHistorialDeEquipos;
import edu.eci.cvds.samples.services.ServicioUsuario;

public class ServicioUsuarioImpl implements ServicioUsuario {
    @Inject
    private UsuarioDAO usuarioDAO;
    private LaboratoryDAO laboratoryDAO;

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
            throw new ExceptionHistorialDeEquipos(e.toString());
        }
    }
    
}
