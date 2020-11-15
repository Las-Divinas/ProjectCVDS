package edu.eci.cvds.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.google.inject.Inject;

import edu.eci.cvds.samples.entities.Element;
import edu.eci.cvds.samples.entities.Novelty;
import edu.eci.cvds.samples.entities.Usuario;
import edu.eci.cvds.samples.services.ExceptionHistorialDeEquipos;
import edu.eci.cvds.samples.services.ServicioUsuario;

@ManagedBean(name = "noveltyBean")
@SessionScoped
public class NoveltyBean extends BasePageBean{

    private static final long serialVersionUID = 4054189115632351646L;

    @Inject
    private ServicioUsuario servicioUsuario;

    private int id;
    private String description;
    private String idUser;
    private int idEquipment;
    private String title;
    private int idElement;
    private String message;
    private List<Novelty> novedadBusquedaBasica;

    @PostConstruct
    public void init(){
        super.init();
        try{
            novedadBusquedaBasica = new ArrayList<>();
            novedadBusquedaBasica = servicioUsuario.consultarNovedades();
        } catch (ExceptionHistorialDeEquipos e){
            e.printStackTrace();
        }
    }

    public void registrarNovedad() throws ExceptionHistorialDeEquipos, IOException{
        Date date = new Date(System.currentTimeMillis());
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        String correoSession = (String) session.getAttribute("correo");
        Usuario usuario = servicioUsuario.consultarIdUsuarioPorCorreo(correoSession);
        Novelty novelty = new Novelty(id, description,title, date , usuario.getDocumento(), idEquipment, idElement);
        message = "Se agrego la nueva novedad al equipo "+idEquipment;
        servicioUsuario.registrarNovedad(novelty);
    }

    public void registrarNovedadElemento() throws ExceptionHistorialDeEquipos{
        Date date = new Date(System.currentTimeMillis());
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        String correoSession = (String) session.getAttribute("correo");
        Usuario usuario = servicioUsuario.consultarIdUsuarioPorCorreo(correoSession);
        Element elemento = servicioUsuario.consultarElementoPorId(idElement);
        System.out.println(elemento.getName()+"-------------"+elemento.getId_equipment());
        Novelty novelty = new Novelty(description,title, date, usuario.getDocumento(), elemento.getId_equipment(), 0);
        message = "Se agrego la nueva novedad al elemento "+idElement+" del equipo "+idEquipment;
        servicioUsuario.registrarNovedad(novelty);
    }

    public void consultarNovedadEquipoID(int equipoID) throws  ExceptionHistorialDeEquipos,IOException{
        this.novedadBusquedaBasica = servicioUsuario.consultarNovedadesPorIDEquipo(equipoID);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().redirect("../public/consultNovelty.xhtml");
    }

    public void consultarNovedadElementoID(int elementoID) throws ExceptionHistorialDeEquipos,IOException{
        this.novedadBusquedaBasica = servicioUsuario.consultarNovedadesPorIDElemento(elementoID);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().redirect("../public/consultNovelty.xhtml");
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

    public List<Novelty> getNovedadBusquedaBasica() {
        return novedadBusquedaBasica;
    }

    public void setNovedadBusquedaBasica(List<Novelty> novedadBusquedaBasica) {
        this.novedadBusquedaBasica = novedadBusquedaBasica;
    }
}
