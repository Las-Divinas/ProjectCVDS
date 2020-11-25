package edu.eci.cvds.view;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import edu.eci.cvds.samples.services.*;

@ManagedBean(name = "noveltyBean")
@SessionScoped
public class NoveltyBean extends BasePageBean{

    private static final long serialVersionUID = 4054189115632351646L;

    @Inject
    private ServicioUsuario servicioUsuario;

    @Inject
    private ServicioNovelty servicioNovelty;

    @Inject
    private ServicioEquipment servicioEquipment;

    @Inject
    private ServicioElement servicioElement;

    @Inject
    ServicioLaboratory servicioLaboratory;

    private int id;
    private String description;
    private String idUser;
    private int idEquipment;
    private String title;
    private int idElement;
    private String message;
    private List<Novelty> novedadBusquedaBasica;
    private List<Equipment> equipos;
    private List<String> nombresEquipos;
    private String nombreEquipo;
    private List<Element> elementos;
    private List<String> nombresElementos;
    private String nombreElemento;

    @PostConstruct
    public void init(){
        super.init();
    }

    public void registrarNovedad() throws ExceptionHistorialDeEquipos, IOException{
        Date date = new Date(System.currentTimeMillis());
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        String correoSession = (String) session.getAttribute("correo");
        Usuario usuario = servicioUsuario.consultarIdUsuarioPorCorreo(correoSession);
        idEquipment = getIdByNameEquipment(nombreEquipo);
        Novelty novelty = new Novelty(id, description,title, date , usuario.getDocumento(), idEquipment);
        message = "Se agrego la nueva novedad al equipo "+nombreEquipo;
        servicioNovelty.registrarNovedad(novelty);
    }

    public void registrarNovedadElemento() throws ExceptionHistorialDeEquipos{
        Date date = new Date(System.currentTimeMillis());
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        String correoSession = (String) session.getAttribute("correo");
        Usuario usuario = servicioUsuario.consultarIdUsuarioPorCorreo(correoSession);
        idElement = getIdByNameElement(nombreElemento);
        Element elemento = servicioElement.consultarElementoPorId(idElement);
        System.out.println(elemento.getElement_name()+"-------------"+elemento.getId_equipment());
        Novelty novelty = new Novelty(description,title, date, usuario.getDocumento(), elemento.getId_equipment(), idElement);
        message = "Se agrego la nueva novedad al elemento "+nombreElemento+" del equipo "+servicioEquipment.consultarEquipoPorId(elemento.getId_equipment()).getEquipment_name();
        servicioNovelty.registrarNovedad(novelty);
    }

    public void consultarNovedadEquipoID(int equipoID) throws  ExceptionHistorialDeEquipos,IOException{
        this.novedadBusquedaBasica = servicioNovelty.consultarNovedadesPorIDEquipo(equipoID);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().redirect("../public/consultNovelty.xhtml");
    }

    public void consultarNovedadElementoID(int elementoID) throws ExceptionHistorialDeEquipos,IOException{
        this.novedadBusquedaBasica = servicioNovelty.consultarNovedadesPorIDElemento(elementoID);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().redirect("../public/consultNovelty.xhtml");
    }

    public int getIdByNameEquipment(String name){
        int idEquipment = 0;
        for(Equipment equipo: equipos){
            if(equipo.getEquipment_name().equals(name)){
                idEquipment = equipo.getId();
            }
        }
        return idEquipment;
    }

    public int getIdByNameElement(String name) throws ExceptionHistorialDeEquipos{
        int idElement = 0;
        equipos = servicioEquipment.consultarEquipos();

        for(Element elemento: elementos) {
            if(elemento.getElement_name().equals(name)){
                idElement = elemento.getId();
            }
        }
        return idElement;
    }

    public String darFormatoFehca(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

        return format.format(date);
    }

    public List<String> getNombresEquipos() throws ExceptionHistorialDeEquipos {
        nombresEquipos = new ArrayList<>();
        equipos = servicioEquipment.consultarEquipos();

        for(Equipment equipo: equipos) {
            nombresEquipos.add(equipo.getEquipment_name());
        }

        return nombresEquipos;
    }

    public void setNombresEquipos(List<String> nombresEquipos){
        this.nombresEquipos = nombresEquipos;
    }

    public List<String> getNombresElementos() throws ExceptionHistorialDeEquipos {
        nombresElementos = new ArrayList<>();
        elementos = servicioElement.consultarElementos();

        for(Element elemento: elementos){
            nombresElementos.add(elemento.getElement_name());
        }

        return nombresElementos;
    }

    public void setNombresElementos(List<String> nombresElementos){
        this.nombresElementos = nombresElementos;
    }

    public String getNombreEquipo(){
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo){
        this.nombreEquipo = nombreEquipo;
    }

    public String getNombreElemento(){
        return nombreElemento;
    }

    public void setNombreElemento(String nombreElemento){
        this.nombreElemento = nombreElemento;
    }

    public int getIdElement(){
        return idElement;
    }

    public void setIdElement(int idElement){
        this.idElement = idElement;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
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

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public List<Novelty> getNovedadBusquedaBasica() throws ExceptionHistorialDeEquipos {
        novedadBusquedaBasica = servicioNovelty.consultarNovedades();

        return novedadBusquedaBasica;
    }

    public void setNovedadBusquedaBasica(List<Novelty> novedadBusquedaBasica) {
        this.novedadBusquedaBasica = novedadBusquedaBasica;
    }
    public void info() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, "PrimeFaces Rocks."));
    }
}
