package edu.eci.cvds.samples.entities;

import java.io.Serializable;
import java.util.List;

public class  Equipment implements Serializable
{

    private static final long serialVersionUID = -1857291145846510680L;
    private int id;
    private String equipment_name;
    private String description ;
    private int laboratory_id;
    private List<Element> elementos;
    private List<Novelty> novedades;
    private String estado;

    public Equipment()
    {

    }

    public Equipment(String equipment_name, String description, String estado) {
        this.equipment_name = equipment_name;
        this.description = description;
        this.estado = estado;
    }

    public Equipment(int id,String equipment_name, String description, int laboratory_id){
        this.id=id;
        this.equipment_name = equipment_name;
        this.description=description;
        this.laboratory_id=laboratory_id;
    }

    public Equipment(String equipment_name, String description, int laboratory_id){
        this.equipment_name = equipment_name;
        this.description=description;
        this.laboratory_id=laboratory_id;
    }

    public Equipment(String equipment_name, String description, int laboratory_id, String estado){
        this.equipment_name = equipment_name;
        this.description=description;
        this.laboratory_id=laboratory_id;
        this.estado = estado;
    }

    public Equipment(String description,String equipment_name,int laboratory_id,List<Element> elementos,List<Novelty> novedades)
    {
        this.description = description;
        this.equipment_name = equipment_name;
        this.laboratory_id = laboratory_id;
        this.elementos = elementos;
        this.novedades = novedades ;
    }

    public String getEstado(){
        return estado;
    }

    public void setEstado(String estado){
        this.estado = estado;
    }

    public String getDescription()
    {
        return this.description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getEquipment_name() {
        return equipment_name;
    }

    public void setEquipment_name(String equipment_name) {
        this.equipment_name = equipment_name;
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