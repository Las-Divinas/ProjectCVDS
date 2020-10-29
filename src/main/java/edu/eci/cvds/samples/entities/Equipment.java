package edu.eci.cvds.samples.entities;

import java.io.Serializable;
import java.util.Date;

public class  Equipment implements Serializable
{
    private int id;
    private int element;
    private String name;
    private String description ;
    private int laboratory_id;
    private List<Element> elementos;
    private List<Novelty> novedades;

    public Equipment()
    {

    }

    public Equipment(int id,String description,int element,String name,int laboratory_id,List<Element> elementos,List<Novelty> novedades)
    {
        this.id = id;
        this.description = description;
        this.element = element;
        this.name = name;
        this.laboratory_id = laboratory_id;
        this.elementos = elementos;
        this.novedades = novedades ;
    }

    public String getId()
    {
        return this.id;
    }
    public void setId(int id)
    {
        this.id = id;
    }

    public String getDescription()
    {
        return this.description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getElement()
    {
        return this.element;
    }
    public void setElement(int element)
    {
        this.element = element;
    }

    public String getName()
    {
        return this.name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getLaboratory_id()
    {
        return this.laboratory_id;
    }
    public void setLaboratory_id(int laboratory_id)
    {
        this.laboratory_id = laboratory_id;
    }
    
    public List<Element> getElementos()
    {
        return this.laboratory_id;
    }
    public void setElementos(List<Element> elementos)
    {
        this.elementos = elementos;
    }

    public List<Novelty> getNovedades()
    {
        return this.novedades;
    }
    public void setNovedades(List<Novelty> novedades)
    {
        this.novedades = novedades;
    }
}