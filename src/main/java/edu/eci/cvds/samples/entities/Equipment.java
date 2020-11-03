package edu.eci.cvds.samples.entities;

import java.io.Serializable;
import java.util.List;

public class  Equipment implements Serializable
{

    private static final long serialVersionUID = -1857291145846510680L;
    private int id;
    private String name;
    private String description ;
    private int laboratory_id;
    private List<Element> elementos;
    private List<Novelty> novedades;

    public Equipment()
    {

    }
    
    public Equipment(int id,String name, String description, int laboratory_id){
        this.id=id;
        this.name=name;
        this.description=description;
        this.laboratory_id=laboratory_id;
    }

    public Equipment(String name, String description, int laboratory_id){
        this.name=name;
        this.description=description;
        this.laboratory_id=laboratory_id;
    }
    
    public Equipment(String description,String name,int laboratory_id,List<Element> elementos,List<Novelty> novedades)
    {
        this.description = description;
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