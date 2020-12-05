package edu.eci.cvds.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.google.inject.Inject;

import edu.eci.cvds.samples.entities.*;
import edu.eci.cvds.samples.services.ExceptionHistorialDeEquipos;
import edu.eci.cvds.samples.services.ServicioEquipment;
import edu.eci.cvds.samples.services.ServicioLaboratory;
import edu.eci.cvds.samples.services.ServicioNovelty;
import edu.eci.cvds.samples.services.ServicioUsuario;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean(name = "laboratoryBean")
@SessionScoped
public class laboratoryBean extends BasePageBean {

    private static final long serialVersionUID = 1L;

    @Inject
    private ServicioUsuario servicioUsuario;

    @Inject
    private ServicioLaboratory servicioLaboratory;

    @Inject
    private ServicioNovelty servicioNovelty;

    @Inject
    private ServicioEquipment servicioEquipment;

    private String laboratory_name;
    private String description;
    private int id;
    private int idEquipment;
    private String message = "Se creo el laboratorio con exito";
    private List<Laboratory> laboratoriosBusquedaBasica;

    @PostConstruct
    public void init() {
        super.init();

        laboratoriosBusquedaBasica = new ArrayList<>();
    }

    public void registrarLaboratorio() throws ExceptionHistorialDeEquipos {
        try {
            //-----Registro de Laboratorio-----
            Date date = new Date(System.currentTimeMillis());
            Laboratory laboratory = new Laboratory(laboratory_name, description, "ACTIVO", date, null);
            servicioLaboratory.registrarLaboratorio(laboratory);

            //-----Registro de Novedad al crear nuevo Laboratorio
            //* Obtener Usuario que esta realizando actividad
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(true);
            String sessionCorreo = (String) httpSession.getAttribute("correo");
            Usuario usuario = servicioUsuario.consultarIdUsuarioPorCorreo(sessionCorreo);
            //* Obtener ID del Elemento creado
            Integer laboratorioID = servicioLaboratory.consultarUltimoIdLaboratorio();
            //* Generar Novedad
            Novelty novelty = new Novelty("El laboratorio " + laboratory_name + " ha sido creado", "Laboratorio Creado", date, usuario.getDocumento(), laboratorioID, "Laboratory");
            servicioNovelty.registrarNovedad(novelty);
            //* Mensaje POPUP
            message = "Laboratorio Creado Correctamente";
        } catch (Exception e) {
            message = "Error al crear el laboratorio";
            throw new ExceptionHistorialDeEquipos("Error al registrar Laboratorio");
        }
    }


    public int countEquipment(int idLaboratory) throws ExceptionHistorialDeEquipos {
        return servicioEquipment.consultarNumeroEquipos(idLaboratory);
    }

    public List<Laboratory> consultarLaboratorios() throws ExceptionHistorialDeEquipos {
        message = "Tuvimos un problema al consultar el Laboratorio";
        return servicioLaboratory.consultarLaboratorios();
    }

    public String consultarNombreLaboratorio(Integer laboratorioID) throws ExceptionHistorialDeEquipos {
        return servicioLaboratory.consultarNombreLaboratorio(laboratorioID);
    }

    public void updateLaboratoryEquipment() throws ExceptionHistorialDeEquipos {
        try {
            int idLaboratorio = servicioLaboratory.consultarUltimoIdLaboratorio();
            Equipment equipo = servicioEquipment.consultarEquipoPorId(idEquipment);
            int oldIdLaboratorio = equipo.getLaboratory_id();
            if (!(equipo.getLaboratory_id() == idLaboratorio)) {
                servicioEquipment.cambiarLaboratorioEquipo(idLaboratorio, idEquipment);
                Date date = new Date(System.currentTimeMillis());
                FacesContext facesContext = FacesContext.getCurrentInstance();
                HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
                String correoSession = (String) session.getAttribute("correo");
                Usuario usuario = servicioUsuario.consultarIdUsuarioPorCorreo(correoSession);
                Novelty novelty = new Novelty("Es equipo fue agregado al laboratorio " + idLaboratorio + "Ya no pertenece al laboratorio " + oldIdLaboratorio, "Agregado al laboratorio " + idLaboratorio, date, usuario.getDocumento(), idEquipment, 0);
                servicioNovelty.registrarNovedad(novelty);
            }

        } catch (Exception e) {
            throw new ExceptionHistorialDeEquipos(e.toString());
        }
    }

    public String darFormatoFecha(Date date) {
        if (date != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

            return format.format(date);
        }

        return "";
    }

    public void cerrarLaboratorio() throws ExceptionHistorialDeEquipos {
        Date date;
        Integer laboratorioID;
        List<Equipment> equiposActivos;

        message = "El "+laboratory_name+" fue cerrado con exito";

        date = new Date(System.currentTimeMillis());
        laboratorioID = servicioLaboratory.consultarIDLaboratorioPorNombre(laboratory_name);
        equiposActivos = servicioEquipment.consultarEquipos();

        System.out.println(laboratorioID);

        for (Equipment e : equiposActivos) {
            System.out.println(e.getLaboratory_id());
            if (laboratorioID.equals(e.getLaboratory_id())) {
                servicioEquipment.cambiarLaboratorioEquipo(null, e.getId());
                break;
            }
        }

        servicioLaboratory.cambiarFechaDeCierre(date, laboratorioID);
        servicioLaboratory.cambiarEstadoLaboratorio("INACTIVO", laboratorioID);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLaboratory_name() {
        return laboratory_name;
    }

    public void setLaboratory_name(String laboratory_name) {
        this.laboratory_name = laboratory_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEquipment() {
        return idEquipment;
    }

    public void setIdEquipment(int idEquipment) {
        this.idEquipment = idEquipment;
    }

    public List<Laboratory> getLaboratoriosBusquedaBasica() throws ExceptionHistorialDeEquipos {
        laboratoriosBusquedaBasica = servicioLaboratory.consultarLaboratorios();

        return laboratoriosBusquedaBasica;
    }

    public List<String> getNombreLaboratoriosActivos() throws ExceptionHistorialDeEquipos {
        List<String> nombreLaboratorio;
        List<Laboratory> laboratorioActivo;

        nombreLaboratorio = new ArrayList<>();
        laboratorioActivo = servicioLaboratory.consultarLaboratoriosActivos();

        for(Laboratory l:laboratorioActivo) {
            nombreLaboratorio.add(l.getLaboratory_name());
        }

        return nombreLaboratorio;
    }

    public void info() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, "PrimeFaces Rocks."));
    }



    //-----------------Graficos --------------------------//
    private PieChartModel model;

    public PieChartModel generarEstadistica() throws ExceptionHistorialDeEquipos {
        model = new PieChartModel();
        laboratoriosBusquedaBasica = servicioLaboratory.consultarLaboratorios();

        for (Laboratory laboratory : laboratoriosBusquedaBasica) {
            model.set(laboratory.getLaboratory_name(),servicioEquipment.consultarNumeroEquipos(laboratory.getId()));
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

        laboratoriosBusquedaBasica = servicioLaboratory.consultarLaboratorios();

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
