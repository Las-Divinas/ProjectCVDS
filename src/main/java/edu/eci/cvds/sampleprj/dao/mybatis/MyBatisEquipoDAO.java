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
    
}
