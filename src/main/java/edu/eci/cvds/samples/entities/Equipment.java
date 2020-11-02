package edu.eci.cvds.samples.entities;

import java.io.Serializable;
import java.util.List;

public class  Equipment implements Serializable
{

    private static final long serialVersionUID = -1857291145846510680L;
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

    public Equipment(String description,int element,String name,int laboratory_id,List<Element> elementos,List<Novelty> novedades)
    {
        this.description = description;
        this.element = element;
        this.name = name;
        this.laboratory_id = laboratory_id;
        this.elementos = elementos;
        this.novedades = novedades ;
    }

    public String getDescription()
    {
        return this.description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getElement()
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

    public int getLaboratory_id()
    {
        return this.laboratory_id;
    }
    public void setLaboratory_id(int laboratory_id)
    {
        this.laboratory_id = laboratory_id;
    }
    
    public List<Element> getElementos()
    {
        return this.elementos;
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
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
}