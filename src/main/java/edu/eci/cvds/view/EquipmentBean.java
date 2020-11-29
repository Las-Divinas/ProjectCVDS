package edu.eci.cvds.view;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.google.inject.Inject;

import com.sun.org.apache.xerces.internal.xs.StringList;
import edu.eci.cvds.samples.entities.*;
import edu.eci.cvds.samples.services.*;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

@ManagedBean(name = "equipmentBean")
@SessionScoped
public class EquipmentBean extends BasePageBean{
	private static final long serialVersionUID = 6194542760432320641L;

    @Inject 
    private ServicioEquipment servicioEquipment;

    @Inject
    private ServicioLaboratory servicioLaboratory;

    @Inject
    private ServicioUsuario servicioUsuario;

    @Inject
    private ServicioNovelty servicioNovelty;

    @Inject
    private ServicioElement servicioElement;

    private int id;
    private String equipment_name;
    private String nombreEquipo;
    private String description;
    private Integer laboratory_id;
    private String message = "Se creo el equipo con exito";
    private List<Equipment> equiposBusquedaBasica;
    private List<Equipment> equiposSeleccionados;
    private List<String> nombresLaboratorios;
    private final String[] bajaDesvincular = new String[] {"Dar de Baja Elementos Vinculados", "Desvincular Elementos"};
    private String accionElementos;
    private String nombreLaboratorio;

    @PostConstruct
    public void init(){
        super.init();
        equiposBusquedaBasica = new ArrayList<>();
    }

    public void registrarEquipo() throws ExceptionHistorialDeEquipos, IOException {
        message = "El equipo "+equipment_name+" ha sido creado con exito.";
        //-----Registro de Equipo-----
        Equipment equipment = new Equipment(equipment_name, description, "ACTIVO");
        servicioEquipment.registrarEquipment(equipment);

        //-----Registro de Novedad al crear nuevo Equipo----
        Date date = new Date(System.currentTimeMillis());
        //* Obtener Usuario que esta realizando actividad
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(true);
        String sessionCorreo = (String) httpSession.getAttribute("correo");
        Usuario usuario = servicioUsuario.consultarIdUsuarioPorCorreo(sessionCorreo);
        //* Obtener ID del Elemento creado
        Integer equipmentID = servicioEquipment.consultarUltimoIdEquipment();
        //* Generar Novedad
        Novelty novelty = new Novelty("El equipo "+equipment_name+" ha sido creado", "Equipo Creado", date, usuario.getDocumento(), equipmentID, "Equipment");
        servicioNovelty.registrarNovedad(novelty);
        //* Redirigir para Seleccionar Elementos del Equipo
        facesContext.getExternalContext().redirect("../admin/selectElement.xhtml");
    }

    public String consultarNombreEquipo(Integer equipmentID) throws ExceptionHistorialDeEquipos {
        return servicioEquipment.consultarNombreEquipo(equipmentID);
    }

    public void eliminarEquipos() throws ExceptionHistorialDeEquipos{
        message = "Entre a eliminar";
        for(int i=0; i < equiposSeleccionados.size(); i++){
            System.out.println("------------------------------Entre"+i+"---------------------------------------");
            int idElemento = equiposSeleccionados.get(i).getId();
            servicioEquipment.cambiarEstadoElementoId(idElemento,"INACTIVO");
        }
        equiposBusquedaBasica = servicioEquipment.consultarEquipos();
    }

    public void darDeBajaEquipo() throws ExceptionHistorialDeEquipos {
        Novelty novelty;
        Date date;
        Integer equipoID;
        List<Element> elementosAsociados;

        message = "El equipo "+nombreEquipo+" fue dado de baja.";
        equiposBusquedaBasica = servicioEquipment.consultarEquipos();

        date = new Date(System.currentTimeMillis());
        equipoID = getEquipoIDporNombre(nombreEquipo);
        elementosAsociados = servicioElement.consultarElementosPorEquipo(equipoID);

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(true);
        String sessionCorreo = (String) httpSession.getAttribute("correo");
        Usuario usuario = servicioUsuario.consultarIdUsuarioPorCorreo(sessionCorreo);

        if(accionElementos.equals("Desvincular Elementos")) {
            for(Element e:elementosAsociados) {
                novelty = new Novelty("El elemento "+e.getElement_name()+" fue desvinculado del equipo "+nombreEquipo, "Elemento Desvinculado", date, usuario.getDocumento(), e.getId(), "Element");

                servicioElement.cambiarIdEquipoParaElemento(null, e.getId());
                servicioNovelty.registrarNovedad(novelty);
            }
        } else {
            for(Element e:elementosAsociados) {
                novelty = new Novelty("El elemento "+e.getElement_name()+" fue dado de baja", "Elemento Dado de Baja", date, usuario.getDocumento(), e.getId(), "Element");

                servicioElement.cambiarIdEquipoParaElemento(null, e.getId());
                servicioElement.cambiarEstadoElementosId(e.getId(), "INACTIVO");
                servicioNovelty.registrarNovedad(novelty);
            }
        }

        novelty = new Novelty("El equipo "+nombreEquipo+" fue dado de baja", "Equipo Dado de Baja", date, usuario.getDocumento(), equipoID, "Equipment");

        servicioNovelty.registrarNovedad(novelty);
        servicioEquipment.cambiarLaboratorioEquipo(null, equipoID);
        servicioEquipment.cambiarEstadoElementoId(equipoID, "INACTIVO");
    }

