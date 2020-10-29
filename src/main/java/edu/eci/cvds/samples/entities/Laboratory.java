package edu.eci.cvds.samples.entities;

import java.io.Serializable;
import java.util.List;

public class  Laboratory implements Serializable {
    
    private static final long serialVersionUID = 2225826998261144603L;
    private int id;
    private String name;
    private String description;
    private List<Equipment> equipos;

    public Laboratory() {

    }

    public Laboratory(int id, String name, String description, List<Equipment> equipos)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.equipos = equipos;
    }

    public int getId()
    {
        return this.id;
    }
    public void setId(int id)
    {
        this.id = id;
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

    public List<Equipment> getEquipos()
    {
        return this.equipos;
    }
    public void setEquipos(List<Equipment> equipos)
    {
        this.equipos = equipos;
    }
    

   
}