package edu.eci.cvds.view;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

@SuppressWarnings("deprecation")
@ManagedBean(name = "registerBean")
@SessionScoped
public class RegisterBean extends BasePageBean{

    private static final long serialVersionUID = 1L;
    private String documento;
    private String correo;
    private String nombre;
    private String contraseña;
    private String rol;

    public void registerNewUser(){
        /* Here, we have to use the method that wa have to create in serviceimp and with this use mappers and DAO´s */
    }

    public String getDocumento(){
        return documento;
    }

    public void setDocumento(String documento){
        this.documento=documento;
    }

    public String getCorre(){
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
}