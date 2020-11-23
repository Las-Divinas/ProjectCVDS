package edu.eci.cvds.view;

import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.google.inject.Inject;


import edu.eci.cvds.samples.entities.Element;
import edu.eci.cvds.samples.entities.Equipment;
import edu.eci.cvds.samples.entities.Novelty;
import edu.eci.cvds.samples.entities.Usuario;
import edu.eci.cvds.samples.services.ExceptionHistorialDeEquipos;
import edu.eci.cvds.samples.services.ServicioElement;
import edu.eci.cvds.samples.services.ServicioEquipment;
import edu.eci.cvds.samples.services.ServicioNovelty;
import edu.eci.cvds.samples.services.ServicioUsuario;
import java.util.Date;

@ManagedBean(name = "elementBean")
@SessionScoped
public class ElementBean extends BasePageBean{

    private static final long serialVersionUID = -6003014894429487789L;

    @Inject
    private ServicioUsuario servicioUsuario;

    @Inject
    private ServicioElement servicioElement;

    @Inject
    private ServicioNovelty servicioNovelty;

    @Inject
    private ServicioEquipment servicioEquipment;

    private int id;
    private String element_name;
    private String type;
    private String description;
    private Integer idEquipment;
    private String message = "Se creo el elemento con exito";
    private String a[] = new String[] {"Torre","Pantalla","Mouse","Teclado"};
    private List<String> types = Arrays.asList(a);
    private List<Element> elementosBusquedaBasica;
    private List<Element> elementosSeleccionados;
    private List<String> nombresElementos;
    private String nombreElemento;
    private List<Equipment> equipos;
    private List<String> nombresEquipos;
    private String nombreEquipo;


    @PostConstruct
    public void init(){
        super.init();
        try{
            elementosBusquedaBasica = new ArrayList<>(); 
            equipos = new ArrayList<>();
            nombresEquipos = new ArrayList<>();
            nombresElementos = new ArrayList<>();
            elementosBusquedaBasica = servicioElement.consultarElementos();
            for(Element elemento: elementosBusquedaBasica){
                nombresElementos.add(elemento.getElement_name());
            }
            equipos = servicioEquipment.consultarEquipos();
            for(Equipment equipo: equipos){
                nombresEquipos.add(equipo.getEquipment_name());
            }
        } catch (ExceptionHistorialDeEquipos e){
            e.printStackTrace();
        }
    }

    public void registrarElemento() throws ExceptionHistorialDeEquipos {
        try {
            //-----Registro de Elemento-----
            Element element = new Element(element_name,description,type,"INACTIVO");
            servicioElement.registrarElemento(element);

            //-----Registro de Novedad al crear nuevo Elemento-----
            Date date = new Date(System.currentTimeMillis());
            //* Obtener Usuario que esta realizando actividad
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(true);
            String sessionCorreo = (String) httpSession.getAttribute("correo");
            Usuario usuario = servicioUsuario.consultarIdUsuarioPorCorreo(sessionCorreo);
            //* Obtener ID del Elemento creado
            Integer elementoID = servicioElement.consultarUltimoIdElement();
            //* Generar Novedad
            Novelty novelty = new Novelty("El elemento "+element_name+" ha sido creado", "Elemento Creado", date, usuario.getDocumento(),elementoID,"Element");
            servicioNovelty.registrarNovedad(novelty);
            //* Mensaje POPUP
            message = "Elemento de Tipo "+type+" Creado Correctamente";
        } catch (Exception e) {
            throw new ExceptionHistorialDeEquipos("Error al registrar nuevo elemento");
        }
    }

    private void desasociarElementoAEQuipo(int idEquipment, String tipo) throws ExceptionHistorialDeEquipos {
        Element elemento = servicioElement.seleccionarElementoPorIdEquipo(idEquipment, tipo);
        if(elemento != null){
            Date date = new Date(System.currentTimeMillis());
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            String correoSession = (String) session.getAttribute("correo");
            Usuario usuario = servicioUsuario.consultarIdUsuarioPorCorreo(correoSession);
            servicioElement.cambiarIdEquipoParaElemento((Integer)null, elemento.getId());
            servicioElement.cambiarEstadoElementosId(elemento.getId(), "INACTIVO");
            Novelty novelty = new Novelty("Elemento fue desasociado","desasociacion elemeto "+elemento.getId(), date, usuario.getDocumento(), (Integer)null, elemento.getId());
            servicioNovelty.registrarNovedad(novelty);
        }
    }

    public void asociarElemento() throws ExceptionHistorialDeEquipos{
        int idEquipment = getIdByNameEquipment(nombreEquipo);
        id = getIdByNameElement(nombreElemento);
        System.out.println(id+"  Consultar el id del elemento++++++++++++++++++++++++++++++++++++++"+nombreElemento+"+++++"+nombreEquipo);
        try {
            Element element = servicioElement.consultarElementoPorId(id);
            System.out.println(element.getEstado()+"++++"+element.getId());
            desasociarElementoAEQuipo(idEquipment, element.getType());
            servicioElement.cambiarIdEquipoParaElemento(idEquipment, id);
            servicioElement.cambiarEstadoElementosId(id, "ACTIVO");
            Date date = new Date(System.currentTimeMillis());
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            String correoSession = (String) session.getAttribute("correo");
            Usuario usuario = servicioUsuario.consultarIdUsuarioPorCorreo(correoSession);
            Novelty novelty = new Novelty("Asociado "+element.getElement_name(),"Asociado", date, usuario.getDocumento(), idEquipment, element.getId());
            servicioNovelty.registrarNovedad(novelty);
        } catch (Exception e) {
            throw new ExceptionHistorialDeEquipos(e.toString());
        }
    }

