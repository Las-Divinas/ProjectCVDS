package edu.eci.cvds.view;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.google.inject.Inject;

import edu.eci.cvds.samples.entities.Equipment;
import edu.eci.cvds.samples.services.ExceptionHistorialDeEquipos;
import edu.eci.cvds.samples.services.ServicioUsuario;
import java.util.List;
import java.util.ArrayList;

@ManagedBean(name = "equipmentBean")
@SessionScoped
public class EquipmentBean extends BasePageBean{

	private static final long serialVersionUID = 6194542760432320641L;

	@Inject
    private ServicioUsuario servicioUsuario;

    private int id;
    private String name;
    private String description;
    private int laboratory_id;
    private List<Equipment> equiposBusquedaBasica;

    @PostConstruct
    public void init(){
        super.init();
        try{
            equiposBusquedaBasica = new ArrayList<>();
            equiposBusquedaBasica = servicioUsuario.consultarEquipos();
        } catch (ExceptionHistorialDeEquipos e){
            e.printStackTrace();
        }
    }

    public void registrarEquipo() throws ExceptionHistorialDeEquipos, IOException{
        Equipment equipo = new Equipment(id, name, description, laboratory_id);
        servicioUsuario.registrarEquipment(equipo);
    }

    public List<Equipment> consultarEquipos() throws ExceptionHistorialDeEquipos{
        return servicioUsuario.consultarEquipos();
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }
    
    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public int getLaboratory_id(){
        return id;
    }

    public void setLaboratory_id(int laboratory_id){
        this.laboratory_id=laboratory_id;
    }

    public List<Equipment> getEquiposBusquedaBasica(){
        return equiposBusquedaBasica;
    }

    public void setEquiposBusquedaBasica(List<Equipment> equiposBusquedaBasica){
        this.equiposBusquedaBasica=equiposBusquedaBasica;
    }
}
