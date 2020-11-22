package edu.eci.cvds.samples.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class  Laboratory implements Serializable {
    
    private static final long serialVersionUID = 2225826998261144603L;
    private String name;
    private String description;
    private List<Equipment> equipos;
    private int id;
    private String estado;
    private Date fecha_creacion;
    private Date fecha_cierre;

    public Laboratory() {

    }

    public Laboratory(String name, String description, String estado, Date fecha_creacion, Date fecha_cierre) {
        this.name = name;
        this.description = description;
        this.estado = estado;
        this.fecha_creacion = fecha_creacion;
        this.fecha_cierre = fecha_cierre;
    }

    public Laboratory(int id, String name, String description) {
        this.id=id;
        this.name=name;
        this.description=description;
    }

    public Laboratory(int id, String name, String description, String estado, Date fecha_creacion, Date fecha_cierre) {
        this.id=id;
        this.name=name;
        this.description=description;
        this.estado=estado;
        this.fecha_creacion=fecha_creacion;
        this.fecha_cierre=fecha_cierre;
    }

    public Laboratory(String name, String description) {
        this.name=name;
        this.description=description;
    }

    public Laboratory(String name, String description, List<Equipment> equipos) {
        this.name = name;
        this.description = description;
        this.equipos = equipos;
    }

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return this.description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }

    public List<Equipment> getEquipos()
    {
        return this.equipos;
    }
    public void setEquipos(List<Equipment> equipos)
    {
        this.equipos = equipos;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getEstado()
    {
        return this.estado;
    }
    public void setEstado(String estado)
    {
        this.estado = estado;
    }
    public Date getFecha_creacion()
    {
        return this.fecha_creacion;
    }
    public void setFecha_creacion(Date fecha_creacion)
    {
        this.fecha_creacion = fecha_creacion;
    }
    public Date getFecha_cierre()
    {
        return this.fecha_cierre;
    }
    public void setFecha_cierre(Date fecha_cierre)
    {
        this.fecha_cierre = fecha_cierre;
    }
}