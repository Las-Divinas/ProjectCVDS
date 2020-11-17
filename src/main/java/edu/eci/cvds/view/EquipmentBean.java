package edu.eci.cvds.view;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.google.inject.Inject;

import edu.eci.cvds.samples.entities.Equipment;
import edu.eci.cvds.samples.entities.Laboratory;
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
    private String message = "Se creo el equipo con exito";
    private List<Equipment> equiposBusquedaBasica;
    private List<Equipment> equiposSeleccionados;
    private List<String> nombresLaboratorios;
    private String nombreLaboratorio;
    private List<Laboratory> laboratorios;

    @PostConstruct
    public void init(){
        super.init();
        try{
            equiposBusquedaBasica = new ArrayList<>();
            laboratorios = new ArrayList<>();
            nombresLaboratorios = new ArrayList<>();
            equiposBusquedaBasica = servicioUsuario.consultarEquipos();
            laboratorios = servicioUsuario.consultarLaboratorios();
            for(int i=0; i<laboratorios.size(); i++){
                nombresLaboratorios.add(laboratorios.get(i).getName());
            }
        } catch (ExceptionHistorialDeEquipos e){
            e.printStackTrace();
        }
    }

    public void registrarEquipo() throws ExceptionHistorialDeEquipos, IOException{
        message = "Se creo el elemento con exito";
        laboratory_id = getIdByNameLaboratory(nombreLaboratorio);
        Equipment equipo = new Equipment(name, description, laboratory_id,"ACTIVO");
        servicioUsuario.registrarEquipment(equipo);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().redirect("../admin/selectElement.xhtml");
    }

    public List<Equipment> consultarEquipos() throws ExceptionHistorialDeEquipos{
        message = "Tuvimos un problema en agregar el equipo";
        return servicioUsuario.consultarEquipos();
    }

    public void eliminarEquipos() throws ExceptionHistorialDeEquipos{
        message = "Entre a eliminar";
        for(int i=0; i < equiposSeleccionados.size(); i++){
            System.out.println("------------------------------Entre"+i+"---------------------------------------");
            int idElemento = equiposSeleccionados.get(i).getId();
            servicioUsuario.cambiarEstadoElementoId(idElemento,"NO_ACTIVO");
        }
        equiposBusquedaBasica = servicioUsuario.consultarEquipos();
    }

    public int getIdByNameLaboratory(String name){
        int laboratoryId = 0;
        for(Laboratory laboratory: laboratorios){
            if(laboratory.getName().equals(name))
                laboratoryId = laboratory.getId();
        }
        return laboratoryId;
    }

    public String getNombreLaboratorio(){
        return nombreLaboratorio;
    }

    public void setNombreLaboratorio(String nombreLaboratorio){
        this.nombreLaboratorio = nombreLaboratorio;
    }

    public List<String> getNombresLaboratorios(){
        return nombresLaboratorios;
    }

    public void setNombresLaboratorios(List<String> nombresLaboratorios){
        this.nombresLaboratorios = nombresLaboratorios;
    }

    public List<Equipment> getEquiposSeleccionados(){
        return equiposSeleccionados;
    }

    public void setEquiposSeleccionados(List<Equipment> equiposSeleccionados){
        this.equiposSeleccionados = equiposSeleccionados;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
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

    public void dialogMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Exitoso", "Se añadio con exito el Equipo"));
    }
}
