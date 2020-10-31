package edu.eci.cvds.sampleprj.dao.mybatis;

/* import com.google.inject.Inject; */

import edu.eci.cvds.sampleprj.dao.EquipoDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Equipment;

public class MyBatisEquipoDAO implements EquipoDAO {
    /* @Inject
    private EquipoMapper equipoMapper; */

    @Override
    public void registrarEquipo(Equipment equipo) throws PersistenceException {
        try {
            /* equipoMapper.registrarEquipo(equipo); */
        } catch (Exception e) {
            throw new PersistenceException("Error al registrar el nuevo equipo");
        }

    }
    
}
