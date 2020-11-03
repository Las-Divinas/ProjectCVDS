package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;

import edu.eci.cvds.sampleprj.dao.LaboratoryDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.LaboratoryMapper;
import edu.eci.cvds.samples.entities.Laboratory;

public class MyBatisLaboratorioDAO implements LaboratoryDAO {

    /* Aqui se inyecta LaboratorioMapper que va hacer Nikolas */
    @Inject
    private LaboratoryMapper laboratorioMapper;


    @Override
    public void registrarLaboratorio(Laboratory lab) throws PersistenceException {
        try {
            System.out.print(lab.getName()+"+-------------------------------");
            laboratorioMapper.registrarLaboratorio(lab);
        } catch (Exception e) {
            throw new PersistenceException(e.toString());
        }

    }
    
}
