package edu.eci.cvds.view;

import java.io.IOException;
import java.util.*;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.google.inject.Inject;

import org.apache.shiro.crypto.hash.Sha512Hash;

import edu.eci.cvds.samples.entities.Usuario;
import edu.eci.cvds.samples.services.ExceptionHistorialDeEquipos;
import edu.eci.cvds.samples.services.ServicioUsuario;

@SuppressWarnings("deprecation")
@ManagedBean(name = "registerBean")
@SessionScoped
public class RegisterBean extends BasePageBean {
    @Inject
    private ServicioUsuario servicioUsuario;

    private static final long serialVersionUID = 1L;
    private String documento;
    private String correo;
    private String nombre;
    private String contraseña;
    private String rol;
    private String a[] = new String[] { "admin", "user" }; 
    private List<String> roles = Arrays.asList(a);

    public void registerNewUser() throws ExceptionHistorialDeEquipos, IOException{
        try {
            servicioUsuario.registrarUsuario(new Usuario(documento, correo, nombre,new Sha512Hash(contraseña).toHex(),rol));
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.getExternalContext().redirect("../Login.xhtml");
        } catch (ExceptionHistorialDeEquipos e) {
            throw new ExceptionHistorialDeEquipos("Error al registrar el equipo");
        }
    }

    public void redirectToLogin() throws IOException{
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().redirect("../Login.xhtml");
    }

    public String getDocumento(){
        return documento;
    }

    public void setDocumento(String documento){
        this.documento=documento;
    }

    public String getCorreo(){
        return correo;
    }

    public void setCorreo(String correo){
        this.correo=correo;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public String getContraseña(){
        return contraseña;
    }

    public void setContraseña(String contraseña){
        this.contraseña=contraseña;
    }

    public String getRol(){
        return rol;
    }

    public void setRol(String rol){
        this.rol=rol;
    } 

    public List<String> getRoles(){
        return roles;
    }

    public void setRoles(List<String> roles){
        this.roles=roles;
    }
}