    public void asociarEquipoLaboratorio() throws ExceptionHistorialDeEquipos{
        Integer equipoID, laboratorioID, laboratorioIDViejo;
        FacesContext facesContext;
        Novelty novelty;
        Date date;

        equiposBusquedaBasica = servicioEquipment.consultarEquipos();

        equipoID = getEquipoIDporNombre(nombreEquipo);
        laboratorioID = servicioLaboratory.consultarIDLaboratorioPorNombre(nombreLaboratorio);
        laboratorioIDViejo = null;

        for(Equipment e:equiposBusquedaBasica) {
            if(e.getId().equals(equipoID)) {
                laboratorioIDViejo = e.getLaboratory_id();
                break;
            }
        }

        date = new Date(System.currentTimeMillis());
        facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        String correoSession = (String) session.getAttribute("correo");
        Usuario usuario = servicioUsuario.consultarIdUsuarioPorCorreo(correoSession);

        if(laboratorioIDViejo!=null) {
            novelty = new Novelty("El equipo "+nombreEquipo+" fue desvinculado de "+servicioLaboratory.consultarNombreLaboratorio(laboratorioIDViejo), "Equipo Desvinculado de Laboratorio", date, usuario.getDocumento(), equipoID, "Equipment");
            servicioEquipment.cambiarLaboratorioEquipo(null, equipoID);
            servicioNovelty.registrarNovedad(novelty);

            novelty = new Novelty("El equipo "+nombreEquipo+" fue vinculado a "+nombreLaboratorio, "Equipo Vinculado a Laboratorio", date, usuario.getDocumento(), equipoID, "Equipment");
            servicioEquipment.cambiarLaboratorioEquipo(laboratorioID, equipoID);
            servicioNovelty.registrarNovedad(novelty);
        } else {
            novelty = new Novelty("El equipo "+nombreEquipo+" fue vinculado a "+nombreLaboratorio, "Equipo Vinculado a Laboratorio", date, usuario.getDocumento(), equipoID, "Equipment");
            servicioEquipment.cambiarLaboratorioEquipo(laboratorioID, equipoID);
            servicioNovelty.registrarNovedad(novelty);
        }
    }

    public List<String> getNombresLaboratorios() throws ExceptionHistorialDeEquipos {
        List<Laboratory> laboratorios;

        nombresLaboratorios = new ArrayList<>();
        laboratorios = servicioLaboratory.consultarLaboratorios();

        for(Laboratory l: laboratorios) {
            nombresLaboratorios.add(l.getLaboratory_name());
        }

        return nombresLaboratorios;
    }

    public void setNombresLaboratorios(List<String> nombresLaboratorios) {
        this.nombresLaboratorios = nombresLaboratorios;
    }

    public List<Equipment> getEquiposSeleccionados() {
        return equiposSeleccionados;
    }

    public void setEquiposSeleccionados(List<Equipment> equiposSeleccionados) {
        this.equiposSeleccionados = equiposSeleccionados;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id=id;
    }

    public String getEquipment_name() {
        return equipment_name;
    }

    public void setEquipment_name(String equipment_name) {
        this.equipment_name = equipment_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description=description;
    }

    public Integer getLaboratory_id() {
        return laboratory_id;
    }

    public void setLaboratory_id(Integer laboratory_id) {
        this.laboratory_id=laboratory_id;
    }

    public List<Equipment> getEquiposBusquedaBasica() throws ExceptionHistorialDeEquipos {
        equiposBusquedaBasica = servicioEquipment.consultarEquipos();

        return equiposBusquedaBasica;
    }

    public void setEquiposBusquedaBasica(List<Equipment> equiposBusquedaBasica) {
        this.equiposBusquedaBasica = equiposBusquedaBasica;
    }

    public void info() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, "PrimeFaces Rocks."));
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public List<String> getNombresDeEquipos() throws ExceptionHistorialDeEquipos {
        List<String> nombresDeEquipos;

        nombresDeEquipos = new ArrayList<>();
        equiposBusquedaBasica = servicioEquipment.consultarEquipos();

        for(Equipment e:equiposBusquedaBasica) {
            nombresDeEquipos.add(e.getEquipment_name());
        }

        return nombresDeEquipos;
    }

    public Integer getEquipoIDporNombre(String nombreEquipo) throws ExceptionHistorialDeEquipos {
        return servicioEquipment.consultarEquipoIDporNombre(nombreEquipo);
    }

    public String[] getBajaDesvincular() {
        return bajaDesvincular;
    }

    public String getAccionElementos() {
        return accionElementos;
    }

    public void setAccionElementos(String accionElementos) {
        this.accionElementos = accionElementos;
    }

    public String getNombreLaboratorio() {
        return nombreLaboratorio;
    }

    public void setNombreLaboratorio(String nombreLaboratorio) {
        this.nombreLaboratorio = nombreLaboratorio;
    }
}
