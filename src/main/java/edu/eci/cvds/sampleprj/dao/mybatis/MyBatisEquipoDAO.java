package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;

/* import com.google.inject.Inject; */

import edu.eci.cvds.sampleprj.dao.EquipoDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.EquipoMapper;
import edu.eci.cvds.samples.entities.Equipment;
import java.util.List;

public class MyBatisEquipoDAO implements EquipoDAO {
    @Inject
    private EquipoMapper equipoMapper;

    @Override
    public void registrarEquipo(Equipment equipo) throws PersistenceException {
        try {
            equipoMapper.registrarEquipo(equipo);
        } catch (Exception e) {
            throw new PersistenceException("Error al registrar el nuevo equipo");
        }
    }

    @Override
    public List<Equipment> consultarEquipos() throws PersistenceException{
        try {
            return equipoMapper.consultarEquipos();
        } catch (Exception e) {
            throw new PersistenceException("Error al consultar los equipos");
        }
    }

    @Override
    public int consultarUltimoId() throws PersistenceException {
        try {
            return equipoMapper.consultarUltimoId();
        } catch (Exception e) {
            throw new PersistenceException("Error al consultar el ultimo Id de los equipos");
        }
    }

    @Override
    public Equipment consultarEquipoPorId(int id) throws PersistenceException {
        try {
            return equipoMapper.consultarEquipoPorId(id);
        } catch (Exception e) {
            throw new PersistenceException("Error al consultar el equipo con el Id "+id);
        }
    }

    @Override
    public void cambiarLaboratorio(int idLaboratory, int idEquipment) throws PersistenceException {
        try {
            equipoMapper.cambiarLaboratorio(idLaboratory, idEquipment);
        } catch (Exception e) {
            throw new PersistenceException("Error al cambiar al laboratorio con id "+idLaboratory+" el equipo con id "+idEquipment);
        }

    }

    @Override
    public int consultarNumeroEquipos(int laboratory_id) throws PersistenceException {
        try {
            return equipoMapper.consultarNumeroEquipos(laboratory_id);
        } catch (Exception e) {
            throw new PersistenceException("Error al consultar el numero de equipos en el laboratorio con id "+laboratory_id);
        }
    }

    @Override
    public void eliminarEquipoPorId(int id) throws PersistenceException {
        try {
            equipoMapper.eliminarElementoPorId(id);
        } catch (Exception e) {
            throw new PersistenceException("Error al eliminar el equipo con id "+id);
        }

    }

    @Override
    public void cambiarEstadoElementoId(int id,String estado) throws PersistenceException {
        try {
            equipoMapper.cambiarEstadoElementoId(id,estado);
        } catch (Exception e) {
            throw new PersistenceException(e.toString());
        }

    }
    
}
