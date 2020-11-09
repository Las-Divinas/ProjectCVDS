package edu.eci.cvds.samples.entities;

import java.io.Serializable;
import java.util.Date;

public class  Novelty implements Serializable
{
    private static final long serialVersionUID = -3203284066887482905L;
    private String description;
    private Date date;
    private String user_id ;
    private int equipment_id;
    private int element_id;
    private int id;
    private String title;

    public Novelty()
    {

    }

    public Novelty(int id,String description,String title, Date date,String user_id,int equipment_id)
    {
        this.id=id;
        this.description = description;
        this.date = date;
        this.user_id = user_id;
        this.equipment_id = equipment_id;
        this.title = title;
    }

    public Novelty(int id,String description,String title,Date date,String user_id,int equipment_id,int element_id)
    {
        this.id=id;
        this.description = description;
        this.date = date;
        this.user_id = user_id;
        this.equipment_id = equipment_id;
        this.element_id = element_id;
        this.title = title;
    }

    public Novelty(String description,String title,Date date,String user_id,int equipment_id)
    {
        this.description = description;
        this.date = date;
        this.user_id = user_id;
        this.equipment_id = equipment_id;
        this.title = title;
    }

    public Novelty(String description,String title,Date date,String user_id,int equipment_id,int element_id)
    {
        this.description = description;
        this.date = date;
        this.user_id = user_id;
        this.equipment_id = equipment_id;
        this.element_id = element_id;
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public int getElement_id(){
        return element_id;
    }

    public void setElement_id(int element_id){
        this.element_id = element_id;
    }


    public String getDescription()
    {
        return this.description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }

    public Date getDate()
    {
        return this.date;
    }
    public void setDate(Date date)
    {
        this.date = date;
    }

    public String getUser_id()
    {
        return this.user_id;
    }
    public void setUser_id(String user_id)
    {
        this.user_id = user_id;
    }

    public int getEquipment_id()
    {
        return this.equipment_id;
    }
    public void setEquipment_id(int equipment_id)
    {
        this.equipment_id = equipment_id;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
}