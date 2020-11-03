package edu.eci.cvds.view;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.google.inject.Inject;

import edu.eci.cvds.samples.entities.Element;
import edu.eci.cvds.samples.services.ExceptionHistorialDeEquipos;
import edu.eci.cvds.samples.services.ServicioUsuario;

@ManagedBean(name = "elementBean")
@SessionScoped
public class ElementBean extends BasePageBean{
    @Inject
    private ServicioUsuario servicioUsuario;

    private int id;
    private String name;
    private String description;
    private int idEquipment;

    public void registrarElemento() throws ExceptionHistorialDeEquipos, IOException{
        Element element = new Element(id, name, description, idEquipment);
        servicioUsuario.registrarElemento(element);
    
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

    public int getIdEquipment(){
        return idEquipment;
    }

    public void setIdEquipment(int idEquipment){
        this.idEquipment=idEquipment;
    }
}
