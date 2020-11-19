package edu.eci.cvds.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
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
import edu.eci.cvds.samples.services.ServicioEquipment;
import edu.eci.cvds.samples.services.ServicioLaboratory;
import edu.eci.cvds.samples.services.ServicioNovelty;
import edu.eci.cvds.samples.services.ServicioUsuario;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean(name = "laboratoryBean")
@SessionScoped
public class laboratoryBean extends BasePageBean{

    private static final long serialVersionUID = 1L;

    @Inject
    private ServicioUsuario servicioUsuario;

    @Inject
    private ServicioLaboratory servicioLaboratory;

    @Inject
    private ServicioNovelty servicioNovelty;

    @Inject
    private ServicioEquipment servicioEquipment;
    
    private String name;
    private String description;
    private int id;
    private int idEquipment;
    private String message = "Se creo el laboratorio con exito";
    private List<Laboratory> laboratoriosBusquedaBasica;

    @PostConstruct
    public void init(){
        super.init();
        try{
            laboratoriosBusquedaBasica = new ArrayList<>();
            laboratoriosBusquedaBasica = servicioLaboratory.consultarLaboratorios();
        } catch (ExceptionHistorialDeEquipos e){
            e.printStackTrace();
        }
    }

    public void registrarLaboratorio() throws ExceptionHistorialDeEquipos, IOException{
        try {
            Date date = new Date(System.currentTimeMillis());
            Laboratory laboratory = new Laboratory(id,name, description,"ACTIVO",date,null);
            servicioLaboratory.registrarLaboratorio(laboratory);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.getExternalContext().redirect("../admin/addEquipLab.xhtml");

        } catch (Exception e) {
            throw new ExceptionHistorialDeEquipos("Error al registrar el nuevo laboratorio");
        }
    }

    public int countEquipment(int idLaboratory) throws ExceptionHistorialDeEquipos
    {
        return servicioEquipment.consultarNumeroEquipos(idLaboratory);
    }
    public List<Laboratory> consultarLaboratorios() throws ExceptionHistorialDeEquipos{
        message = "Tuvimos un problema al consultar el Laboratorio";
        return servicioLaboratory.consultarLaboratorios();
    }

    public void updateLaboratoryEquipment() throws ExceptionHistorialDeEquipos{
        try {
            int idLaboratorio = servicioLaboratory.consultarUltimoIdLaboratorio();
            Equipment equipo = servicioEquipment.consultarEquipoPorId(idEquipment);
            int oldIdLaboratorio = equipo.getLaboratory_id();
            if(!(equipo.getLaboratory_id() == idLaboratorio)){
                servicioEquipment.cambiarLaboratorioEquipo(idLaboratorio, idEquipment);               
                Date date = new Date(System.currentTimeMillis());
                FacesContext facesContext = FacesContext.getCurrentInstance();
                HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
                String correoSession = (String) session.getAttribute("correo");
                Usuario usuario = servicioUsuario.consultarIdUsuarioPorCorreo(correoSession);
                Novelty novelty = new Novelty("Es equipo fue agregado al laboratorio "+idLaboratorio+"Ya no pertenece al laboratorio "+oldIdLaboratorio, "Agregado al laboratorio "+idLaboratorio, date, usuario.getDocumento(), idEquipment, 0);
                servicioNovelty.registrarNovedad(novelty);
            }

        } catch (Exception e) {
            throw new ExceptionHistorialDeEquipos(e.toString());
        }
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
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
    public List<Laboratory> getLaboratoriosBusquedaBasica(){
        return laboratoriosBusquedaBasica;
    }

    public void setLaboratoriosBusquedaBasica(List<Laboratory> laboratoriosBusquedaBasica){
        this.laboratoriosBusquedaBasica=laboratoriosBusquedaBasica;
    }


    //-----------------Graficos --------------------------//
    private PieChartModel model;

    public PieChartModel generarEstadistica() throws ExceptionHistorialDeEquipos {
        model = new PieChartModel();
        for (Laboratory laboratory : laboratoriosBusquedaBasica) {
            model.set(laboratory.getName(),servicioEquipment.consultarNumeroEquipos(laboratory.getId()));
        }
        model.setTitle("Cantidad de equipos por laboratorio");
        model.setShowDataLabels(true);
        model.setDataLabelFormatString("%dK");
        model.setLegendPosition("e");
        model.setShowDatatip(true);
        model.setShowDataLabels(true);
        model.setDataFormat("value");
        model.setDataLabelFormatString("%d");
        //model.setSeriesColors("006600,FFFF00,000099,990000");
        return model;
    }
    public PieChartModel generarEstadisticaEstado() throws ExceptionHistorialDeEquipos {
        model = new PieChartModel();
        int activo=0;
        int noActivo=0;
        for (Laboratory laboratory : laboratoriosBusquedaBasica) {
           if(laboratory.getEstado().equals("ACTIVO"))
           {
               activo++;
           }else
           {
            noActivo++;
           }
        }
        model.set("Activos",activo);
        model.set("No Activos",noActivo);
        model.setTitle("Estado Laboratorios");
        model.setShowDataLabels(true);
        model.setDataLabelFormatString("%dK");
        model.setLegendPosition("e");
        model.setShowDatatip(true);
        model.setShowDataLabels(true);
        model.setDataFormat("value");
        model.setDataLabelFormatString("%d");
        //model.setSeriesColors("006600,FFFF00,000099,990000");
        return model;
    }
}
