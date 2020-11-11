package edu.eci.cvds.view;

import java.io.IOException;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.google.inject.Inject;

import edu.eci.cvds.samples.entities.Equipment;
import edu.eci.cvds.samples.entities.Laboratory;
import edu.eci.cvds.samples.entities.Novelty;
import edu.eci.cvds.samples.entities.Usuario;
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
    private int idEquipment;

    public void registrarLaboratorio() throws ExceptionHistorialDeEquipos, IOException{
        try {
            Laboratory laboratory = new Laboratory(id,name, description);
            servicioUsuario.registrarLaboratorio(laboratory);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.getExternalContext().redirect("../admin/addEquipLab.xhtml");

        } catch (Exception e) {
            throw new ExceptionHistorialDeEquipos("Error al registrar el nuevo laboratorio");
        }
    }

    public void updateLaboratoryEquipment() throws ExceptionHistorialDeEquipos{
        try {
            int idLaboratorio = servicioUsuario.consultarUltimoIdLaboratorio();
            Equipment equipo = servicioUsuario.consultarEquipoPorId(idEquipment);
            int oldIdLaboratorio = equipo.getLaboratory_id();
            if(!(equipo.getLaboratory_id() == idLaboratorio)){
                servicioUsuario.cambiarLaboratorioEquipo(idLaboratorio, idEquipment);               
                Date date = new Date(System.currentTimeMillis());
                FacesContext facesContext = FacesContext.getCurrentInstance();
                HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
                String correoSession = (String) session.getAttribute("correo");
                Usuario usuario = servicioUsuario.consultarIdUsuarioPorCorreo(correoSession);
                Novelty novelty = new Novelty("Es equipo fue agregado al laboratorio "+idLaboratorio+"Ya no pertenece al laboratorio "+oldIdLaboratorio, "Agregado al laboratorio "+idLaboratorio, date, usuario.getDocumento(), idEquipment, 0);
                servicioUsuario.registrarNovedad(novelty);
            }

        } catch (Exception e) {
            throw new ExceptionHistorialDeEquipos(e.toString());
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

    public int getIdEquipment(){
        return idEquipment;
    }

    public void setIdEquipment(int idEquipment){
        this.idEquipment = idEquipment;
    }
}
