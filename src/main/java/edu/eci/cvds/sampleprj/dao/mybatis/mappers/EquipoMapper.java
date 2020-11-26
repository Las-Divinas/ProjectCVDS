package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Equipment;
import java.util.List;

public interface EquipoMapper {
    void registrarEquipo(@Param("equipo") Equipment equipo);
    List<Equipment> consultarEquipos();
    int consultarUltimoId();
    Equipment consultarEquipoPorId(@Param("id") int id);
    void cambiarLaboratorio(@Param("idLaboratory") Integer idLaboratory,@Param("idEquipment") Integer idEquipment);
    int consultarNumeroEquipos(@Param("laboratory_id")int laboratory_id);
    void eliminarElementoPorId(@Param("id") int id);
    void cambiarEstadoElementoId(@Param("id") int id,@Param("estado") String estado);
    String consultarNombreEquipo(@Param("equipmentID") Integer equipmentID);
    Integer consultarEquipoIDporNombre(@Param("nombreEquipo") String nombreEquipo);
}
