package edu.eci.cvds.view;

import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
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
    private String message;
    private final String[] a = new String[] {"Torre","Pantalla","Mouse","Teclado"};
    private List<String> types = Arrays.asList(a);
    private List<Element> elementosBusquedaBasica;
    private List<Element> elementosSeleccionados;
    private List<String> nombresElementos;
    private String nombreElemento;
    private List<Equipment> equipos;
    private List<String> nombresEquipos;
    private String nombreEquipo;
    private String nombreTeclado, nombreMouse, nombrePantalla, nombreTorre;

    @PostConstruct
    public void init() {
        super.init();
    }

    public void asociarElementoEquipo() throws ExceptionHistorialDeEquipos, IOException {
        Novelty novedadTeclado, novedadMouse, novedadPantalla, novedadTorre;
        Integer equipoID, tecladoID, mouseID, pantallaID, torreID;
        String nombreEquipo;
        Date date;

        message = "Los elementos seleccionados fueron asociados con el equipo.";

        equipoID = servicioEquipment.consultarUltimoIdEquipment();
        nombreEquipo = servicioEquipment.consultarNombreEquipo(equipoID);

        tecladoID = getIdByNameElement(nombreTeclado);
        mouseID = getIdByNameElement(nombreMouse);
        pantallaID = getIdByNameElement(nombrePantalla);
        torreID = getIdByNameElement(nombreTorre);

        servicioElement.cambiarIdEquipoParaElemento(equipoID, tecladoID);
        servicioElement.cambiarIdEquipoParaElemento(equipoID, mouseID);
        servicioElement.cambiarIdEquipoParaElemento(equipoID, pantallaID);
        servicioElement.cambiarIdEquipoParaElemento(equipoID, torreID);

        date = new Date(System.currentTimeMillis());

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(true);
        String sessionCorreo = (String) httpSession.getAttribute("correo");
        Usuario usuario = servicioUsuario.consultarIdUsuarioPorCorreo(sessionCorreo);

        novedadTeclado = new Novelty("Teclado "+nombreTeclado+" asociado a equipo "+nombreEquipo, "Elemento Asociado a Equipo", date, usuario.getDocumento(), equipoID, tecladoID);
        novedadMouse = new Novelty("Mouse "+nombreMouse+" asociado a equipo "+nombreEquipo, "Elemento Asociado a Equipo", date, usuario.getDocumento(), equipoID, mouseID);
        novedadPantalla = new Novelty("Pantalla "+nombrePantalla+" asociado a equipo "+nombreEquipo, "Elemento Asociado a Equipo", date, usuario.getDocumento(), equipoID, pantallaID);
        novedadTorre = new Novelty("Torre "+nombreTorre+" asociado a equipo "+nombreEquipo, "Elemento Asociado a Equipo", date, usuario.getDocumento(), equipoID, torreID);

        servicioNovelty.registrarNovedad(novedadTeclado);
        servicioNovelty.registrarNovedad(novedadMouse);
        servicioNovelty.registrarNovedad(novedadPantalla);
        servicioNovelty.registrarNovedad(novedadTorre);

        facesContext.getExternalContext().redirect("../admin/admin.xhtml");
    }

    public void registrarElemento() throws ExceptionHistorialDeEquipos {
        try {
            //-----Registro de Elemento-----
            Element element = new Element(element_name,description,type,"ACTIVO");
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
            this.message = "Elemento de Tipo "+type+" Creado Correctamente";
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
            servicioElement.cambiarIdEquipoParaElemento(null, elemento.getId());
            servicioElement.cambiarEstadoElementosId(elemento.getId(), "ACTIVO");
            Novelty novelty = new Novelty("El elemento "+elemento.getElement_name()+" fue desvinculado de un equipo","Elemento Desvinculado de Equipo", date, usuario.getDocumento(), null, elemento.getId());
            servicioNovelty.registrarNovedad(novelty);
        }
    }

    public void deleteElement() throws ExceptionHistorialDeEquipos{
        this.message = "PRUEBA";
        Integer idElement = getIdByNameElement(nombreElemento);
        Element element = servicioElement.consultarElementoPorId(idElement);
        if(element.getId_equipment() == null && !element.getEstado().equals("ACTIVO")){
            message = "El elemento "+element.getElement_name()+" fue dado de baja.";
            servicioElement.cambiarEstadoElementosId(element.getId(), "INACTIVO");
            Date date = new Date(System.currentTimeMillis());
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            String correoSession = (String) session.getAttribute("correo");
            Usuario usuario = servicioUsuario.consultarIdUsuarioPorCorreo(correoSession);
            Novelty novelty = new Novelty("Dado de baja","Inactivo elemento "+element.getElement_name(), date, usuario.getDocumento(), null, element.getId());
            servicioNovelty.registrarNovedad(novelty);
        }
        else{
            message = "El elemento "+element.getElement_name()+" Esta asociado al equipo "+servicioEquipment.consultarEquipoPorId(element.getId_equipment()).getEquipment_name();
        }
    }

    public void asociarElemento() throws ExceptionHistorialDeEquipos{
        Integer idEquipment = getIdByNameEquipment(nombreEquipo);
        id = getIdByNameElement(nombreElemento);

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
            this.message = "Se asocio correctamente el elemento";
        } catch (Exception e) {
            message = "Error al asociar el elemento";
            throw new ExceptionHistorialDeEquipos(e.toString());
        }
    }

    public void changeEquipmentElement() throws ExceptionHistorialDeEquipos{
        Integer idEquipo = servicioEquipment.consultarUltimoIdEquipment();
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

    public void darDeBajaElemento() throws ExceptionHistorialDeEquipos {
        Date date;
        Integer elementoID;

        date = new Date(System.currentTimeMillis());
        elementoID = servicioElement.consultarElementoIDPorNombre(nombreElemento);
        message = "El Elemento "+nombreElemento+" fue dado de baja con exito.";

        servicioElement.cambiarEstadoElementosId(elementoID, "INACTIVO");

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        String correoSession = (String) session.getAttribute("correo");
        Usuario usuario = servicioUsuario.consultarIdUsuarioPorCorreo(correoSession);

        Novelty novelty = new Novelty("El Elemento "+nombreElemento+" fue dado de baja", "Elemento Dado de Baja", date, usuario.getDocumento(), elementoID, "Element");
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

    public Integer getIdByNameEquipment(String nombre) throws ExceptionHistorialDeEquipos {
        Integer idEquipment = null;

        equipos = servicioEquipment.consultarEquipos();

        for(Equipment equipo: equipos){
            if(equipo.getEquipment_name().equals(nombre)){
                idEquipment = equipo.getId();
            }
        }
        return idEquipment;
    }

    public Integer getIdByNameElement(String nombre) throws ExceptionHistorialDeEquipos {
        Integer idElement = null;

        elementosBusquedaBasica = servicioElement.consultarElementos();

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
        Novelty novelty;

        message = "Los Elementos seleccionados fueron dados de baja";

        Date date = new Date(System.currentTimeMillis());
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(true);
        String sessionCorreo = (String) httpSession.getAttribute("correo");
        Usuario usuario = servicioUsuario.consultarIdUsuarioPorCorreo(sessionCorreo);

        for(Element e:elementosSeleccionados) {
            if(e.getId_equipment()==null) {
                novelty = new Novelty("El Elemento "+e.getElement_name()+" fue dado de baja.", "Elemento Dado de Baja", date, usuario.getDocumento(), e.getId(), "Element");

                servicioElement.cambiarEstadoElementosId(e.getId(), "INACTIVO");;
                servicioNovelty.registrarNovedad(novelty);
            }
            else {
                novelty = new Novelty("El Elemento "+e.getElement_name()+" no se pudo dar de baja ya que esta asociado a un equipo.", "Error al dar de Baja Elemento", date, usuario.getDocumento(), e.getId(), "Element");
                servicioNovelty.registrarNovedad(novelty);
            }
        }

        elementosBusquedaBasica = servicioElement.consultarElementos();
    }

    public String consultarNombreElemento(Integer elementoID) throws ExceptionHistorialDeEquipos {
        return servicioElement.consultarNombreElemento(elementoID);
    }

    public List<String> getNombresElementos() throws ExceptionHistorialDeEquipos {
        nombresElementos = new ArrayList<>();
        elementosBusquedaBasica = servicioElement.consultarElementos();

        for (Element elemento : elementosBusquedaBasica) {
            nombresElementos.add(elemento.getElement_name());
        }

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

    public List<String> getNombresEquipos() throws ExceptionHistorialDeEquipos {
        nombresEquipos = new ArrayList<>();
        equipos = servicioEquipment.consultarEquipos();

        for(Equipment e:equipos) {
            nombresEquipos.add(e.getEquipment_name());
        }

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

    public List<Element> getElementosBusquedaBasica() throws ExceptionHistorialDeEquipos {
        elementosBusquedaBasica = servicioElement.consultarElementos();

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

    public void info() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, "PrimeFaces Rocks."));
    }
    
    public List<String> getNombreTecladoNoAsociado() throws ExceptionHistorialDeEquipos{
        List<Element> tecladoNoAsociado;
        List<String> nombreTecladoNoAsociado = new ArrayList<>();

        tecladoNoAsociado = servicioElement.consultarElementoNoAsociado("Teclado");

        for(Element e:tecladoNoAsociado) {
            nombreTecladoNoAsociado.add(e.getElement_name());
        }

        return nombreTecladoNoAsociado;
    }

    public List<String> getNombreMouseNoAsociado() throws ExceptionHistorialDeEquipos{
        List<Element> mouseNoAsociado;
        List<String> nombreMouseNoAsociado = new ArrayList<>();

        mouseNoAsociado = servicioElement.consultarElementoNoAsociado("Mouse");

        for(Element e:mouseNoAsociado) {
            nombreMouseNoAsociado.add(e.getElement_name());
        }

        return nombreMouseNoAsociado;
    }

    public List<String> getNombrePantallaNoAsociado() throws ExceptionHistorialDeEquipos{
        List<Element> pantallaNoAsociado;
        List<String> nombrePantallaNoAsociado = new ArrayList<>();

        pantallaNoAsociado = servicioElement.consultarElementoNoAsociado("Pantalla");

        for(Element e:pantallaNoAsociado) {
            nombrePantallaNoAsociado.add(e.getElement_name());
        }

        return nombrePantallaNoAsociado;
    }

    public List<String> getNombreTorreNoAsociado() throws ExceptionHistorialDeEquipos{
        List<Element> torreNoAsociado;
        List<String> nombreTorreNoAsociado = new ArrayList<>();

        torreNoAsociado = servicioElement.consultarElementoNoAsociado("Torre");

        for(Element e:torreNoAsociado) {
            nombreTorreNoAsociado.add(e.getElement_name());
        }

        return nombreTorreNoAsociado;
    }

    public String getNombreTeclado() {
        return nombreTeclado;
    }

    public void setNombreTeclado(String nombreTeclado) {
        this.nombreTeclado = nombreTeclado;
    }

    public String getNombreMouse() {
        return nombreMouse;
    }

    public void setNombreMouse(String nombreMouse) {
        this.nombreMouse = nombreMouse;
    }

    public String getNombrePantalla() {
        return nombrePantalla;
    }

    public void setNombrePantalla(String nombrePantalla) {
        this.nombrePantalla = nombrePantalla;
    }

    public String getNombreTorre() {
        return nombreTorre;
    }

    public void setNombreTorre(String nombreTorre) {
        this.nombreTorre = nombreTorre;
    }

    public List<String> getNombresElementosNoAsociados() throws ExceptionHistorialDeEquipos {
        List<Element> elementos;
        List<String> nombresElementosNoA;

        nombresElementosNoA = new ArrayList<>();
        elementos = servicioElement.consultarElementos();

        for(Element e:elementos) {
            if(e.getId_equipment()==null && e.getEstado().equals("ACTIVO")) {
                nombresElementosNoA.add(e.getElement_name());
            }
        }

        return nombresElementosNoA;
    }
}
