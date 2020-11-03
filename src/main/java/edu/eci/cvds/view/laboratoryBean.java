package edu.eci.cvds.view;

import java.io.IOException;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import com.google.inject.Inject;

import edu.eci.cvds.samples.entities.Laboratory;
import edu.eci.cvds.samples.services.ExceptionHistorialDeEquipos;
import edu.eci.cvds.samples.services.ServicioUsuario;

@ManagedBean(name = "laboratoryBean")
@SessionScoped
public class laboratoryBean extends BasePageBean{

    private static final long serialVersionUID = 1L;

    @Inject
    private ServicioUsuario servicioUsuario;
    
    private String name;
    private String description;
    private int id;

    public void registrarLaboratorio() throws ExceptionHistorialDeEquipos, IOException{
        try {
            Laboratory laboratory = new Laboratory(id,name, description);
            servicioUsuario.registrarLaboratorio(laboratory);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
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

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }
}