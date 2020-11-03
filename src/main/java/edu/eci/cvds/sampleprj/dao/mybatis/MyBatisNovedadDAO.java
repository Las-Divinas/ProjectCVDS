package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;

/* import com.google.inject.Inject; */

import edu.eci.cvds.sampleprj.dao.NovedadDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.NovedadMapper;
import edu.eci.cvds.samples.entities.Novelty;

public class MyBatisNovedadDAO implements NovedadDAO {
    @Inject
    private NovedadMapper novedadMapper;

    @Override
    public void registrarNovedad(Novelty novedad) throws PersistenceException {
        try {
            novedadMapper.registrarNovedad(novedad);
        } catch (Exception e) {
            throw new PersistenceException("Error al registrar la nueva novedad");
        }

    }
    
}
