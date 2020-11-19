package edu.eci.cvds.samples.services.impl;

import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.sampleprj.dao.NovedadDAO;
import edu.eci.cvds.samples.entities.Novelty;
import edu.eci.cvds.samples.services.ExceptionHistorialDeEquipos;
import edu.eci.cvds.samples.services.ServicioNovelty;

public class ServicioNoveltyImpl implements ServicioNovelty {

    @Inject
    private NovedadDAO novedadDAO;

    @Override
    public void registrarNovedad(Novelty n) throws ExceptionHistorialDeEquipos {
        try {
            novedadDAO.registrarNovedad(n);
        } catch (Exception e) {
            throw new ExceptionHistorialDeEquipos(e.toString());
        }
    }

    @Override
    public List<Novelty> consultarNovedades() throws ExceptionHistorialDeEquipos {
        try{
            return  novedadDAO.consultarNovedades();
        } catch (Exception e){
            throw new ExceptionHistorialDeEquipos("Error al consultar los novedades");
        }
    }

    @Override
    public List<Novelty> consultarNovedadesPorIDEquipo(int equipoID) throws ExceptionHistorialDeEquipos {
        try{
            return novedadDAO.consultarNovedadesPorIDEquipo(equipoID);
        }catch (Exception e){
            throw new ExceptionHistorialDeEquipos(e.toString());
        }
    }

    @Override
    public List<Novelty> consultarNovedadesPorIDElemento(int elementoID) throws ExceptionHistorialDeEquipos {
        try{
            return novedadDAO.consultarNovedadesPorIDElemento(elementoID);
        }catch (Exception e){
            throw new ExceptionHistorialDeEquipos(e.toString());
        }
    }
}
