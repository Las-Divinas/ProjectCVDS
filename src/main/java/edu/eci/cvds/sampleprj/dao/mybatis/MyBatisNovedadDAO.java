package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;

/* import com.google.inject.Inject; */

import edu.eci.cvds.sampleprj.dao.NovedadDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.NovedadMapper;
import edu.eci.cvds.samples.entities.Novelty;

import java.util.List;

public class MyBatisNovedadDAO implements NovedadDAO {
    @Inject
    private NovedadMapper novedadMapper;

    @Override
    public void registrarNovedad(Novelty novedad) throws PersistenceException {
        try {
            novedadMapper.registrarNovedad(novedad);
        } catch (Exception e) {
            throw new PersistenceException(e.toString());
        }

    }

    @Override
    public List<Novelty> consultarNovedades() throws PersistenceException {
        try{
            return novedadMapper.consultarNovedades();
        } catch (Exception e) {
            throw new PersistenceException("Error al consultar novedades");
        }
    }

    @Override
    public List<Novelty> consultarNovedadesPorIDEquipo(int equipoID) throws PersistenceException {
        try{
            return novedadMapper.consultarNovedadesPorIDEquipo(equipoID);
        } catch (Exception e){
            throw new PersistenceException(e.toString());
        }
    }

    @Override
    public List<Novelty> consultarNovedadesPorIDElemento(int elementoID) throws PersistenceException {
        try{
            return novedadMapper.consultarNovedadesPorIDElemento(elementoID);
        } catch (Exception e){
            throw new PersistenceException(e.toString());
        }
    }
}
