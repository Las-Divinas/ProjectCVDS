package edu.eci.cvds.view;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
    private String a[] = new String[] {"Torre","Pantalla","Mouse","Teclado"};
    private List<String> types = Arrays.asList(a);

    public void registrarElemento() throws ExceptionHistorialDeEquipos, IOException{
        int id_equipo = servicioUsuario.consultarUltimoId();
        Element element = new Element(id, name, description, id_equipo,type);
        servicioUsuario.registrarElemento(element);
        Date date = new Date(System.currentTimeMillis());
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        String correoSession = (String) session.getAttribute("correo");
        Usuario usuario = servicioUsuario.consultarIdUsuarioPorCorreo(correoSession);
        int id_elemento = servicioUsuario.consultarUltimoIdElement();
        Novelty novelty = new Novelty("Elemento agregado al equipo"+id_equipo, "Se agrego le agrego a equipo"+"", date, usuario.getDocumento() , id_elemento);
        System.out.println(novelty.getTitle()+"-----------------------------asasasasas");
        servicioUsuario.registrarNovedad(novelty);
    
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
}
