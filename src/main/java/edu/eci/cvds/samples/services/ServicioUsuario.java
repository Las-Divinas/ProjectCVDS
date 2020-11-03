package edu.eci.cvds.samples.services;

import edu.eci.cvds.samples.entities.Laboratory;
import edu.eci.cvds.samples.entities.Usuario;

public interface ServicioUsuario {
    public abstract void registrarUsuario(Usuario u) throws ExceptionHistorialDeEquipos;
    public abstract void registrarLaboratorio(Laboratory l) throws ExceptionHistorialDeEquipos;
}
