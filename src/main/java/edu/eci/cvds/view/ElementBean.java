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
import edu.eci.cvds.samples.entities.Novelty;
import edu.eci.cvds.samples.entities.Usuario;
import edu.eci.cvds.samples.services.ExceptionHistorialDeEquipos;
import edu.eci.cvds.samples.services.ServicioUsuario;
import java.util.Date;

@ManagedBean(name = "elementBean")
@SessionScoped
public class ElementBean extends BasePageBean{

    private static final long serialVersionUID = -6003014894429487789L;

    @Inject
    private ServicioUsuario servicioUsuario;
    private int id;
    private String name;
    private String type;
    private String description;
    private int idEquipment;
    private String message = "Se creo el elemento con exito";
    private String a[] = new String[] {"Torre","Pantalla","Mouse","Teclado"};
    private List<String> types = Arrays.asList(a);
    private List<Element> elementosBusquedaBasica;

    @PostConstruct
    public void init(){
        super.init();
        try{
            elementosBusquedaBasica = new ArrayList<>();
            elementosBusquedaBasica = servicioUsuario.consultarElementos();
        } catch (ExceptionHistorialDeEquipos e){
            e.printStackTrace();
        }
    }

    public void registrarElemento() throws ExceptionHistorialDeEquipos, IOException{
        Element element = new Element(id, name, description, idEquipment,type,"ACTIVO");
        servicioUsuario.registrarElemento(element);
        Date date = new Date(System.currentTimeMillis());
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        String correoSession = (String) session.getAttribute("correo");
        Usuario usuario = servicioUsuario.consultarIdUsuarioPorCorreo(correoSession);
        int id_elemento = servicioUsuario.consultarUltimoIdElement();
        Novelty novelty = new Novelty("Elemento agregado al equipo"+idEquipment, "Se agrego le agrego a equipo"+"", date, usuario.getDocumento() ,idEquipment,id_elemento);
        message = "Se creo que correctamente el elemento del equipo"+idEquipment;
        servicioUsuario.registrarNovedad(novelty);
    
    }

    public void changeEquipmentElement() throws ExceptionHistorialDeEquipos, IOException{
        int idEquipo = servicioUsuario.consultarUltimoId();
        Element elemento = servicioUsuario.consultarElementoPorId(id);
        if(!(elemento.getId_equipment()==idEquipo)){
            servicioUsuario.cambiarIdEquipoParaElemento(idEquipo, id);
            Date date = new Date(System.currentTimeMillis());
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            String correoSession = (String) session.getAttribute("correo");
            Usuario usuario = servicioUsuario.consultarIdUsuarioPorCorreo(correoSession);
            Novelty novelty = new Novelty("Elemento agregado al equipo"+idEquipo, "Se agrego le agrego a equipo"+"", date, usuario.getDocumento() ,idEquipo,id);
            servicioUsuario.registrarNovedad(novelty);
            message = "Se ha asociado correctamente el elemento al equipo "+idEquipo;
        }
        else{
            message = "El equipo ya esta asociado a ese equipo :)";
        }
    }

    public List<Element> consultarElementos() throws ExceptionHistorialDeEquipos{
        return servicioUsuario.consultarElementos();
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
}
