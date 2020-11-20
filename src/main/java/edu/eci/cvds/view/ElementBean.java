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
    private String name;
    private String type;
    private String description;
    private int idEquipment;
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
                nombresElementos.add(elemento.getName());
            }
            equipos = servicioEquipment.consultarEquipos();
            for(Equipment equipo: equipos){
                nombresEquipos.add(equipo.getName());
            }
        } catch (ExceptionHistorialDeEquipos e){
            e.printStackTrace();
        }
    }

    public void registrarElemento() throws ExceptionHistorialDeEquipos, IOException{
        idEquipment = getIdByNameEquipment(nombreEquipo);
        desasociarElementoAEQuipo(idEquipment, type);
        Element element = new Element(id, name, description, idEquipment,type,"ACTIVO");
        servicioElement.registrarElemento(element);
        Date date = new Date(System.currentTimeMillis());
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        String correoSession = (String) session.getAttribute("correo");
        Usuario usuario = servicioUsuario.consultarIdUsuarioPorCorreo(correoSession);
        int id_elemento = servicioElement.consultarUltimoIdElement();
        Novelty novelty = new Novelty("Elemento agregado al equipo"+idEquipment, "Se agrego le agrego a equipo"+"", date, usuario.getDocumento() ,idEquipment,id_elemento);
        message = "Se creo que correctamente el elemento del equipo"+idEquipment;
        servicioNovelty.registrarNovedad(novelty);
    
    }

    private void desasociarElementoAEQuipo(int idEquipment, String tipo) throws ExceptionHistorialDeEquipos {
        Element elemento = servicioElement.seleccionarElementoPorIdEquipo(idEquipment, tipo);
        if(!elemento.equals(null)){
            int lastIdEquipment = elemento.getId_equipment();
            System.out.println("-----------------------Prueba--------------------"+elemento.getId()+"++++"+idEquipment+tipo);
            servicioElement.cambiarIdEquipoParaElemento(1, elemento.getId());
            System.out.println("-----------------------Prueba--------------------"+elemento.getId()+"++++"+idEquipment+tipo);
            Date date = new Date(System.currentTimeMillis());
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            String correoSession = (String) session.getAttribute("correo");
            Usuario usuario = servicioUsuario.consultarIdUsuarioPorCorreo(correoSession);
            Novelty novelty = new Novelty("Elemento fue desassociado de "+lastIdEquipment,"desasociacion elemeto "+elemento.getId(), date, usuario.getDocumento(), lastIdEquipment, elemento.getId());
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
        } catch (Exception e) {
            throw new ExceptionHistorialDeEquipos(e.toString());
        }
        
    }

    public void changeEquipmentElement() throws ExceptionHistorialDeEquipos, IOException{
        int idEquipo = servicioEquipment.consultarUltimoIdEquipment();
        id = getIdByNameElement(nombreElemento);
        Element elemento = servicioElement.consultarElementoPorId(id);
        if(!(elemento.getId_equipment()==idEquipo)){
            servicioElement.cambiarIdEquipoParaElemento(idEquipo, id);
            Date date = new Date(System.currentTimeMillis());
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            String correoSession = (String) session.getAttribute("correo");
            Usuario usuario = servicioUsuario.consultarIdUsuarioPorCorreo(correoSession);
            Novelty novelty = new Novelty("Elemento agregado al equipo"+idEquipo, "Se agrego le agrego a equipo"+"", date, usuario.getDocumento() ,idEquipo,id);
            servicioNovelty.registrarNovedad(novelty);
            message = "Se ha asociado correctamente el elemento al equipo "+idEquipo;
        }
        else{
            message = "El equipo ya esta asociado a ese equipo :)";
        }
    }

    public int getIdByNameEquipment(String nombre){
        int idEquipment = 0;
        for(Equipment equipo: equipos){
            if(equipo.getName().equals(nombre)){
                idEquipment = equipo.getId();
            }
        }
        return idEquipment;
    }

    public int getIdByNameElement(String nombre){
        int idElement = 0;
        for(Element elemento: elementosBusquedaBasica){
            System.out.println(elemento.getId()+"++++++++++"+elemento.getName()+"++++++"+nombre);
            if(elemento.getName().equals(nombre)){
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
