package edu.eci.cvds.samples.services;

import java.util.List;
import edu.eci.cvds.samples.entities.Element;
import edu.eci.cvds.samples.entities.Equipment;
import edu.eci.cvds.samples.entities.Laboratory;
import edu.eci.cvds.samples.entities.Novelty;
import edu.eci.cvds.samples.entities.Usuario;

public interface ServicioUsuario {
    public abstract void registrarUsuario(Usuario u) throws ExceptionHistorialDeEquipos;
    public abstract void registrarLaboratorio(Laboratory l) throws ExceptionHistorialDeEquipos;
    public abstract void registrarEquipment(Equipment e) throws ExceptionHistorialDeEquipos;
    public abstract void registrarElemento(Element e) throws ExceptionHistorialDeEquipos;
    public abstract void registrarNovedad(Novelty n) throws ExceptionHistorialDeEquipos;
    public abstract List<Equipment> consultarEquipos() throws ExceptionHistorialDeEquipos;
}
