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
    public abstract int consultarUltimoId() throws ExceptionHistorialDeEquipos;
    public abstract int consultarUltimoIdElement() throws ExceptionHistorialDeEquipos;
    public abstract Usuario consultarIdUsuarioPorCorreo(String correo) throws ExceptionHistorialDeEquipos;
    public abstract Element consultarElementoPorId(int id) throws ExceptionHistorialDeEquipos;
    public abstract int consultarUltimoIdLaboratorio() throws ExceptionHistorialDeEquipos;
    public abstract Equipment consultarEquipoPorId(int id) throws ExceptionHistorialDeEquipos;
    public abstract void cambiarLaboratorioEquipo(int idLaboratory, int idEquipment) throws ExceptionHistorialDeEquipos;
    public abstract List<Laboratory> consultarLaboratorios() throws ExceptionHistorialDeEquipos;
    public int consultarNumeroEquipos(int laboratory_id) throws ExceptionHistorialDeEquipos;
    public abstract List<Element> consultarElementos() throws ExceptionHistorialDeEquipos;
    public abstract List<Novelty> consultarNovedades() throws ExceptionHistorialDeEquipos;
    public abstract void eliminarEquipoPorId(int id) throws ExceptionHistorialDeEquipos;
    public abstract void cambiarEstadoElementoId(int id,String estado) throws ExceptionHistorialDeEquipos;
}
