package edu.eci.cvds.samples.entities;

import java.io.Serializable;

public class  Element implements Serializable
{

    private static final long serialVersionUID = 3057312705708674257L;
    private int id;
    private String name;
    private String description;
    private String type;
    private int id_equipment;
    private String estado;

    public Element()
    {

    }

    public Element(int id,String name,String description,int id_equipment, String type, String estado)
    {
        this.id=id;
        this.name = name;
        this.description = description;
        this.id_equipment = id_equipment;
        this.type = type;
        this.estado = estado;
    }

    public Element(String name,String description,int id_equipment, String type)
    {
        this.name = name;
        this.description = description;
        this.id_equipment = id_equipment;
        this.type = type;
    }

    public Element(String name,String description,int id_equipment)
    {
        this.name = name;
        this.description = description;
        this.id_equipment = id_equipment;
    }

    public String getName()
    {
        return this.name;
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

    public int getId_equipment()
    {
        return id_equipment;
    }
    public void setId_equipment(int id_equipment)
    {
        this.id_equipment = id_equipment;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getEstado(){
        return estado;
    }

    public void setEstado(String estado){
        this.estado = estado;
    }
}