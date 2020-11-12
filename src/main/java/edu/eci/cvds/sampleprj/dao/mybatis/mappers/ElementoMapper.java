package edu.eci.cvds.sampleprj.dao.mybatis.mappers;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Element;

public interface ElementoMapper {
    public void registrarElemento(@Param("elemento") Element elemento);
    public int consultarUltimoId();
    public Element consultarElementoPorId(@Param("id") int id);
    public List<Element> consultarElementos();
}
