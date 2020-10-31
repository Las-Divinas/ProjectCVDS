package edu.eci.cvds.sampleprj.dao.mybatis;

import edu.eci.cvds.sampleprj.dao.LaboratoryDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Laboratory;

public class MyBatisLaboratorioDAO implements LaboratoryDAO {

    /* Aqui se inyecta LaboratorioMapper que va hacer Nikolas */
    /* @Inject
       private LaboratorioMapper laboratorioMapper */


    @Override
    public void registrarLaboratorio(Laboratory lab) throws PersistenceException {
        try {
            /* laboratorioMapper.registrarLaboratorio(lab); */
        } catch (Exception e) {
            throw new PersistenceException("Error al registrar el nuevo Laboratorio");
        }

    }
    
}
