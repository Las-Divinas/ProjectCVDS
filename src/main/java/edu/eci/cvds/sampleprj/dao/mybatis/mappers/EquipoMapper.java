package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Equipment;
import java.util.List;

public interface EquipoMapper {
    public void registrarEquipo(@Param("equipo") Equipment equipo);
    public List<Equipment> consultarEquipos();
    public int consultarUltimoId();
    public Equipment consultarEquipoPorId(@Param("id") int id);
    public void cambiarLaboratorio(@Param("idLaboratory") int idLaboratory,@Param("idEquipment") int idEquipment);
    public int consultarNumeroEquipos(@Param("laboratory_id")int laboratory_id);
    public void eliminarElementoPorId(@Param("id") int id);
    public void cambiarEstadoElementoId(@Param("id") int id,@Param("estado") String estado);
}
