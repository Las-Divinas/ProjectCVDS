package edu.eci.cvds.view;

import java.io.IOException;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.google.inject.Inject;

import edu.eci.cvds.samples.entities.Novelty;
import edu.eci.cvds.samples.services.ExceptionHistorialDeEquipos;
import edu.eci.cvds.samples.services.ServicioUsuario;

@ManagedBean(name = "noveltyBean")
@SessionScoped
public class NoveltyBean extends BasePageBean{
    @Inject
    private ServicioUsuario servicioUsuario;

    private int id;
    private String description;
    private String idUser;
    private int idEquipment;

    public void registrarNovedad() throws ExceptionHistorialDeEquipos, IOException{
        Date date = new Date(System.currentTimeMillis());
        Novelty novelty = new Novelty(id, description, date , idUser, idEquipment);
        servicioUsuario.registrarNovedad(novelty);
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public String getIdUser(){
        return idUser;
    }

    public void setIdUser(String idUser){
        this.idUser=idUser;
    }

    public int getIdEquipment(){
        return idEquipment;
    }
    public void setIdEquipment(int idEquipment){
        this.idEquipment=idEquipment;
    }
    
}