    public void changeEquipmentElement() throws ExceptionHistorialDeEquipos, IOException{
        int idEquipo = servicioEquipment.consultarUltimoIdEquipment();
        System.out.println(idEquipo+" Id Equipo");
        id = getIdByNameElement(nombreElemento);
        Element elemento = servicioElement.consultarElementoPorId(id);
        System.out.println(id+" Id Elemento");
        System.out.println(elemento.getElement_name()+" Nombre elemento");
        messageManagerElementAssociete(elemento, servicioEquipment.consultarEquipoPorId(idEquipo));
        servicioElement.cambiarIdEquipoParaElemento(idEquipo, id);
        servicioElement.cambiarEstadoElementosId(elemento.getId(), "ACTIVO");
        Date date = new Date(System.currentTimeMillis());
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        String correoSession = (String) session.getAttribute("correo");
        Usuario usuario = servicioUsuario.consultarIdUsuarioPorCorreo(correoSession);
        Novelty novelty = new Novelty("Elemento agregado al equipo"+idEquipo, "Elemento Asociado a Equipo", date, usuario.getDocumento() ,idEquipo,id);
        servicioNovelty.registrarNovedad(novelty);
    }

    private void messageManagerElementAssociete(Element elemento, Equipment equipo){
        if(elemento.getId_equipment() == null){
            message = "El equipo se asocio al equipo "+equipo.getEquipment_name();
        }
        else{
            if(elemento.getId_equipment()==equipo.getId()){
                message = "El elemento ya esta asociado al equipo "+equipo.getEquipment_name();
            }
            else{
                message = "El equipo se asocio al equipo "+equipo.getEquipment_name();
            }
        }
    }

    public int getIdByNameEquipment(String nombre){
        int idEquipment = 0;
        for(Equipment equipo: equipos){
            if(equipo.getEquipment_name().equals(nombre)){
                idEquipment = equipo.getId();
            }
        }
        return idEquipment;
    }

    public int getIdByNameElement(String nombre){
        int idElement = 0;
        for(Element elemento: elementosBusquedaBasica){
            System.out.println(elemento.getId()+"++++++++++"+elemento.getElement_name()+"++++++"+nombre);
            if(elemento.getElement_name().equals(nombre)){
                idElement = elemento.getId();
            }
        }
        return idElement;
    }

    public List<Element> consultarElementos() throws ExceptionHistorialDeEquipos{
        return servicioElement.consultarElementos();
    }

    public void eliminarElementos() throws ExceptionHistorialDeEquipos{
        message = "Entre a eliminar";
        for(int i=0; i < elementosSeleccionados.size(); i++){
            System.out.println("------------------------------Entre"+i+"---------------------------------------");
            int idElementos = elementosSeleccionados.get(i).getId();
            servicioElement.cambiarEstadoElementosId(idElementos,"NO_ACTIVO");
        }
        elementosBusquedaBasica = servicioElement.consultarElementos();
    }

    public String consultarNombreElemento(Integer elementoID) throws ExceptionHistorialDeEquipos {
        return servicioElement.consultarNombreElemento(elementoID);
    }

    public List<String> getNombresElementos(){
        return nombresElementos;
    }

    public void setNombresElementos(List<String> nombresElementos){
        this.nombresElementos = nombresElementos;
    }

    public String getNombreElemento(){
        return nombreElemento;
    }

    public void setNombreElemento(String nombreElemento){
        this.nombreElemento = nombreElemento;
    }

    public List<String> getNombresEquipos(){
        return nombresEquipos;
    }

    public void setNombresEquipos(List<String> nombresEquipos){
        this.nombresEquipos = nombresEquipos;
    }

    public String getNombreEquipo(){
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo){
        this.nombreEquipo = nombreEquipo;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public List<String> getTypes(){
        return types;
    }

    public void setTypes(List<String> types){
        this.types=types;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getElement_name() {
        return element_name;
    }

    public void setElement_name(String element_name) {
        this.element_name = element_name;
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

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public List<Element> getElementosBusquedaBasica(){
        return elementosBusquedaBasica;
    }

    public void setElementosBusquedaBasica(List<Element> elementosBusquedaBasica){
        this.elementosBusquedaBasica = elementosBusquedaBasica;
    }

    public List<Element> getElementosSeleccionados(){
        return elementosSeleccionados;
    }

    public void setElementosSeleccionados(List<Element> elementosSeleccionados){
        this.elementosSeleccionados = elementosSeleccionados;
    }
    
}
