package edu.eci.cvds.sampleprj.dao.mybatis.mappers;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Element;

public interface ElementoMapper {
    void registrarElemento(@Param("elemento") Element elemento);
    int consultarUltimoId();
    Element consultarElementoPorId(@Param("id") int id);
    List<Element> consultarElementos();
    void cambiarIdEquipo(@Param("idEquipment") Integer idEquipment, @Param("idElement") Integer idElement);
    void eliminarElementosPorId(@Param("id") int id);
    void cambiarEstadoElementosId(@Param("id") int id,@Param("estado") String estado);
    Element seleccionarElementoPorIdEquipo(@Param("idEquipment") int idEquipment, @Param("tipo") String tipo);
    String consultarNombreElemento(@Param("elementoID") Integer elementoID);
    List<Element> consultarElementoNoAsociado(@Param("type") String type);
    List<Element> consultarElementosPorEquipo(@Param("equipoID") Integer equipoID);
    Integer consultarElementoIDPorNombre(@Param("nombreElemento") String nombreElemento);
}
