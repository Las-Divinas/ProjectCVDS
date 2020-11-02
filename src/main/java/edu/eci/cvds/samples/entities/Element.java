package edu.eci.cvds.samples.entities;

import java.io.Serializable;

public class  Element implements Serializable
{

    private static final long serialVersionUID = 3057312705708674257L;
    private int id;
    private String name;
    private String description;
    private int id_equipment;

    public Element()
    {

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
        return this.id_equipment;
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
}