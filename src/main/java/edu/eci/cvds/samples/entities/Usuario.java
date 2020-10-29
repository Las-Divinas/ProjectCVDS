package edu.eci.cvds.samples.entities;

import java.io.Serializable;

public class  Usuario implements Serializable
{
    private String documento;
    private String correo;
    private String nombre;
    private String contraseña;
    private String rol;
    private List<Novelty> novedades;

    public Usuario()
    {

    }

    public Usuario(String documento,String correo,String nombre,String contraseña,String rol,List<Novelty> novedades)
    {
        this.documento = documento;
        this.correo = correo;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.rol = rol;
        this.novedades = novedades;
    }

    public String getDocumento()
    {
        return this.documento;
    }
    public void setDocumento(String documento)
    {
        this.documento = documento;
    }

    public String getCorreo()
    {
        return this.correo;
    }
    public void setCorreo(String correo)
    {
        this.correo = correo;
    }

    public String getNombre()
    {
        return this.nombre;
    }
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getContraseña()
    {
        return this.contraseña;
    }
    public void setContraseña(String contraseña)
    {
        this.contraseña = contraseña;
    }

    public String getRol()
    {
        return this.documento;
    }
    public void setRol(String rol)
    {
        this.rol = rol;
    }
}