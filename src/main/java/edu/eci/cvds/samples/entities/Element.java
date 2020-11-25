package edu.eci.cvds.samples.entities;

import java.io.Serializable;

public class  Element implements Serializable
{

    private static final long serialVersionUID = 3057312705708674257L;
    private int id;
    private String element_name;
    private String description;
    private String type;
    private Integer id_equipment;
    private String estado;

    public Element() {
    }

    public Element(String element_name, String description, String type, String estado) {
        this.element_name = element_name;
        this.description = description;
        this.type = type;
        this.estado = estado;
    }

    public Element(int id,String element_name,String description, String type, String estado)
    {
        this.id=id;
        this.element_name = element_name;
        this.description = description;
        this.type = type;
        this.estado = estado;
    }

    public Element(String element_name,String description,int id_equipment, String type)
    {
        this.element_name = element_name;
        this.description = description;
        this.id_equipment = id_equipment;
        this.type = type;
    }

    public Element(String element_name,String description,int id_equipment)
    {
        this.element_name = element_name;
        this.description = description;
        this.id_equipment = id_equipment;
    }

    public String getElement_name() {
        return element_name;
    }

    public void setElement_name(String element_name) {
        this.element_name = element_name;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Integer getId_equipment()
    {
        return id_equipment;
    }

    public void setId_equipment(Integer id_equipment)
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